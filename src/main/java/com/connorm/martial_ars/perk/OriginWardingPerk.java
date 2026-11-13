package com.connorm.martial_ars.perk;

import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.api.perk.Perk;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

public class OriginWardingPerk extends Perk {
    public static final OriginWardingPerk INSTANCE = new OriginWardingPerk(MartialArs.prefix("origin_thread_warding"));
    public OriginWardingPerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public @NotNull ItemAttributeModifiers applyAttributeModifiers(ItemAttributeModifiers modifiers, ItemStack stack, int slotValue, EquipmentSlotGroup equipmentSlotGroup) {
        return modifiers.withModifierAdded(Attributes.ARMOR, new AttributeModifier(INSTANCE.getRegistryName(), 3 * slotValue, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);

    }

    @Override
    public String getLangName() {
        return "Origin: Warding";
    }

    @Override
    public String getLangDescription() {
        return "The Negation of The Thread of Warding. \nIncreases the users Armor and Armor toughness.";
    }

}
