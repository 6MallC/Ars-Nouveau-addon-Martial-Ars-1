package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.client.jei.AliasProvider;
import com.hollingsworth.arsnouveau.common.items.RitualTablet;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;
import java.util.TreeMap;

public class MELangDatagen  extends LanguageProvider {

    public MELangDatagen(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add("item.martial_ars.source_ingot", "Magenesium Ingot");
        add("item.martial_ars.source_matrix", "Source Matrix");
        add("block.martial_ars.sourceingot_block", "Block of Magenesium");
        add("item.martial_ars.mushroom_wand", "Mushroom Wand");
        add("creativetab.martial_ars.main", "Martial Ars");
        add("tooltip.martial_ars.mushroom_wand.tooltip", "Turns grass into Mycelium.");
        add("tooltip.martial_ars.mushroom_wand.shift", "Made for a really fungi. Unfortunately there isn't mushroom for the list of errors you have helped me fix. So I will have to spore you the details.");
        add("tooltip.martial_ars.source_ingot.tooltip", "Made from using source to force Iron to resonate with the trace amounts of Magenisium already present in the ingot.");
        add("item.martial_ars.enchanters_spear", "Enchanter's Spear");
        add("item.martial_ars.spear_handle", "Enchanter's Spear Handle");
        add("item.martial_ars.spear_head","Enchanter's Spear Head");
        add("item.martial_ars.enchanters_kunai","Enchanter's Kunai");
        add("item.martial_ars.blank_inlay","Blank Inlay");
        add("item.martial_ars.delay_inlay", "Delay Inlay");
        add("item.martial_ars.luck_inlay", "Luck Inlay");
        add("item.martial_ars.attack_inlay", "Attack Inlay");
        add("item.martial_ars.discount_inlay", "Discount Inlay");
        add("item.martial_ars.kunai_handle", "Enchanter's Kunai Handle");
        add("item.martial_ars.kunai_blade", "Enchanter's Kunai Blade");
        add("martial_ars.spear.invalid", "Hey uh, take the form glyph off your spell and try again.");
        add("item.martial_ars.sword.geomancy", "Earth Sword");
        add("item.martial_ars.sword.aquamancy", "Water Sword");
        add("item.martial_ars.sword.aeromancy", "Air Sword");
        add("item.martial_ars.sword.pyromancy", "Fire Sword");
        add("item.martial_ars.aero.blade", "Air Blade");
        add("item.martial_ars.aero.hilt", "Air Hilt");
        add("item.martial_ars.aqua.blade", "Water Blade");
        add("item.martial_ars.aqua.hilt", "Water Hilt");
        add("item.martial_ars.geo.blade", "Earth Blade");
        add("item.martial_ars.geo.hilt", "Earth Hilt");
        add("item.martial_ars.pyro.blade", "Fire Blade");
        add("item.martial_ars.pyro.hilt", "File Hilt");
        add("item.martial_ars.earth_bagel", "Bane of Earth");
        add("item.martial_ars.air_bagel", "Bane of Air");
        add("item.martial_ars.fire_bagel", "Bane of Fire");
        add("item.martial_ars.water_bagel", "Bane of Water");
        add("effect.martial_ars.repairing", "Repairing");

            for (RitualTablet i : RitualRegistry.getRitualItemMap().values()) {
                if (i.ritual.getRegistryName().getNamespace().equals(MartialArs.MODID)) {
                    add("martial_ars.ritual_desc." + i.ritual.getRegistryName().getPath(), i.ritual.getLangDescription());
                    add("item.martial_ars." + i.ritual.getRegistryName().getPath(), i.ritual.getLangName());
                }
            }
        }
    }


