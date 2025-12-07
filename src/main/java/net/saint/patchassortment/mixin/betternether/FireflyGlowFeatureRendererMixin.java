package net.saint.patchassortment.mixin.betternether;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "org.betterx.betternether.entity.render.FireflyGlowFeatureRenderer", remap = false)
public abstract class FireflyGlowFeatureRendererMixin {

	@Inject(method = "addViewAlignedGlow", at = @At("HEAD"), cancellable = true, remap = false)
	private void pa$addViewAlignedGlow(CallbackInfo callbackInfo) {
		callbackInfo.cancel();
	}

}
