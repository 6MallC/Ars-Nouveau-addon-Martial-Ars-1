package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.ArsNouveauRegistry;
import com.connorm.martial_ars.registry.ModRegistry;
import com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModRegistry.MushroomWand.get())
                .pattern("CBC")
                .pattern(" A ")
                .pattern(" A ")
                .define('A',ModRegistry.SourceIngot.get())
                .define('B', RecipeDatagen.SOURCE_GEM)
                .define('C', Tags.Items.MUSHROOMS)
                .unlockedBy("has_sourceingot", has(ModRegistry.SourceIngot)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModRegistry.SourceIngot.get(),9)
                .requires(ModRegistry.sourceingot_block)
                .unlockedBy("has_sourceingot_block", has(ModRegistry.sourceingot_block)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModRegistry.sourceingot_block.get())
                .requires(ModRegistry.SourceIngot,9)
                .unlockedBy("has_source_ingot", has(ModRegistry.SourceIngot)).save(recipeOutput);

    }
}
