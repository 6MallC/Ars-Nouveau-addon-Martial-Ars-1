package com.connorm.martial_ars;

import com.connorm.martial_ars.component.ModDataComponents;
import com.connorm.martial_ars.effect.MartialEffects;
import com.connorm.martial_ars.entity.ModEntities;
import com.connorm.martial_ars.entity.client.KunaiProjectileModel;
import com.connorm.martial_ars.entity.client.KunaiProjectileRenderer;
import com.connorm.martial_ars.event.AttributeEventHandler;
import com.connorm.martial_ars.registry.ModCreativeModeTabs;
import com.connorm.martial_ars.registry.MartialRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MartialArs.MODID)
public class MartialArs {
    public static final String MODID = "martial_ars";

    private static final Logger LOGGER = LogManager.getLogger();

    public MartialArs(IEventBus modEventBus, ModContainer modContainer) {
        ArsNouveauRegistry.registerGlyphs();
        ArsNouveauRegistry.init();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(AttributeEventHandler.class);
        ModCreativeModeTabs.register(modEventBus);
        MartialRegistry.registerRegistries(modEventBus);
        modEventBus.register(MartialRegistry.class);
        ModDataComponents.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        MartialEffects.register(modEventBus);

    }


    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ArsNouveauRegistry.registerSounds();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }


        // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            EntityRenderers.register(ModEntities.KUNAI.get(), KunaiProjectileRenderer::new);
        }
        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(KunaiProjectileModel.LAYER_LOCATION, KunaiProjectileModel::createBodyLayer);
        }
}
}
