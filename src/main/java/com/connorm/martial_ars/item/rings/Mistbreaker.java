package com.connorm.martial_ars.item.rings;

import alexthw.ars_elemental.registry.ModPotions;
import com.connorm.martial_ars.item.MartialCurio;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

import static net.minecraft.world.effect.MobEffects.*;

public class Mistbreaker extends MartialCurio {
    public Mistbreaker(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void curioTick(SlotContext context, ItemStack stack) {
        super.curioTick(context, stack);
        LivingEntity entity = context.entity();
        if (entity instanceof LivingEntity living && living.hasEffect(ModPotions.MIST) && !entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(INVISIBILITY, 60,0,true,false));
            entity.addEffect(new MobEffectInstance(MOVEMENT_SPEED, 60,0,true,false));
            entity.addEffect(new MobEffectInstance(DAMAGE_BOOST, 60,1,true,false));
        }
    }
    @Override
    public void appendHoverText(@NotNull ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.martial_ars.mistbreaker.base"));
    }
}
