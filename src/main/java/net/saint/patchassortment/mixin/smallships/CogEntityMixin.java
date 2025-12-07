package net.saint.patchassortment.mixin.smallships;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.talhanation.smallships.world.entity.ship.CogEntity;

@Mixin(CogEntity.class)
public abstract class CogEntityMixin {

	@Inject(method = "waterSplash", at = @At("HEAD"), cancellable = true, remap = false)
	private void patchassortment$waterSplash(CallbackInfo callbackInfo) {
		callbackInfo.cancel();
	}

}
