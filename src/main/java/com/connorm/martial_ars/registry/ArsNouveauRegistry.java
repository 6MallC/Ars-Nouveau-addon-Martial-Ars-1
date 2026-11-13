package com.connorm.martial_ars.registry;

import com.connorm.martial_ars.glyphs.TestEffect;
import com.connorm.martial_ars.perk.*;
import com.connorm.martial_ars.ritual.RitualRepair;
import com.hollingsworth.arsnouveau.api.registry.GlyphRegistry;
import com.hollingsworth.arsnouveau.api.registry.PerkRegistry;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.api.registry.SpellSoundRegistry;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;

import java.util.ArrayList;
import java.util.List;

public class ArsNouveauRegistry {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>(); //this will come handy for datagen

    public static void registerGlyphs(){
        register(TestEffect.INSTANCE);
    }
    public static void registerSounds(){
        SpellSoundRegistry.registerSpellSound(MartialRegistry.EXAMPLE_SPELL_SOUND);
    }
    public static void register(AbstractSpellPart spellPart){
        GlyphRegistry.registerSpell(spellPart);
        registeredSpells.add(spellPart);


    }
    public static List<AbstractRitual> RITUALS = new ArrayList<>();

    public static void init() {
        // apiregistry is where these are stored
        registerRituals();
        registerPerks();

    }

    private static void registerRituals() {
        register(new RitualRepair());
    }

    private static void register(AbstractRitual ritual) {
        RitualRegistry.registerRitual(ritual);
        RITUALS.add(ritual);
    }
    public static void registerPerks() {
        PerkRegistry.registerPerk(OriginRegenPerk.INSTANCE);
        PerkRegistry.registerPerk(OriginPowerPerk.INSTANCE);
        PerkRegistry.registerPerk(OriginBunclePerk.INSTANCE);
        PerkRegistry.registerPerk(OriginGolemPerk.INSTANCE);
        PerkRegistry.registerPerk(OriginWixiePerk.INSTANCE);
        PerkRegistry.registerPerk(OriginWardingPerk.INSTANCE);
        PerkRegistry.registerPerk(OriginWhirliPerk.INSTANCE);
    }
}
