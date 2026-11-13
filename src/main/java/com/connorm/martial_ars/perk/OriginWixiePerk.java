package com.connorm.martial_ars.perk;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.api.perk.Perk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

public class OriginWixiePerk extends Perk {
    public static final OriginWixiePerk INSTANCE = new OriginWixiePerk(MartialArs.prefix("origin_thread_wixie"));
    public OriginWixiePerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public @NotNull ItemAttributeModifiers applyAttributeModifiers(ItemAttributeModifiers modifiers, ItemStack stack, int slotValue, EquipmentSlotGroup equipmentSlotGroup) {
        return modifiers.withModifierAdded(MartialRegistry.AilmentDuration, new AttributeModifier(INSTANCE.getRegistryName(), -0.1 * slotValue, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
    }

    @Override
    public String getLangName() {
        return "Origin: The Wixie";
    }

    @Override
    public String getLangDescription() {
        return "The Negation of The Thread of The Wixie. \ndecreases the duration of negative effects by 10% per level.";
    }
}
