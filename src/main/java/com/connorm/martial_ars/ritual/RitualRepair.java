package com.connorm.martial_ars.ritual;

import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.block.ArcanePedestal;
import com.hollingsworth.arsnouveau.common.block.tile.ArcanePedestalTile;
import com.hollingsworth.arsnouveau.common.datagen.ItemTagProvider;
import com.hollingsworth.arsnouveau.common.items.FireEssence;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class RitualRepair extends AbstractRitual  {
    int radius = 1;
    public int amp = 1;



 @Override
    protected void tick() {
        Level world = getWorld();

        if (world.isClientSide) {
            BlockPos pos = getPos();
            assert pos != null;
            ParticleUtil.spawnRitualAreaEffect(pos, getWorld(), rand, getCenterColor(), radius);
        }
        if (!getWorld().isClientSide && world.getGameTime() % 20 == 0) {

            ArrayList<ItemStack> posList = new ArrayList<>();
            for (BlockPos blockPos : BlockPos.betweenClosed(getPos().offset(-radius, 0, -radius), getPos().offset(radius, 0, radius))) {
                if (world.getBlockState(blockPos).getBlock() instanceof ArcanePedestal) {
                    ArcanePedestalTile tile = (ArcanePedestalTile) world.getBlockEntity(blockPos);
                    // (the good practice is using an ItemStack.EMPTY as placeholder/default)
                    if (tile == null || tile.isEmpty()) {continue;}
                    posList.add(tile.getStack());


                    for (ItemStack I : posList) {
                        int damage = I.getDamageValue();
                        int repairAmount = Math.min(damage,amp * 20);
                        if (damage > 0) {
                             I.setDamageValue(damage - repairAmount);
                             takeSourceNow();
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getSourceCost() {
        return 200;
    }

    @Override
    public boolean canConsumeItem(ItemStack stack) {
        return stack.getItem() instanceof FireEssence && itemConsumedCount(i -> i.getItem() == ItemsRegistry.FIRE_ESSENCE) <= 10 ;
    }
    @Override
    public ResourceLocation getRegistryName() {
        return MartialArs.prefix("ritual_repair");
    }

    @Override
    public String getLangName() {
        return "Repairing";
    }
    @Override
    public String getLangDescription(){
        return "Uses a moderate amount of source per second to repair anything with durability" +
                " placed on adjacent arcane pedestals, Unless you augment it with earth and air essence in which case" +
                " it will instead apply an effect in a wide area that repairs tools in equipment slots" +
                "  (armor slots off hand and main hand) though at halved efficiency." +
                " fire essence can be used to increase throughput consuming more source for more durability per second.";
    }




}

