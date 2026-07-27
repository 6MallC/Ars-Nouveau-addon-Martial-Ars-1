package com.connorm.martial_ars.event;

import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import com.hollingsworth.arsnouveau.api.util.PerkUtil;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
/*
public class AttributeEventHandler {
    @SubscribeEvent
    public static void onPotionAdd(MobEffectEvent.Added event) {
        LivingEntity target = event.getEntity();
        Entity applier = event.getEffectSource();
        if (target.level().isClientSide)
            return;
        double bonus = 0.0;
        Holder<MobEffect> holder = event.getEffectInstance().getEffect();
        MobEffect effect = holder.value();
        if (effect.isBeneficial()) {
            bonus = PerkUtil.valueOrZero(target, PerkAttributes.WIXIE);
        } else if (applier instanceof LivingEntity living) {
            bonus = PerkUtil.valueOrZero(living, PerkAttributes.WIXIE);
        }

        if (bonus > 0.0) {
            event.getEffectInstance().duration = (int) (event.getEffectInstance().getDuration() * bonus);
        }

    }
}
*/