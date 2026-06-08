package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.registry.ModRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MartialArs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModRegistry.SourceIngot.get());
        basicItem(ModRegistry.SourceMatrix.get());
        basicItem(ModRegistry.BlankInlay.get());
        basicItem(ModRegistry.DelayInlay.get());
    }
}
