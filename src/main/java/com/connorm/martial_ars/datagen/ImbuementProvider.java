package com.connorm.martial_ars.datagen;

import alexthw.ars_elemental.registry.ModItems;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.common.crafting.recipes.ImbuementRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ImbuementRecipeProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.connorm.martial_ars.datagen.DataGenerators.provider;

public class ImbuementProvider extends ImbuementRecipeProvider {

    public ImbuementProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput pOutput) {
        collectJsons(pOutput);
        List<CompletableFuture<?>> futures = new ArrayList<>();
        return provider.thenCompose((registry) -> {
            for (ImbuementRecipe g : recipes) {
                Path path = getRecipePath(output, g.id.getPath());
                futures.add(DataProvider.saveStable(pOutput, registry, ImbuementRecipe.CODEC, g, path));
            }
            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        });
    }
    @Override
    public void collectJsons(CachedOutput cache) {
        // Banes
       recipes.add(new ImbuementRecipe("bane_of_earth_imbue", Ingredient.of(ModItems.EARTH_BANGLE.get()), new ItemStack(MartialRegistry.EARTHBAGEL.get()), 2500)
               .withPedestalItem(MartialRegistry.SourceIngot)
               .withPedestalItem(MartialRegistry.SourceIngot)
               .withPedestalItem(MartialRegistry.SourceIngot)
               .withPedestalItem(MartialRegistry.SourceIngot));
        recipes.add(new ImbuementRecipe("bane_of_air_imbue", Ingredient.of(ModItems.AIR_BANGLE.get()), new ItemStack(MartialRegistry.AIRBAGEL.get()), 2500)
                .withPedestalItem(MartialRegistry.SourceIngot)
                .withPedestalItem(MartialRegistry.SourceIngot)
                .withPedestalItem(MartialRegistry.SourceIngot)
                .withPedestalItem(MartialRegistry.SourceIngot));
        recipes.add(new ImbuementRecipe("bane_of_water_imbue", Ingredient.of(ModItems.WATER_BANGLE.get()), new ItemStack(MartialRegistry.WATERBAGEL.get()), 2500)
                .withPedestalItem(MartialRegistry.SourceIngot)
                .withPedestalItem(MartialRegistry.SourceIngot)
                .withPedestalItem(MartialRegistry.SourceIngot)
                .withPedestalItem(MartialRegistry.SourceIngot));
        recipes.add(new ImbuementRecipe("bane_of_fire_imbue", Ingredient.of(ModItems.FIRE_BANGLE.get()), new ItemStack(MartialRegistry.FIREBAGEL.get()), 2500)
                .withPedestalItem(MartialRegistry.SourceIngot)
                .withPedestalItem(MartialRegistry.SourceIngot)
                .withPedestalItem(MartialRegistry.SourceIngot)
                .withPedestalItem(MartialRegistry.SourceIngot));
    }
    private static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/ars_nouveau/recipe/imbuement_" + str + ".json");
    }
    }
