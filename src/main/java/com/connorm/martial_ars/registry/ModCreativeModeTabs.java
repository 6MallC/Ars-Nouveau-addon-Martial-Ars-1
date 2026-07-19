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
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(MartialRegistry.SourceIngot.get()))
                .title(Component.translatable("creativetab.martial_ars.main"))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(MartialRegistry.SourceIngot);
                    output.accept(MartialRegistry.sourceingot_block);
                    output.accept(MartialRegistry.SourceMatrix);
                    output.accept(MartialRegistry.MushroomWand);
                    output.accept(MartialRegistry.EnchantersSpear);
                    output.accept(MartialRegistry.SpearShaft);
                    output.accept(MartialRegistry.SpearHead);
                    output.accept(MartialRegistry.EnchantersKunai);
                    output.accept(MartialRegistry.KunaiHandle);
                    output.accept(MartialRegistry.KunaiBlade);
                    output.accept(MartialRegistry.GeoSword);
                    output.accept(MartialRegistry.GeoBlade);
                    output.accept(MartialRegistry.GeoHilt);
                    output.accept(MartialRegistry.AeroSword);
                    output.accept(MartialRegistry.AeroBlade);
                    output.accept(MartialRegistry.AeroHilt);
                    output.accept(MartialRegistry.AquaSword);
                    output.accept(MartialRegistry.AquaBlade);
                    output.accept(MartialRegistry.AquaHilt);
                    output.accept(MartialRegistry.PyroSword);
                    output.accept(MartialRegistry.PyroBlade);
                    output.accept(MartialRegistry.PyroHilt);
                    output.accept(MartialRegistry.BlankInlay);
                    output.accept(MartialRegistry.DelayInlay);
                    output.accept(MartialRegistry.LuckInlay);
                    output.accept(MartialRegistry.AttackInlay);
                    output.accept(MartialRegistry.DiscountInlay);

                    // output.accept(ModRegistry.);

                })
                .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
