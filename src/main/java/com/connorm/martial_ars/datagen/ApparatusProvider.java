package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.common.crafting.recipes.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeBuilder;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;

import java.nio.file.Path;


public class ApparatusProvider extends ApparatusRecipeProvider {

        public ApparatusProvider(DataGenerator generatorIn)  {
            super(generatorIn);
        }

    public void collectJsons(CachedOutput pOutput) {
        this.addEntries();

        for (ApparatusRecipeBuilder.RecipeWrapper<? extends EnchantingApparatusRecipe> recipe : this.recipes) {
            Path path = getRecipePath(this.output, recipe.id().getPath());
            this.saveStable(pOutput, recipe.serialize(), path);
        }
    }
        @Override
        public void addEntries() {
            recipes.add(builder()
                    .withResult(MartialRegistry.SourceMatrix)
                    .withReagent(RecipeDatagen.SOURCE_GEM_BLOCK)
                    .withPedestalItem(4, MartialRegistry.SourceIngot)
                    .withSourceCost(500)
                    .build()
            );
            recipes.add(builder()
                    .withResult(MartialRegistry.sourceingot_block)
                    .withReagent(Items.IRON_BLOCK)
                    .withPedestalItem(4,RecipeDatagen.SOURCE_GEM_BLOCK)
                    .withPedestalItem(2,RecipeDatagen.SOURCE_GEM)
                    .withSourceCost(1000)
                    .build()
            );
            recipes.add(builder()
                    .withResult(MartialRegistry.SourceIngot)
                    .withReagent(Items.IRON_INGOT)
                    .withPedestalItem(2,RecipeDatagen.SOURCE_GEM)
                    .withSourceCost(100)
                    .build()
            );
        }

    }