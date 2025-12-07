package net.saint.patchassortment.mixin.tenseambience;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.cyberking42.tenseambience.SoundManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.sound.SoundCategory;

/**
 * Fixes volume computation by applying the ambience multiplier linearly
 * instead of scaling the dB value, and smooths gain changes to avoid abrupt
 * dropouts when the multiplier or slider flickers.
 */
@Mixin(value = SoundManager.class, remap = false)
public abstract class SoundManagerVolumeComputationMixin {

	@Shadow
	private static float volumeMultiplier;

	@Unique
	private static float patchassortment$smoothedDb = Float.NaN;

	@Unique
	private static final float patchassortment$maxStep = getMaxStepDb();

	@Inject(method = "getAdjustedVolume", at = @At("HEAD"), cancellable = true)
	private static void patchassortment$useLinearVolume(CallbackInfoReturnable<Float> cir) {
		MinecraftClient client = MinecraftClient.getInstance();
		GameOptions options = client.options;
		float slider = options.getSoundVolume(SoundCategory.AMBIENT);
		if (slider <= 0.0f) {
			patchassortment$smoothedDb = -80.0f;
			cir.setReturnValue(-80.0f);
			return;
		}

		// Convert slider (-80..0 dB) to linear gain, apply multiplier linearly, convert back to dB.
		double baseDb = -80.0 + (double) slider * 80.0;
		double linear = Math.pow(10.0, baseDb / 20.0);
		linear *= volumeMultiplier;

		// Prevent log10(0) by clamping to a tiny floor.
		double clampedLinear = Math.max(linear, 1.0e-4);
		double db = 20.0 * Math.log10(clampedLinear);

		// Clamp to mixer limits.
		float targetDb = (float) Math.max(-80.0, Math.min(db, 6.0));

		// Initialize smoothing buffer.
		if (Float.isNaN(patchassortment$smoothedDb)) {
			patchassortment$smoothedDb = targetDb;
		}

		// Move toward target by at most maxStep per call to avoid audible jumps.
		float delta = targetDb - patchassortment$smoothedDb;
		if (Math.abs(delta) > patchassortment$maxStep) {
			patchassortment$smoothedDb += Math.copySign(patchassortment$maxStep, delta);
		} else {
			patchassortment$smoothedDb = targetDb;
		}

		cir.setReturnValue(patchassortment$smoothedDb);
	}

	private static float getMaxStepDb() {
		try {
			return Math.max(0.1f, (float) Double.parseDouble(System.getProperty("patchassortment.tenseambienceMaxDbStep", "1.0")));
		} catch (NumberFormatException ex) {
			return 1.0f;
		}
	}
}
