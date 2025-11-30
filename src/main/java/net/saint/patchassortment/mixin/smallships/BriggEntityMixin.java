package net.saint.patchassortment.mixin.smallships;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.talhanation.smallships.world.entity.ship.BriggEntity;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BriggEntity.class)
public class BriggEntityMixin {
	
	@Inject(method = "waterSplash", at = @At("HEAD"), cancellable = true, remap = false)
	private void sspd$waterSplash(CallbackInfo callbackInfo) {
		callbackInfo.cancel();
	}

}
