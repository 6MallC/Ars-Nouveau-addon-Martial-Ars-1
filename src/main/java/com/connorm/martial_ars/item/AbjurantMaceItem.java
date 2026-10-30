package com.connorm.martial_ars.item;

import com.hollingsworth.arsnouveau.api.item.ICasterTool;
import com.hollingsworth.arsnouveau.api.mana.IManaDiscountEquipment;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.api.spell.wrapped_caster.IWrappedCaster;
import com.hollingsworth.arsnouveau.api.spell.wrapped_caster.LivingCaster;
import com.hollingsworth.arsnouveau.api.spell.wrapped_caster.PlayerCaster;
import com.hollingsworth.arsnouveau.client.gui.SpellTooltip;
import com.hollingsworth.arsnouveau.common.perk.RepairingPerk;
import com.hollingsworth.arsnouveau.common.spell.method.MethodTouch;
import com.hollingsworth.arsnouveau.common.util.PortUtil;
import com.hollingsworth.arsnouveau.setup.config.Config;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Optional;

public class AbjurantMaceItem extends MaceItem implements ICasterTool, IManaDiscountEquipment {
    public AbjurantMaceItem(Properties properties) {
        super(properties);
    }
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof ServerPlayer serverplayer) {
            AbstractCaster<?> caster = getSpellCaster(stack);
            IWrappedCaster wrappedCaster = attacker instanceof  Player player ? new PlayerCaster(player) : new LivingCaster(attacker);
            SpellContext context = new SpellContext(attacker.level(), caster.modifySpellBeforeCasting((ServerLevel) target.level(), attacker, InteractionHand.MAIN_HAND, caster.getSpell()), attacker, wrappedCaster, stack);
            SpellResolver resolver = attacker instanceof Player ? new SpellResolver(context) : new EntitySpellResolver(context);
            EntityHitResult entityRes = new EntityHitResult(target);
            resolver.onCastOnEntity(stack, entityRes.getEntity(), InteractionHand.MAIN_HAND);
            if (canSmashAttack(serverplayer)) {
                ServerLevel serverlevel = (ServerLevel)attacker.level();
                if (serverplayer.isIgnoringFallDamageFromCurrentImpulse() && serverplayer.currentImpulseImpactPos != null) {
                    if (serverplayer.currentImpulseImpactPos.y > serverplayer.position().y) {
                        serverplayer.currentImpulseImpactPos = serverplayer.position();
                    }
                } else {
                    serverplayer.currentImpulseImpactPos = serverplayer.position();
                }

                serverplayer.setIgnoreFallDamageFromCurrentImpulse(true);
                serverplayer.setDeltaMovement(serverplayer.getDeltaMovement().with(Direction.Axis.Y, (double)0.01F));
                serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));
                if (target.onGround()) {
                    serverplayer.setSpawnExtraParticlesOnFall(true);
                    SoundEvent soundevent = serverplayer.fallDistance > 5.0F ? SoundEvents.MACE_SMASH_GROUND_HEAVY : SoundEvents.MACE_SMASH_GROUND;
                    serverlevel.playSound((Player)null, serverplayer.getX(), serverplayer.getY(), serverplayer.getZ(), soundevent, serverplayer.getSoundSource(), 1.0F, 1.0F);
                } else {
                    serverlevel.playSound((Player)null, serverplayer.getX(), serverplayer.getY(), serverplayer.getZ(), SoundEvents.MACE_SMASH_AIR, serverplayer.getSoundSource(), 1.0F, 1.0F);
                }

                knockback(serverlevel, serverplayer, target);
            }
        }

        return true;
    }





    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level world, @NotNull Entity entity, int p_77663_4_, boolean p_77663_5_) {
        super.inventoryTick(stack, world, entity, p_77663_4_, p_77663_5_);
        if (entity instanceof Player player)
            RepairingPerk.attemptRepair(stack, player);
    }
    @Override
    public boolean isScribedSpellValid(AbstractCaster<?> caster, Player player, InteractionHand hand, ItemStack stack, Spell spell) {
        return spell.unsafeList().stream().noneMatch(s -> s instanceof AbstractCastMethod);
    }
    @Override
    public void sendInvalidMessage(Player player) {
        PortUtil.sendMessageNoSpam(player, Component.translatable("martial_ars.spear.invalid"));
    }
    @Override
    public void scribeModifiedSpell(AbstractCaster<?> caster, Player player, InteractionHand hand, ItemStack stack, Spell.Mutable spell) {
        ArrayList<AbstractSpellPart> recipe = new ArrayList<>();
        recipe.add(MethodTouch.INSTANCE);
        recipe.addAll(spell.recipe);
        spell.recipe = recipe;
    }
    @Override
    public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pStack) {
        AbstractCaster<?> caster = getSpellCaster(pStack);
        if (caster != null && !Screen.hasShiftDown() && Config.GLYPH_TOOLTIPS.get() && !caster.isSpellHidden() && !caster.getSpell().isEmpty())
            return Optional.of(new SpellTooltip(caster));
        return Optional.empty();
    }
}
