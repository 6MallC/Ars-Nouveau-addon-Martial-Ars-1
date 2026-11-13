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

public class OriginWhirliPerk extends Perk {
    public static final OriginWhirliPerk INSTANCE = new OriginWhirliPerk(MartialArs.prefix("origin_thread_whirli"));
    public OriginWhirliPerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public @NotNull ItemAttributeModifiers applyAttributeModifiers(ItemAttributeModifiers modifiers, ItemStack stack, int slotValue, EquipmentSlotGroup equipmentSlotGroup) {
        return modifiers.withModifierAdded(MartialRegistry.WhirliHealing, new AttributeModifier(INSTANCE.getRegistryName(), .11 * slotValue, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);

    }

    @Override
    public String getLangName() {
        return "Origin: The Whirlisprig";
    }

    @Override
    public String getLangDescription() {
        return "The Negation of The Thread of The Whirlisprig. \nIncreases all incoming healing by 11% per level.";
    }
}
