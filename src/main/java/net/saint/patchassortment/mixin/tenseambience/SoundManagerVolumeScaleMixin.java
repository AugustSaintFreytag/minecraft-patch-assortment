package net.saint.patchassortment.mixin.tenseambience;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.cyberking42.tenseambience.SoundManager;

@Mixin(value = SoundManager.class, remap = false)
public class SoundManagerVolumeScaleMixin {

	private static final float VOLUME_FACTOR = 0.75f;

	@Shadow
	private static volatile float cachedMasterVolume;

	@Inject(method = "updateMasterVolumeCache", at = @At("TAIL"))
	private static void updateMasterVolumeCache(float volume, CallbackInfo callbackInfo) {
		cachedMasterVolume = volume * VOLUME_FACTOR;
	}

	static {
		SoundManager.updateMasterVolumeCache(VOLUME_FACTOR);
	}

}
