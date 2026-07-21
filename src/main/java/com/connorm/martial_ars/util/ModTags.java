package com.connorm.martial_ars.util;

import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.common.lib.PotionEffectTags;
import com.hollingsworth.arsnouveau.common.network.PacketANEffect;
import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

public class ModTags {
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
        public static final TagKey<MobEffect> ANIMA_EFFECTS = TagKey.create(Registries.MOB_EFFECT, MartialArs.prefix("anima_effects"));
        private static final HashMap<TagKey<MobEffect>, ArrayList<Holder<MobEffect>>> potionEffects = new HashMap<>();
        }
    }

