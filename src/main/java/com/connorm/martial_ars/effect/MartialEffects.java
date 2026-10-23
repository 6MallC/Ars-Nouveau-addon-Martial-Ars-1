package com.connorm.martial_ars.effect;

import com.connorm.martial_ars.MartialArs;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.perk.PerkAttributes;
import com.hollingsworth.arsnouveau.common.potions.PublicEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MartialEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
    DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MartialArs.MODID);

    public static final Holder<MobEffect> REPAIR_EFFECT = MOB_EFFECTS.register("repairing",
            () -> new RepairEffect(MobEffectCategory.BENEFICIAL, 20021));
    public static final DeferredHolder<MobEffect, MobEffect> MANA_DECAY_EFFECT = MOB_EFFECTS.register("mana_decay",
            () -> new PublicEffect(MobEffectCategory.HARMFUL, 8080895).addAttributeModifier(PerkAttributes.MANA_REGEN_BONUS, ArsNouveau.prefix("mana_regen_bonus"), -10, AttributeModifier.Operation.ADD_VALUE));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
