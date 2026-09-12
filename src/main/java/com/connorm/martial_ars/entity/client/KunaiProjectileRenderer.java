package com.connorm.martial_ars.entity.client;

import com.connorm.martial_ars.MartialArs;
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

public class KunaiProjectileRenderer extends EntityRenderer<KunaiProjectileEntity> {
    private final KunaiProjectileModel model;


    public KunaiProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new KunaiProjectileModel(context.bakeLayer(KunaiProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(KunaiProjectileEntity pEntity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        if (!pEntity.isGrounded()) {
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, pEntity.yRotO, pEntity.getYRot())));

            VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                    buffer, this.model.renderType(this.getTextureLocation(pEntity)), false, false);
            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
            super.render(pEntity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }
    }
        @Override
        public ResourceLocation getTextureLocation (KunaiProjectileEntity kunaiProjectileEntity){
            return ResourceLocation.fromNamespaceAndPath(MartialArs.MODID, "textures/entity/enchanterskunai/kunaientity.png");
        }
    }
