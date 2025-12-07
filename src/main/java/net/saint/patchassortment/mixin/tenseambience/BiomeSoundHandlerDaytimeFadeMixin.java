package net.saint.patchassortment.mixin.tenseambience;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.cyberking42.tenseambience.BiomeSoundHandler;
import net.cyberking42.tenseambience.SoundManager;
import net.saint.patchassortment.Mod;

@Mixin(value = BiomeSoundHandler.class, remap = false)
public class BiomeSoundHandlerDaytimeFadeMixin {

	@Redirect(method = "updateBiomeSound", at = @At(value = "INVOKE", target = "Lnet/cyberking42/tenseambience/SoundManager;stopAllSounds()V"))
	private static void patchassortment$fadeInsteadOfHardStop() {
		if (SoundManagerAccessor.patchassortment$getCurrentSound() == null) {
			SoundManager.stopAllSounds();
			return;
		}

		// Force a fade to silence even if another crossfade is already in progress.
		SoundManagerAccessor.patchassortment$setNextSound(null);
		SoundManagerAccessor.patchassortment$setNextVolume(-80.0f);

		// Align the tracked current volume with the clip before starting the fade.
		SoundManagerAccessor.patchassortment$setCurrentVolume(SoundManagerAccessor.patchassortment$getCurrentSound().getVolume());

		SoundManagerAccessor.patchassortment$setFading(true);

		if (patchassortment$DEBUG_FADE_LOGS) {
			Mod.LOGGER.info("[Patch-Assortment] TenseAmbience: fading current ambience to silence (daytime stop). currentVolume={}, nextVolume={}", SoundManagerAccessor.patchassortment$getCurrentVolume(), -80.0f);
		}
	}

	@Unique
	private static final boolean patchassortment$DEBUG_FADE_LOGS = Boolean.getBoolean("patchassortment.tenseambienceDebug");
}
