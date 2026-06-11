package com.connorm.martial_ars.registry.custom;

import com.connorm.martial_ars.item.RangeTool;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.item.ICasterTool;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import com.hollingsworth.arsnouveau.api.spell.SpellCaster;
import com.hollingsworth.arsnouveau.setup.registry.DataComponentRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class EnchantersSpear extends SwordItem implements ICasterTool, RangeTool {
    public EnchantersSpear(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public EnchantersSpear(Tier iItemTier, int baseDamage, float baseAttackSpeed, Properties properties) {
        super(iItemTier, properties.component(DataComponents.TOOL, createToolProperties())
                .attributes(ItemAttributeModifiers.builder()
                .add(
                        Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(BASE_ENTITY_INTERACTION_RANGE_ID, 2.0F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
                )
                .add(PerkAttributes.SPELL_DAMAGE_BONUS, new AttributeModifier(ArsNouveau.prefix("sword_spell_bonus"), 4.0f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build()));
                //.component(DataComponentRegistry.SPELL_CASTER, new SpellCaster());
    }

}

