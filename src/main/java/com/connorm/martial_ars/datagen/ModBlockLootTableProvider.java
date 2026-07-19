package com.connorm.martial_ars.datagen;

import com.connorm.martial_ars.registry.MartialRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider( HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
dropSelf(MartialRegistry.sourceingot_block.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MartialRegistry.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
