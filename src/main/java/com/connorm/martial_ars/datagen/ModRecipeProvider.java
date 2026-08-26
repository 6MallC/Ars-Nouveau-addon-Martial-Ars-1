package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class  ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MartialRegistry.MushroomWand.get())
                .pattern("CBC")
                .pattern(" A ")
                .pattern(" A ")
                .define('A', MartialRegistry.SourceIngot.get())
                .define('B', RecipeDatagen.SOURCE_GEM)
                .define('C', Tags.Items.MUSHROOMS)
                .unlockedBy("has_sourceingot", has(MartialRegistry.SourceIngot)).save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MartialRegistry.BlankInlay.get(), 2)
                .pattern("ABC")
                .pattern("CDA")
                .pattern("ABC")
                .define('A', MartialRegistry.SourceIngot.get())
                .define('B', RecipeDatagen.SOURCE_GEM)
                .define('C', Tags.Items.INGOTS_GOLD)
                .define('D', ItemsRegistry.MAGE_FIBER)
                .unlockedBy("has_sourceingot", has(MartialRegistry.SourceIngot)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MartialRegistry.SourceIngot.get(),9)
                .requires(MartialRegistry.sourceingot_block)
                .unlockedBy("has_sourceingot_block", has(MartialRegistry.sourceingot_block)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MartialRegistry.sourceingot_block.get())
                .requires(MartialRegistry.SourceIngot,9)
                .unlockedBy("has_source_ingot", has(MartialRegistry.SourceIngot)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MartialRegistry.SpearShaft.get())
                .pattern("AB ")
                .pattern("AB ")
                .pattern("AB ")
                .define('A', MartialRegistry.SourceIngot.get())
                .define('B', Tags.Items.INGOTS_GOLD)
                .unlockedBy("has_sourceingot", has(MartialRegistry.SourceIngot)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MartialRegistry.SpearHead.get())
                .pattern("A  ")
                .pattern("A A")
                .pattern("BBB")
                .define('A', MartialRegistry.SourceIngot.get())
                .define('B', Tags.Items.INGOTS_GOLD)
                .unlockedBy("has_sourceingot", has(MartialRegistry.SourceIngot)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MartialRegistry.KunaiBlade.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" BB")
                .define('A', MartialRegistry.SourceIngot.get())
                .define('B', RecipeDatagen.SOURCE_GEM)
                .unlockedBy("has_sourceingot", has(MartialRegistry.SourceIngot)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MartialRegistry.KunaiHandle.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', MartialRegistry.SourceIngot.get())
                .define('B', Tags.Items.INGOTS_GOLD)
                .unlockedBy("has_sourceingot", has(MartialRegistry.SourceIngot)).save(recipeOutput);

    }
}
