package com.connorm.martial_ars.util;

import alexthw.ars_elemental.common.items.bangles.BaseBangle;
import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.spell.SpellSchools;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MartialTags {
    public static class Blocks {

        public static final TagKey<Block> Needs_Martial_Tool = createTag("needs_martial_tool");
        public static final TagKey<Block> Incorrect_For_Martial_Tool = createTag("incorrect_for_martial_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MartialArs.MODID, name));

        }
    }

    public static class Items {
        public static final TagKey<Item> Weapon_Handles = createTag("weapon_handles");
        public static final TagKey<Item> Weapon_Heads = createTag("weapon_heads");
        public static final TagKey<Item> Inlays = createTag("inlays");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MartialArs.MODID, name));

        }
    }

    public static class Effects {
        public static final TagKey<MobEffect> EARTH_EFFECTS = TagKey.create(Registries.MOB_EFFECT, MartialArs.prefix("earth_effects"));
        public static final TagKey<MobEffect> AQUA_EFFECTS = TagKey.create(Registries.MOB_EFFECT, MartialArs.prefix("aqua_effects"));
        public static final TagKey<MobEffect> AIR_EFFECTS = TagKey.create(Registries.MOB_EFFECT, MartialArs.prefix("air_effects"));
        public static final TagKey<MobEffect> FIRE_EFFECTS = TagKey.create(Registries.MOB_EFFECT, MartialArs.prefix("fire_effects"));

        public static final TagKey<MobEffect> CONJURATION_EFFECTS = TagKey.create(Registries.MOB_EFFECT, MartialArs.prefix("conjuration_effects"));
        public static final TagKey<MobEffect> ABJURATION_EFFECTS = TagKey.create(Registries.MOB_EFFECT, MartialArs.prefix("abjuration_effects"));
        public static final TagKey<MobEffect> MANIPULATION_EFFECTS = TagKey.create(Registries.MOB_EFFECT, MartialArs.prefix("manipulation_effects"));
        public static final TagKey<MobEffect> ANIMA_EFFECTS = TagKey.create(Registries.MOB_EFFECT, MartialArs.prefix("anima_effects"));

        public static final Map<SpellSchool, List<TagKey<MobEffect>>> SCHOOL_TO_EFFECT_TYPES = new ConcurrentHashMap<>(Map.of(
                SpellSchools.ELEMENTAL_FIRE, List.of(FIRE_EFFECTS),
                SpellSchools.ELEMENTAL_EARTH, List.of(EARTH_EFFECTS),
                SpellSchools.ELEMENTAL_WATER, List.of(AQUA_EFFECTS),
                SpellSchools.ELEMENTAL_AIR, List.of(AIR_EFFECTS),
                SpellSchools.ELEMENTAL, List.of(FIRE_EFFECTS, AQUA_EFFECTS, EARTH_EFFECTS, AIR_EFFECTS)
        ));
        public static class Curios {

        }
    }

    }

