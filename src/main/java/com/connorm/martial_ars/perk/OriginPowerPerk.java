package com.connorm.martial_ars.perk;

import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.api.perk.Perk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

public class OriginPowerPerk extends Perk {
    public static final OriginPowerPerk INSTANCE = new OriginPowerPerk(MartialArs.prefix("origin_thread_spellpower"));
    public OriginPowerPerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public @NotNull ItemAttributeModifiers applyAttributeModifiers(ItemAttributeModifiers modifiers, ItemStack stack, int slotValue, EquipmentSlotGroup equipmentSlotGroup) {
        return modifiers.withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(INSTANCE.getRegistryName(), 1.5 * slotValue, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
    }

    @Override
    public String getLangName() {
        return "Origin: Power";
    }

    @Override
    public String getLangDescription() {
        return "The Negation of The Thread of Spell Power. \nIncreases Strength by a flat amount.";
    }
}
