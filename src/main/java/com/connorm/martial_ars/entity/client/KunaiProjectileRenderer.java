package com.connorm.martial_ars.entity.client;

import com.connorm.martial_ars.entity.custom.KunaiProjectileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class KunaiProjectileRenderer extends EntityRenderer<KunaiProjectileEntity> {
    private KunaiProjectileModel model;


    public KunaiProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new KunaiProjectileModel(context.bakeLayer(KunaiProjectileModel.LAYER_LOCATION));
    }
    @Override
    public void render(KunaiProjectileEntity pEntity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        if(!pEntity.isGrounded()) {
        } else {
            poseStack.mulPose(Axis.YP.rotationDegrees(pEntity.groundedOffset.y));
            poseStack.mulPose(Axis.XP.rotationDegrees(pEntity.groundedOffset.x));
            poseStack.translate(0, 0.0f, 0);
        }

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                buffer, this.model.renderType(this.getTextureLocation(pEntity)),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(pEntity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
    @Override
    public ResourceLocation getTextureLocation(KunaiProjectileEntity KunaiProjectileEntity) {
        return ResourceLocation.withDefaultNamespace("textures/entity/projectiles/arrow.png");
    }
}
