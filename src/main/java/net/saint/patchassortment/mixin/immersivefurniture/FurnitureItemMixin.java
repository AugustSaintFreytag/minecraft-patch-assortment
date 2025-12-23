package net.saint.patchassortment.mixin.immersivefurniture;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.conczin.immersive_furniture.item.FurnitureItem;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.saint.patchassortment.mixinlogic.FurnitureItemMixinLogic;

@Mixin(FurnitureItem.class)
public abstract class FurnitureItemMixin implements FurnitureItemMixinLogic {

	@Inject(method = "place", at = @At("RETURN"))
	private void pa$place(ItemPlacementContext context, BlockState state, CallbackInfoReturnable<Boolean> callbackInfo) {
		pa$$place(context, state, callbackInfo);
	}

}
