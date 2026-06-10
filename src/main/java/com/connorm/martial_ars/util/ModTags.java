package com.connorm.martial_ars.util;

import com.connorm.martial_ars.MartialArs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

    public static final TagKey<Block> Needs_Martial_Tool = createTag("needs_martial_tool");
        public static final TagKey<Block> Incorrect_For_Martial_Tool = createTag("incorrect_for_martial_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MartialArs.MODID, name));

        }
    }
    public static class Items{
        public static final TagKey<Item> Weapon_Handles = createTag("weapon_handles");
        public static final TagKey<Item> Weapon_Heads = createTag("weapon_heads");
        public static final TagKey<Item> Inlays = createTag("inlays");
        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MartialArs.MODID, name));

            }
        }

    }

