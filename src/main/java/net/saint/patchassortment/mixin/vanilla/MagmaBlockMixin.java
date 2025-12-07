package net.saint.patchassortment.mixin.vanilla;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.MagmaBlock;

@Mixin(AbstractBlockState.class)
public abstract class MagmaBlockMixin {

	@Shadow
	public abstract Block getBlock();

	@Inject(method = "getLuminance", at = @At("HEAD"), cancellable = true)
	private void pa$getLuminance(CallbackInfoReturnable<Integer> callbackInfo) {

		if (getBlock() instanceof MagmaBlock) {
			callbackInfo.setReturnValue(0);
		}
	}

}
