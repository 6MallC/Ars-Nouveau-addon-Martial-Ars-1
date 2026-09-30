package com.connorm.martial_ars.item.head;

import com.connorm.martial_ars.item.MartialCurio;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

import static com.hollingsworth.arsnouveau.setup.registry.ModPotions.MAGIC_FIND_EFFECT;

public class MagesShadesItem extends MartialCurio {
    public MagesShadesItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void curioTick(SlotContext context, ItemStack stack){
        LivingEntity wearer = context.entity();
        if (!wearer.level().isClientSide() && wearer instanceof Player player) {
            MobEffectInstance activeEffect = player.getEffect(MAGIC_FIND_EFFECT);
            if (activeEffect == null || activeEffect.getDuration() <= 20) {
                player.addEffect(new MobEffectInstance(MAGIC_FIND_EFFECT, 200,0,true,false));
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.martial_ars.mages_shades.shift"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.martial_ars.mages_shades.base"));
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        }
    }
}
