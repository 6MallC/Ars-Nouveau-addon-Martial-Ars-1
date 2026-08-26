package com.connorm.martial_ars.effect;

import com.connorm.martial_ars.MartialArs;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MartialEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
    DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MartialArs.MODID);

    public static final Holder<MobEffect> REPAIR_EFFECT = MOB_EFFECTS.register("repairing",
            () -> new RepairEffect(MobEffectCategory.BENEFICIAL, 20021));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
