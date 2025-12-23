package net.saint.patchassortment;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.google.common.collect.ImmutableMap;

import net.fabricmc.loader.api.FabricLoader;

public class ModMixinPlugin implements IMixinConfigPlugin {

	private static final Supplier<Boolean> TRUE = () -> true;

	private static final Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of(
			"net.saint.patchassortment.mixin.smallships.BriggEntityMixin", () -> FabricLoader.getInstance().isModLoaded("smallships"),
			"net.saint.patchassortment.mixin.smallships.CogEntityMixin", () -> FabricLoader.getInstance().isModLoaded("smallships"),
			"net.saint.patchassortment.mixin.betternether.FireflyGlowFeatureRendererMixin",
			() -> FabricLoader.getInstance().isModLoaded("betternether"),
			"net.saint.patchassortment.mixin.tenseambience.SoundManagerAccessor",
			() -> FabricLoader.getInstance().isModLoaded("tense-ambience"),
			"net.saint.patchassortment.mixin.tenseambience.BiomeSoundHandlerDaytimeFadeMixin",
			() -> FabricLoader.getInstance().isModLoaded("tense-ambience"),
			"net.saint.patchassortment.mixin.tenseambience.BiomeSoundHandlerSkyAngleMixin",
			() -> FabricLoader.getInstance().isModLoaded("tense-ambience"),
			"net.saint.patchassortment.mixin.immersivefurniture.FurnitureItemMixin",
			() -> FabricLoader.getInstance().isModLoaded("immersive_furniture"));

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
	}

	@Override
	public void onLoad(String mixinPackage) {
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
	}

	@Override
	public List<String> getMixins() {
		return List.of();
	}

	@Override
	public void preApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName,
			IMixinInfo mixinInfo) {
	}

	@Override
	public void postApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName,
			IMixinInfo mixinInfo) {
	}

}
