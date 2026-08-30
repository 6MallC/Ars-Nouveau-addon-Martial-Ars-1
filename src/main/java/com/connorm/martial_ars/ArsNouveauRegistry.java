package com.connorm.martial_ars;

import com.connorm.martial_ars.glyphs.TestEffect;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.connorm.martial_ars.ritual.RitualRepair;
import com.hollingsworth.arsnouveau.api.registry.GlyphRegistry;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.api.registry.SpellSoundRegistry;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;

import java.util.ArrayList;
import java.util.List;

import static alexthw.ars_elemental.ArsNouveauRegistry.registerRituals;

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
        registerRituals();

    }

    private static void registerRituals() {
        register(new RitualRepair());
    }

    private static void register(AbstractRitual ritual) {
        RitualRegistry.registerRitual(ritual);
        RITUALS.add(ritual);
    }
}
