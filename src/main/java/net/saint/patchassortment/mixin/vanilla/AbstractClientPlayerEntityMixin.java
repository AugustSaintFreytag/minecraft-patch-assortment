package net.saint.patchassortment.mixin.vanilla;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.network.AbstractClientPlayerEntity;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {

	@ModifyConstant(method = "getFovMultiplier()F", constant = @Constant(floatValue = 0.1F, ordinal = 0), require = 1)
	private float pa$spyglassFovMultiplier(float original) {
		return 0.35F;
	}

}
