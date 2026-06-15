package com.connorm.martial_ars.entity;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.entity.custom.KunaiProjectileEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MartialArs.MODID);
    public static final Supplier<EntityType<KunaiProjectileEntity>> KUNAI =
            ENTITY_TYPES.register("kunai",() -> EntityType.Builder.of(KunaiProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f,1.15).build("kunai"));
}
