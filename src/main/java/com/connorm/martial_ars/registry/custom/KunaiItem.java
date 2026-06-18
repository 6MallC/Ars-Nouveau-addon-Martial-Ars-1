package com.connorm.martial_ars.registry.custom;

import com.connorm.martial_ars.entity.custom.KunaiProjectileEntity;
import com.connorm.martial_ars.item.RangeTool;
import com.hollingsworth.arsnouveau.api.item.ICasterTool;
import com.hollingsworth.arsnouveau.api.mana.IManaDiscountEquipment;
import com.hollingsworth.arsnouveau.api.spell.SpellCaster;
import com.hollingsworth.arsnouveau.common.perk.RepairingPerk;
import com.hollingsworth.arsnouveau.setup.registry.DataComponentRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class KunaiItem extends SwordItem implements ICasterTool, RangeTool, IManaDiscountEquipment {
    public KunaiItem(Tier tier, Properties properties) {
        super(tier, properties);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            KunaiProjectileEntity KunaiProjectileEntity = new KunaiProjectileEntity(pPlayer, pLevel);
            KunaiProjectileEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 0F);
            pLevel.addFreshEntity(KunaiProjectileEntity);
        }
    
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild && !pLevel.isClientSide) {
            itemstack.setDamageValue(itemstack.getDamageValue() + Math.max(1, itemstack.getMaxDamage() / 10));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level world, @NotNull Entity entity, int p_77663_4_, boolean p_77663_5_) {
        super.inventoryTick(stack, world, entity, p_77663_4_, p_77663_5_);
        if (entity instanceof Player player)
            RepairingPerk.attemptRepair(stack, player);
    }
}


