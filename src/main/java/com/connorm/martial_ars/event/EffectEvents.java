package com.connorm.martial_ars.event;

import com.connorm.martial_ars.effect.MartialEffects;
import com.connorm.martial_ars.registry.MartialRegistry;
import com.hollingsworth.arsnouveau.api.util.CuriosUtil;
import com.hollingsworth.arsnouveau.api.util.DamageUtil;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import static alexthw.ars_elemental.registry.ModPotions.MIST;
import static net.minecraft.world.effect.MobEffects.*;

public class EffectEvents {
    @SubscribeEvent
    public static void mistProtect(MobEffectEvent.Applicable event) {
        event.getEffectInstance();
        LivingEntity entity = event.getEntity();
        if (CuriosUtil.hasItem(entity, MartialRegistry.MISTBREAKER.get()) && event.getEffectInstance().getEffect() == MIST && entity instanceof Player){
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
            entity.addEffect(new MobEffectInstance(INVISIBILITY, 60, 0, true, false));
            entity.addEffect(new MobEffectInstance(MOVEMENT_SPEED, 60, 2, true, false));
            entity.addEffect(new MobEffectInstance(DAMAGE_BOOST, 60, 1, true, false));
        }
    }
    @SubscribeEvent
public static void fallVulnerability(LivingIncomingDamageEvent event){
    LivingEntity living = event.getEntity();
    DamageSource source = event.getSource();
        if (living.hasEffect(MartialEffects.WEAK_KNEES_EFFECT) && source.is(DamageTypeTags.IS_FALL)) {
            DamageUtil.SpellDamageSource newSource = (DamageUtil.SpellDamageSource) DamageUtil.source(living.level(), MartialRegistry.EVILFALL, source.getEntity());
            if (source instanceof DamageUtil.SpellDamageSource oldspellDamageSource) {
                newSource.setLuckLevel(oldspellDamageSource.getLuckLevel());
                }
                source = newSource;
            }
        }
    }
