package com.connorm.martial_ars.perk;

import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.api.perk.Perk;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

public class OriginRegenPerk extends Perk {
    public static final OriginRegenPerk INSTANCE = new OriginRegenPerk(MartialArs.prefix("origin_thread_regen"));
    public OriginRegenPerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public @NotNull ItemAttributeModifiers applyAttributeModifiers(ItemAttributeModifiers modifiers, ItemStack stack, int slotValue, EquipmentSlotGroup equipmentSlotGroup) {
        return modifiers.withModifierAdded(PerkAttributes.MANA_REGEN_BONUS, new AttributeModifier(INSTANCE.getRegistryName(), 0.1 * slotValue, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), equipmentSlotGroup);
    }

    @Override
    public String getLangName() {
        return "Origin: Mana Regen";
    }

    @Override
    public String getLangDescription() {
        return "The Negation of The Thread of Mana Capacity. \nIncreases the users Mana Regen by 10%% per level.";
    }

}
