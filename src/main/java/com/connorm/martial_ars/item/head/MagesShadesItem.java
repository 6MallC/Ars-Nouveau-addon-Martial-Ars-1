package com.connorm.martial_ars.item.head;

import com.connorm.martial_ars.item.MartialCurio;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

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
                player.addEffect(new MobEffectInstance(MAGIC_FIND_EFFECT, 60,0,true,false));
            }
        }
    }
}
