package net.saint.patchassortment.mixinlogic;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public interface FurnitureItemMixinLogic {

	public default void pa$$place(ItemPlacementContext context, BlockState state, CallbackInfoReturnable<Boolean> callbackInfo) {
		if (!callbackInfo.getReturnValue()) {
			return;
		}

		var world = context.getWorld();

		if (world.isClient()) {
			return;
		}

		var serverWorld = (ServerWorld) world;
		var position = context.getBlockPos();

		serverWorld.playSound(null, position, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
	}
}