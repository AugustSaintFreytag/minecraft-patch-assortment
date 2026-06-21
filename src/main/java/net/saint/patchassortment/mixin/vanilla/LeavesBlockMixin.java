package net.saint.patchassortment.mixin.vanilla;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.LeavesBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin {

	@Redirect(
			method = "randomDisplayTick",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/util/ParticleUtil;spawnParticle(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/particle/ParticleEffect;)V"))
	private void pa$disableDrippingWaterParticles(World world, BlockPos pos, Random random, ParticleEffect particle) {
	}

}
