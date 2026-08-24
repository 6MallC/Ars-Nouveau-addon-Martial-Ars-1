package com.connorm.martial_ars.ritual;

import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.block.ArcanePedestal;
import com.hollingsworth.arsnouveau.common.block.tile.ArcanePedestalTile;
import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.commands.arguments.coordinates.BlockPosArgument.getBlockPos;
import static software.bernie.geckolib.util.ClientUtil.getLevel;

public class RitualRepair extends AbstractRitual {
    @Override
    protected void tick() {
        Level world = getWorld();
        int radius = 1;
        if (world.isClientSide) {
            BlockPos pos = getPos();
            ParticleUtil.spawnRitualAreaEffect(getPos(), getWorld(), rand, getCenterColor(), radius);
        }
        if (!getWorld().isClientSide && world.getGameTime() % 20 == 0) {
            boolean didWork = false;
            Level level = getWorld();
            BlockPos pos = getPos();
            //Get nearby source jars
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-radius, 0, -radius), pos.offset(radius, 0, radius))) {
                if (level.getBlockState(blockPos).getBlock() instanceof ArcanePedestal) {
                    ArcanePedestalTile tile = (ArcanePedestalTile) level.getBlockEntity(blockPos);

                    }
                List<BlockPos> pedestalList;() {
                    return pedestalList(getBlockPos(), radius, getLevel());
                }

                List<ItemStack> getPedestalItems;() {
                    ArrayList<ItemStack> pedestalItems = new ArrayList<>();
                    for (BlockPos blockPos : pedestalList()) {
                        if (level.getBlockEntity(blockPos) instanceof ArcanePedestalTile tile && tile.getStack() != null && !tile.getStack().isEmpty()) {
                            pedestalItems.add(tile.getStack());
                        }
                    }
                    return;
                }
                }
            }
        }

    @Override
    public ResourceLocation getRegistryName() {
        return null;
    }
}
