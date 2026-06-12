package com.connorm.martial_ars.component;

import com.connorm.martial_ars.MartialArs;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.print.attribute.Attribute;
import java.util.function.UnaryOperator;
import java.util.jar.Attributes;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE,   MartialArs.MODID);

    // public static final DeferredHolder<DataComponentType<?>, DataComponentType<Attribute>> Attack_Range = register("attack_range", attributeBuilder -> attributeBuilder.persistent());

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                          UnaryOperator<DataComponentType.Builder<T>> builderOperator){
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register (IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
