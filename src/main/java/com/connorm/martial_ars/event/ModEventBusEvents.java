package com.connorm.martial_ars.event;

import com.connorm.martial_ars.entity.client.KunaiProjectileModel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import javax.swing.text.html.parser.Entity;

public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerlayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(KunaiProjectileModel.LAYER_LOCATION, KunaiProjectileModel::createBodyLayer);
    }
}
