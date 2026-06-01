package com.connorm.martial_ars.registry;

import com.connorm.martial_ars.MartialArs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MartialArs.MODID);

public static final Supplier<CreativeModeTab> Martial_Ars_Main = CREATIVE_MODE_TAB.register("martial_ars_main",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModRegistry.SourceIngot.get()))
                .title(Component.translatable("creativetab.martial_ars.main"))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ModRegistry.SourceIngot);
                    output.accept(ModRegistry.SourceMatrix);
                    output.accept(ModRegistry.sourceingot_block);
                    output.accept(ModRegistry.MushroomWand);
                    output.accept(ModRegistry.EnchantersSpear);
                })
                .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
