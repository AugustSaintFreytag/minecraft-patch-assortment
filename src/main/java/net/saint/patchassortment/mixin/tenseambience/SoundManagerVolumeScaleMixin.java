package net.saint.patchassortment.mixin.tenseambience;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.cyberking42.tenseambience.SoundManager;

/**
 * Scales all ambience volumes by a configurable factor (default 0.5x). Configure via JVM property:
 * -Dpatchassortment.tenseambienceVolumeScale=0.5
 */
@Mixin(value = SoundManager.class, remap = false)
public class SoundManagerVolumeScaleMixin {

	@Unique
	private static final float patchassortment$volumeScale = getScale();

	@ModifyVariable(method = "playLoopingSound", at = @At("HEAD"), argsOnly = true)
	private static float patchassortment$scalePlayLoopingVolume(float volumeMultiplier) {
		return volumeMultiplier * patchassortment$volumeScale;
	}

	@ModifyVariable(method = "updateCurrentSoundVolume", at = @At("HEAD"), argsOnly = true)
	private static float patchassortment$scaleUpdateCurrentVolume(float volumeMultiplier) {
		return volumeMultiplier * patchassortment$volumeScale;
	}

	private static float getScale() {
		try {
			return Math.max(0.0f, (float) Double.parseDouble(System.getProperty("patchassortment.tenseambienceVolumeScale", "0.5")));
		} catch (NumberFormatException ex) {
			return 0.75f;
		}
	}
}
