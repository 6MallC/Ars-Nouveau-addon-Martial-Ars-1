package com.connorm.martial_ars.item;

import com.connorm.martial_ars.component.BladeCasterTomeData;
import com.connorm.martial_ars.component.ModDataComponents;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.api.spell.wrapped_caster.IWrappedCaster;
import com.hollingsworth.arsnouveau.api.spell.wrapped_caster.LivingCaster;
import com.hollingsworth.arsnouveau.api.spell.wrapped_caster.PlayerCaster;
import com.hollingsworth.arsnouveau.client.gui.SpellTooltip;
import com.hollingsworth.arsnouveau.common.items.CasterTome;
import com.hollingsworth.arsnouveau.setup.config.Config;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BladeCasterTomeItem extends CasterTome {
    public BladeCasterTomeItem(Properties properties) {
        super(properties.component(ModDataComponents.BLADE_TOME_CASTER, new BladeCasterTomeData()));
    }
    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity entity) {
        AbstractCaster<?> caster = getSpellCaster(stack);
        IWrappedCaster wrappedCaster = entity instanceof  Player player ? new PlayerCaster(player) : new LivingCaster(entity);
        SpellContext context = new SpellContext(entity.level(), caster.modifySpellBeforeCasting((ServerLevel) target.level(), entity, InteractionHand.MAIN_HAND, caster.getSpell()), entity, wrappedCaster, stack);
        SpellResolver resolver = entity instanceof Player ? new SpellResolver(context) : new EntitySpellResolver(context);
        EntityHitResult entityRes = new EntityHitResult(target);
        resolver.onCastOnEntity(stack, entityRes.getEntity(), InteractionHand.MAIN_HAND);
        return super.hurtEnemy(stack, target, entity);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pStack) {
        AbstractCaster<?> caster = getSpellCaster(pStack);
        if (caster != null && !Screen.hasShiftDown() && Config.GLYPH_TOOLTIPS.get() && !caster.isSpellHidden() && !caster.getSpell().isEmpty())
            return Optional.of(new SpellTooltip(caster));
        return Optional.empty();
    }
}
