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

public class OriginBunclePerk extends Perk {
    public static final OriginBunclePerk INSTANCE = new OriginBunclePerk(MartialArs.prefix("origin_thread_buncle"));
    public OriginBunclePerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public @NotNull ItemAttributeModifiers applyAttributeModifiers(ItemAttributeModifiers modifiers, ItemStack stack, int slotValue, EquipmentSlotGroup equipmentSlotGroup) {
        return modifiers.withModifierAdded(Attributes.ATTACK_SPEED, new AttributeModifier(INSTANCE.getRegistryName(), 0.2 * slotValue, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), equipmentSlotGroup);
    }

    @Override
    public String getLangName() {
        return "Origin: The Starbuncle";
    }

    @Override
    public String getLangDescription() {
        return "The Negation of The Thread of The Starbuncle. \nIncreases Attack Speed.";
    }
}
