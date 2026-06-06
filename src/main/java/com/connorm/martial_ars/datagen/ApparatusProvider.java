package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.registry.ModRegistry;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;


public class ApparatusProvider extends ApparatusRecipeProvider {

        public ApparatusProvider(DataGenerator generatorIn) {
            super(generatorIn);
        }

        @Override
        public void collectJsons(CachedOutput cache) {
            recipes.add(builder()
                    .withResult(ModRegistry.SourceMatrix)
                    .withReagent(RecipeDatagen.SOURCE_GEM_BLOCK)
                    .withPedestalItem(4, ModRegistry.SourceIngot)
                    .withSourceCost(500)
                    .build()
            );
            recipes.add(builder()
                    .withResult(ModRegistry.sourceingot_block)
                    .withReagent(Items.IRON_BLOCK)
                    .withPedestalItem(4,RecipeDatagen.SOURCE_GEM_BLOCK)
                    .withPedestalItem(2,RecipeDatagen.SOURCE_GEM)
                    .withSourceCost(1000)
                    .build()
            );
        }

    }