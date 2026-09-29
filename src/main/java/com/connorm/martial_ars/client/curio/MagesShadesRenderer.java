package com.connorm.martial_ars.client.curio;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.entity.client.MagesShadesModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class MagesShadesRenderer implements ICurioRenderer {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(MartialArs.prefix("magesshadesentity"),"magesshadesentity");
//private final MagesShadesModel model= new MagesShadesModel(Minecraft.getInstance().getEntityModels().bakeLayer(MagesShadesModel.LAYER_LOCATION));
private final HumanoidModel<LivingEntity> model;

    public MagesShadesRenderer(ModelPart part) {
        this.model = new HumanoidModel<>(part);
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext context, PoseStack matrixStack,
                                                                          RenderLayerParent<T, M> parent, MultiBufferSource renderTypeBuffer,
                                                                          int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {



        matrixStack.pushPose();
        matrixStack.translate(model.head.x / 16.0, model.head.y / 16.0, model.head.z / 16.0);
        matrixStack.mulPose(Axis.ZP.rotation(model.head.zRot));
        matrixStack.mulPose(Axis.YP.rotation(model.head.yRot));
        matrixStack.mulPose(Axis.XP.rotation(model.head.xRot));


        matrixStack.translate(0, -0.1, 0);
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0f));
        matrixStack.scale(2.76328f, 2.99375f, 2.61484f);

        model.renderToBuffer(matrixStack, renderTypeBuffer.getBuffer(RenderType.entityCutoutNoCull(LAYER.getModel())),
                light, OverlayTexture.NO_OVERLAY, 2);
        matrixStack.popPose();
    }
    public static MeshDefinition mesh() {
        CubeListBuilder builder = new CubeListBuilder();
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
        mesh.getRoot().addOrReplaceChild("head", builder, PartPose.ZERO);
        return mesh;
    }
}

