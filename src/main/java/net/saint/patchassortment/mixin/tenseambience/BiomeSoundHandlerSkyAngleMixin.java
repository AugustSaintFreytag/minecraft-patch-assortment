package net.saint.patchassortment.mixin.tenseambience;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.cyberking42.tenseambience.BiomeSoundHandler;
import net.minecraft.client.MinecraftClient;

@Mixin(value = BiomeSoundHandler.class, remap = false)
public abstract class BiomeSoundHandlerSkyAngleMixin {

	@Shadow
	private static boolean newIsNight;

	@Inject(method = "onClientTick", at = @At(value = "FIELD", target = "Lnet/cyberking42/tenseambience/BiomeSoundHandler;newIsNight:Z", opcode = Opcodes.PUTSTATIC, shift = At.Shift.AFTER))
	private static void patchassortment$useSkyAngleForNight(MinecraftClient client, CallbackInfo ci) {
		var world = client.world;

		if (world == null) {
			return;
		}

		var skyAngle = world.getSkyAngle(1.0f);
		newIsNight = skyAngle > 0.25f && skyAngle < 0.75f;
	}
}