package com.connorm.martial_ars.item;

import com.connorm.martial_ars.MartialArs;
import net.minecraft.client.searchtree.ResourceLocationSearchTree;
import net.minecraft.resources.ResourceLocation;

public interface RangeTool {

    ResourceLocation BASE_ENTITY_INTERACTION_RANGE_ID = ResourceLocation.fromNamespaceAndPath(MartialArs.MODID,"base_entity_interaction_range");
        ResourceLocation BASE_BLOCK_INTERACTION_RANGE_ID = ResourceLocation.fromNamespaceAndPath(MartialArs.MODID,"base_block_interaction_range");

        ResourceLocation BASE_ENTITY_ARMOR_ID = ResourceLocation.fromNamespaceAndPath(MartialArs.MODID,"base_armor_range");
        ResourceLocation BASE_ENTITY_ARMOR_TOUGHNESS_ID = ResourceLocation.fromNamespaceAndPath(MartialArs.MODID,"base_armor_toughness_range");

        ResourceLocation BASE_ENTITY_KNOCKBACK_RESISTANCE_ID = ResourceLocation.fromNamespaceAndPath(MartialArs.MODID,"base_knockback_resistance");

        ResourceLocation BASE_ENTITY_CRITICAL_DAMAGE_ID = ResourceLocation.fromNamespaceAndPath(MartialArs.MODID,"base_critical_damage");
    }

