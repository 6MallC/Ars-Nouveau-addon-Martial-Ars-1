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
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "kunaiprojectilemodel"), "main");
    private final ModelPart blade;
    private final ModelPart handle;

    public KunaiProjectileModel(ModelPart root) {
        this.blade = root.getChild("blade");
        this.handle = root.getChild("handle");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition blade = partdefinition.addOrReplaceChild("blade", CubeListBuilder.create().texOffs(9, 9).addBox(-0.175F, 0.0F, -2.9F, 0.35F, 0.2F, 0.9F, new CubeDeformation(0.0F))
                .texOffs(7, 1).addBox(-0.55F, 0.0F, -2.0F, 1.1F, 0.2F, 2.3F, new CubeDeformation(0.0F))
                .texOffs(6, 4).addBox(-0.9F, 0.0F, 0.3F, 1.8F, 0.2F, 1.8F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.1F, -1.5799F));

        PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(2, 1).addBox(-0.5F, 0.2F, 0.5F, 1.0F, 0.6F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.5F));

        PartDefinition Outer_r1 = handle.addOrReplaceChild("Outer_r1", CubeListBuilder.create().texOffs(2, 2).addBox(-0.8F, -0.4F, -0.8F, 1.6F, 0.8F, 1.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 3.0586F, 0.0F, 0.7854F, 0.0F));

        PartDefinition connector_r1 = handle.addOrReplaceChild("connector_r1", CubeListBuilder.create().texOffs(0, 4).addBox(-0.7F, 0.3F, -0.7F, 1.5F, 0.4F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        blade.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        handle.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}