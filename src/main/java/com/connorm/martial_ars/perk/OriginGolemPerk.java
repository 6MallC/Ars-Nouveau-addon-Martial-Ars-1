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

public class OriginGolemPerk extends Perk {
    public static final OriginGolemPerk INSTANCE = new OriginGolemPerk(MartialArs.prefix("origin_thread_golem"));
    public OriginGolemPerk(ResourceLocation key) {
        super(key);
    }
    @Override
    public @NotNull ItemAttributeModifiers applyAttributeModifiers(ItemAttributeModifiers modifiers, ItemStack stack, int slotValue, EquipmentSlotGroup equipmentSlotGroup) {
        return modifiers.withModifierAdded(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(INSTANCE.getRegistryName(), 0.5 * slotValue, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), equipmentSlotGroup);
    }

    @Override
    public String getLangName() {
        return "Origin: The Amethyst Golem";
    }

    @Override
    public String getLangDescription() {
        return "The Negation of The Thread of The Amethyst Golem. \nIncreases Attack Knockback by 50% per level.";
}
}
