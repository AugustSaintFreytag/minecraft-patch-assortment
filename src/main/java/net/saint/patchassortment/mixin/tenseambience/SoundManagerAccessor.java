package net.saint.patchassortment.mixin.tenseambience;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.cyberking42.tenseambience.SoundClip;
import net.cyberking42.tenseambience.SoundManager;

@Mixin(value = SoundManager.class, remap = false)
public interface SoundManagerAccessor {

	@Accessor("fading")
	static boolean patchassortment$isFading() {
		throw new AssertionError();
	}

	@Accessor("fading")
	static void patchassortment$setFading(boolean fading) {
		throw new AssertionError();
	}

	@Accessor("currentVolume")
	static float patchassortment$getCurrentVolume() {
		throw new AssertionError();
	}

	@Accessor("currentVolume")
	static void patchassortment$setCurrentVolume(float volume) {
		throw new AssertionError();
	}

	@Accessor("nextVolume")
	static void patchassortment$setNextVolume(float volume) {
		throw new AssertionError();
	}

	@Accessor("nextSound")
	static void patchassortment$setNextSound(SoundClip sound) {
		throw new AssertionError();
	}

	@Accessor("currentSound")
	static SoundClip patchassortment$getCurrentSound() {
		throw new AssertionError();
	}
}
