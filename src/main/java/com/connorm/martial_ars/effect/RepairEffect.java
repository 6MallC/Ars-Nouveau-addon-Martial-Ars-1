package com.connorm.martial_ars.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class RepairEffect extends MobEffect {
    public RepairEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        if (duration % 20 == 0) {
            return true;
        }
        return super.shouldApplyEffectTickThisTick(duration, amplifier);
    }
    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {

        for (ItemStack i : entity.getAllSlots()) {
            int damage = i.getDamageValue();
            int repairAmount = Math.min(damage,10 + amplifier * 10);
            if (damage >= 0) {
                i.setDamageValue(damage - repairAmount);
            }
        }
        return super.applyEffectTick(entity, amplifier);
    }


}
