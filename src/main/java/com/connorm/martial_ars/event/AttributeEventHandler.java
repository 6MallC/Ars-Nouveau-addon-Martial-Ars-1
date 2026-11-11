package com.connorm.martial_ars.event;

import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.api.util.PerkUtil;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

public class AttributeEventHandler {
    @SubscribeEvent
    public static void onPotionAdd(MobEffectEvent.Added event) {
        LivingEntity target = event.getEntity();
        if (target.level.isClientSide)
            return;
        double bonus = 0.0;
        Holder<MobEffect> holder = event.getEffectInstance().getEffect();
        MobEffect effect = holder.value();
        if (!effect.isBeneficial()) {
            bonus = PerkUtil.valueOrZero(target, MartialRegistry.AilmentDuration);
        }
        if (bonus > 0.0) {
            event.getEffectInstance().duration = (int) (event.getEffectInstance().duration * bonus);
        }
    }
}