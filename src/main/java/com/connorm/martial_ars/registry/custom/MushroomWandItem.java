package com.connorm.martial_ars.registry.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class MushroomWandItem extends Item {
    private static final Map<Block, Block> CONVERSION_MAP =
            Map.of(
                    Blocks.GRASS_BLOCK, Blocks.MYCELIUM
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

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.martial_ars.mushroom_wand.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
