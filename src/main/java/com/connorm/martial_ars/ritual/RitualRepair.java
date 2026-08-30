package com.connorm.martial_ars.ritual;

import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.api.block.IPedestalMachine;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleUtil;
import com.hollingsworth.arsnouveau.common.block.tile.ArcanePedestalTile;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.units.qual.Prefix;

import java.util.ArrayList;
import java.util.List;

import static software.bernie.geckolib.util.ClientUtil.getLevel;

public class RitualRepair extends AbstractRitual implements IPedestalMachine {
    int radius = 1;
    Level level = getWorld();

    public List<BlockPos> pedestalList() {
        return pedestalList(this.getPos(), radius, getLevel());
    }

        List<ItemStack> getPedestalItems() {
            ArrayList<ItemStack> pedestalItems = new ArrayList<>();
            for (BlockPos Stack : pedestalList()) {
                if (level.getBlockEntity(Stack) instanceof ArcanePedestalTile tile && tile.getStack() != null && !tile.getStack().isEmpty()) {
                    pedestalItems.add(tile.getStack());
                }
            }
            return pedestalItems;
    }
    @Override
    protected void tick() {
        Level world = getWorld();

        assert world != null;
        if (world.isClientSide) {
            BlockPos pos = getPos();
            ParticleUtil.spawnRitualAreaEffect(pos, getWorld(), rand, getCenterColor(), radius);
        }
        if (!getWorld().isClientSide && world.getGameTime() % 20 == 0) {

            for (ItemStack I : getPedestalItems()) {
                int damage = I.getDamageValue();
                int repairAmount = Math.min(damage,1 * 20);
                if (damage >= 0) {
                    I.setDamageValue(damage - repairAmount);
                    takeSourceNow();
                }
            }
        }
    }

    @Override
    public int getSourceCost() {
        return 200;
    }

    @Override
    public ResourceLocation getRegistryName() {
        return MartialArs.prefix("ritual_repair");
    }

    @Override
    public String getLangName() {
        return "Ritual of Repairing";
    }
    @Override
    public String getLangDescription(){
        return "Uses a moderate amount of source per second to repair anything with durability" +
                " placed on adjacent arcane pedestals, Unless you augment it with earth and air essence in which case;" +
                " it will instead apply an effect in a wide area that repairs tools in equipment slots" +
                "  (armor slots off hand and main hand) though at halved efficiency." +
                " fire essence can be used to increase throughput consuming more source for more durability per second.";
    }
    public static String ID = "ritual_repair";


    @Override
    public void lightPedestal(Level level) {

    }

}
