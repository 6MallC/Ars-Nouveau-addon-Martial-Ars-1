package com.connorm.martial_ars.datagen;

import com.hollingsworth.arsnouveau.common.crafting.recipes.ImbuementRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ImbuementRecipeProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.connorm.martial_ars.datagen.Setup.provider;

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

    }
    }
