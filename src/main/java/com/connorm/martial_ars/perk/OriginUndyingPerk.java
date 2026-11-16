package com.connorm.martial_ars.perk;

import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.api.perk.Perk;
import com.hollingsworth.arsnouveau.api.perk.PerkSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

public class OriginUndyingPerk extends Perk {
    public static final OriginUndyingPerk INSTANCE = new OriginUndyingPerk(MartialArs.prefix("origin_thread_undying"));
    public OriginUndyingPerk(ResourceLocation key) {
        super(key);
    }

    @Override
    public PerkSlot minimumSlot() {
        return PerkSlot.THREE;
    }

    @Override
    public @NotNull ItemAttributeModifiers applyAttributeModifiers(ItemAttributeModifiers modifiers, ItemStack stack, int slotValue, EquipmentSlotGroup equipmentSlotGroup) {
        return modifiers.withModifierAdded(Attributes.MAX_HEALTH, new AttributeModifier(INSTANCE.getRegistryName(), 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), equipmentSlotGroup);
    }

    @Override
    public String getLangName() {
        return "Origin: Undying";
    }

    @Override
    public String getLangDescription() {
        return "The Negation of The Thread of Undying. \nCan only be put in a T3 slot but increases Max Health by 50%.";
    }

}
