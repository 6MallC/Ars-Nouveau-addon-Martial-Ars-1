package com.connorm.martial_ars.block.entity;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.registry.MartialRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MartialBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MartialArs.MODID);

    public static final Supplier<BlockEntityType<ArcaneForgeEntity>> FORGE_BE =
            BLOCK_ENTITIES.register("forge_be", () -> BlockEntityType.Builder.of(
                    ArcaneForgeEntity::new, MartialRegistry.ARCANEFORGE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
