package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.ArsNouveauRegistry;
import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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
        basicItem(MartialRegistry.EARTHBAGEL.get());
        basicItem(MartialRegistry.AIRBAGEL.get());
        basicItem(MartialRegistry.FIREBAGEL.get());
        basicItem(MartialRegistry.WATERBAGEL.get());

        /*for (RitualTablet i : RitualRegistry.getRitualItemMap().values()) {
            try {
                if (i.ritual.getRegistryName().getNamespace().equals(MartialArs.MODID))
                    getBuilder(i.ritual.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", itemTexture(i));
            } catch (Exception e) {
                System.out.println("No texture for " + i);
            }
        }*/
    }
}
