package com.connorm.martial_ars.datagen;

import alexthw.ars_elemental.ArsElemental;
import alexthw.ars_elemental.datagen.AELangDatagen;
import com.connorm.martial_ars.MartialArs;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MartialArs.MODID)
public class DataGenerators {
    public static CompletableFuture<HolderLookup.Provider> provider;
    public static PackOutput output;
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator =  event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        provider = lookupProvider;
        output = packOutput;


        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput,lookupProvider,blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput,lookupProvider));

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)),lookupProvider));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new ImbuementProvider(generator));
        generator.addProvider(event.includeServer(), new GlyphProvider(generator));
        generator.addProvider(event.includeServer(), new ApparatusProvider(generator));
        generator.addProvider(event.includeServer(), new PotionEffectTagProvider(output, provider, existingFileHelper));
        generator.addProvider(event.includeClient(), new MELangDatagen(output, MartialArs.MODID, "en_us"));
    }
}
