package com.connorm.martial_ars.datagen;

import alexthw.ars_elemental.ArsElemental;
import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MELangDatagen  extends LanguageProvider {

    public MELangDatagen(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {


        for (RitualTablet i : RitualRegistry.getRitualItemMap().values()) {
            if (i.ritual.getRegistryName().getNamespace().equals(MartialArs.MODID)) {
                add("martial_ars.ritual_desc." + i.ritual.getRegistryName().getPath(), i.ritual.getLangDescription());
                add("item.martial_ars." + i.ritual.getRegistryName().getPath(), i.ritual.getLangName());
            }
        }
    }
}

