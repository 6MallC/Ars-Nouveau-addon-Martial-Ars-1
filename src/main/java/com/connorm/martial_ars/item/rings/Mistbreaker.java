package com.connorm.martial_ars.item.rings;

import alexthw.ars_elemental.registry.ModPotions;
import com.connorm.martial_ars.api.IMartialRing;
import com.connorm.martial_ars.item.MartialCurio;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class Mistbreaker extends MartialCurio implements IMartialRing {
    public Mistbreaker(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void onEquip(SlotContext context, ItemStack prevStack, ItemStack itemStack) {
        super.onEquip(context, prevStack, itemStack);
        LivingEntity entity = context.entity();
        entity.removeEffect(ModPotions.MIST);
    }


    @Override
    public void appendHoverText(@NotNull ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.martial_ars.mistbreaker.base"));
    }
}
