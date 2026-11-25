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
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MartialEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
    DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MartialArs.MODID);

    // Special
    public static final Holder<MobEffect> REPAIR_EFFECT = MOB_EFFECTS.register("repairing",
            () -> new RepairEffect(MobEffectCategory.BENEFICIAL, 20021));
    public static final Holder<MobEffect> WEAK_KNEES_EFFECT = MOB_EFFECTS.register("fall_vulnerability",
            () -> new PublicEffect(MobEffectCategory.HARMFUL, 20021).addAttributeModifier(Attributes.SAFE_FALL_DISTANCE, ArsNouveau.prefix("safe_fall_distance  "), 0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));;
    public static final Holder<MobEffect> CONTAMINATE_EFFECT = MOB_EFFECTS.register("contaminate_effect",
            () -> new PublicEffect(MobEffectCategory.BENEFICIAL, 20021));
    public static final Holder<MobEffect> EVAPORATE_PEFFECT = MOB_EFFECTS.register("evaporate_effect",
            () -> new PublicEffect(MobEffectCategory.BENEFICIAL, 20021));
    public static final Holder<MobEffect> ELECTROCUTE_EFFECT = MOB_EFFECTS.register("electrocute_effect",
            () -> new PublicEffect(MobEffectCategory.BENEFICIAL, 20021));


    // Attribute Mods
    public static final DeferredHolder<MobEffect, MobEffect> MANA_DECAY_EFFECT = MOB_EFFECTS.register("mana_decay",
            () -> new PublicEffect(MobEffectCategory.HARMFUL, 8080895).addAttributeModifier(PerkAttributes.MANA_REGEN_BONUS, ArsNouveau.prefix("mana_regen_bonus"), -10, AttributeModifier.Operation.ADD_VALUE));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
