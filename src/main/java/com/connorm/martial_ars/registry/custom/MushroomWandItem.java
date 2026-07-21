package com.connorm.martial_ars.registry.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.*;

public class MushroomWandItem extends Item {

    private static final Map<Block, List<Block>> BLOCK_MAP = Map.of(
            Blocks.GRASS_BLOCK, Arrays.asList(Blocks.MYCELIUM)
    );

    private static final Map<ItemTags, List<Block>> TAG_MAP = Map.of(
            ItemTags.LOGS, Arrays.asList(Blocks.MUSHROOM_STEM)
    );

    public MushroomWandItem(Properties properties) {
        super(properties);
    }

    private Block getRandomBlock(List<Block> blocks, Random random) {
        return blocks.get(random.nextInt(blocks.size());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        Optional.ofNullable(BLOCK_MAP.get(clickedBlock))
                .map((conversions) -> {
                    Block target = this.getRandomBlock(conversions, (Random) level.random);
                    doThingWithTarget(target);
                    return InteractionResult.SUCCESS;
                })
                .orElseGet(() => {
                for (Map.Entry<ItemTags, List<Block>> entry : TAG_MAP.entrySet()) {
            if (clickedBlock.is(entry.getKey())) {
                Block target = this.getRandomBlock(entry.getValue(), (Random) level.random);
                doThingWithTarget(target);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    };
    }





    /*private static final Map<Block, Block> CONVERSION_MAP =
            Map.of(
                    Blocks.GRASS_BLOCK, Blocks.MYCELIUM,
                    Blocks.SHORT_GRASS, Blocks.BROWN_MUSHROOM,
                    Blocks.TALL_GRASS, Blocks.RED_MUSHROOM
            );

    public MushroomWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
if (CONVERSION_MAP.containsKey(clickedBlock)) {
    if (!level.isClientSide()) {
        level.setBlockAndUpdate(context.getClickedPos(), CONVERSION_MAP.get(clickedBlock).defaultBlockState());
        level.playSound(null,context.getClickedPos(), SoundEvents.GRASS_STEP, SoundSource.BLOCKS);
    }
}
        return InteractionResult.SUCCESS;
    }
*/
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.martial_ars.mushroom_wand.shift"));
        } else {
        tooltipComponents.add(Component.translatable("tooltip.martial_ars.mushroom_wand.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        }
    }
}
