package com.connorm.martial_ars.entity.client;

import com.connorm.martial_ars.MartialArs;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class KunaiProjectileModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MartialArs.MODID, "kunaiprojectilemodel"), "main");
    private final ModelPart bb_main;

    public KunaiProjectileModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(9, 10).addBox(-0.175F, -8.1F, -4.4799F, 0.35F, 0.2F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(7, 2).addBox(-0.55F, -8.1F, -3.5799F, 1.1F, 0.2F, 2.3F, new CubeDeformation(0.0F))
                .texOffs(7, 1).addBox(-0.9F, -8.1F, -1.2799F, 1.8F, 0.2F, 1.8F, new CubeDeformation(0.0F))
                .texOffs(2, 1).addBox(-0.5F, -8.3F, 1.0F, 1.0F, 0.6F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Outer_r1 = bb_main.addOrReplaceChild("Outer_r1", CubeListBuilder.create().texOffs(0, 1).addBox(-0.8F, -0.4F, -0.8F, 1.6F, 0.8F, 1.6F, new CubeDeformation(0.0F))
                .texOffs(2, 3).addBox(0.4F, -0.4F, -0.4F, -0.8F, 0.8F, 0.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 3.5586F, 0.0F, 0.7854F, 0.0F));

        PartDefinition bladeconnector = bb_main.addOrReplaceChild("blade connector", CubeListBuilder.create().texOffs(0, 5).addBox(-0.7F, -0.7F, -0.7F, 1.5F, 0.4F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, 0.5F, 0.0F, -0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
