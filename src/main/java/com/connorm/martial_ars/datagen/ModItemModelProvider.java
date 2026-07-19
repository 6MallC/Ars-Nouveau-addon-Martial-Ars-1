package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.registry.MartialRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MartialArs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(MartialRegistry.SourceIngot.get());
        basicItem(MartialRegistry.SourceMatrix.get());
        basicItem(MartialRegistry.BlankInlay.get());
        basicItem(MartialRegistry.DelayInlay.get());
        basicItem(MartialRegistry.LuckInlay.get());
        basicItem(MartialRegistry.AttackInlay.get());
        basicItem(MartialRegistry.DiscountInlay.get());
    }
}
