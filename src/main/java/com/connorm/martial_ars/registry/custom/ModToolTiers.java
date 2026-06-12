package com.connorm.martial_ars.registry.custom;

import com.connorm.martial_ars.registry.ModRegistry;
import com.connorm.martial_ars.util.ModTags;
import com.hollingsworth.arsnouveau.common.items.ModItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier Martial = new SimpleTier(ModTags.Blocks.Incorrect_For_Martial_Tool,
            2048, 10f,5f, 25, () -> Ingredient.of(ModRegistry.SourceIngot));
}
