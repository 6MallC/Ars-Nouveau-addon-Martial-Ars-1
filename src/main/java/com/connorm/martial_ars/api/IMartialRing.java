package com.connorm.martial_ars.api;

import com.alexthw.sauce.util.CompatUtils;
import com.connorm.martial_ars.item.rings.Mistbreaker;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotResult;

public interface IMartialRing {
    static boolean hasBreaker(Level world, Entity entity) {
        if (!world.isClientSide && entity instanceof Player player) {
            for (SlotResult curio : CompatUtils.getCurios(player, c -> (c.getItem() instanceof Mistbreaker))) {
                if (!curio.stack().isEmpty() && curio.stack().getItem() instanceof Mistbreaker) {
                    return true;
                }
            }
        }
        return false;
    }
}
