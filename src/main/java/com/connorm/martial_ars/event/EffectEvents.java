package com.connorm.martial_ars.event;

import com.connorm.martial_ars.api.IMartialRing;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import static alexthw.ars_elemental.registry.ModPotions.MIST;
import static net.minecraft.world.effect.MobEffects.*;

public class EffectEvents {
    @SubscribeEvent
    public static void mistProtect(MobEffectEvent.Applicable event) {
        event.getEffectInstance();
        LivingEntity entity = event.getEntity();
        if (IMartialRing.hasBreaker(entity.level(), entity) && event.getEffectInstance().getEffect() == MIST && entity instanceof Player){
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
            entity.addEffect(new MobEffectInstance(INVISIBILITY, 60, 0, true, false));
            entity.addEffect(new MobEffectInstance(MOVEMENT_SPEED, 60, 2, true, false));
            entity.addEffect(new MobEffectInstance(DAMAGE_BOOST, 60, 1, true, false));
        }
    }


}
