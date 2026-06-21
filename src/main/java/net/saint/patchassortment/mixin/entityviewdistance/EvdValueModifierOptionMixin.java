package net.saint.patchassortment.mixin.entityviewdistance;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.lambdaurora.spruceui.option.SpruceOption;
import eu.pb4.entityviewdistance.screen.EvdValueModifierOption;
import net.minecraft.entity.EntityType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Mixin(value = EvdValueModifierOption.class, remap = false)
public abstract class EvdValueModifierOptionMixin extends SpruceOption {

	// Properties

	@Shadow
	@Final
	private EntityType<?> type;

	@Shadow
	@Final
	private Identifier identifier;

	@Shadow
	@Final
	@Mutable
	private Text name;

	@Shadow
	@Final
	@Mutable
	public String nameString;

	// Init

	EvdValueModifierOptionMixin(String key) {
		super(key);
	}

	// Injections

	@Inject(method = "<init>", at = @At("TAIL"))
	private void patchassortment$init(EntityType<?> type, CallbackInfo callbackInfo) {
		var originalLocalizedText = this.name;
		var namespaceString = this.identifier.getNamespace();
		var namespaceText = Text.of(namespaceString);
		var identifierString = this.identifier.toString();
		var identifierText = Text.of(identifierString);
		var augmentedText = Text.empty().append(originalLocalizedText).append(Text.of(" (")).append(namespaceText).append(Text.of(")"));

		this.name = augmentedText;
		this.nameString = identifierString;
		this.setTooltip(identifierText);
	}

}
