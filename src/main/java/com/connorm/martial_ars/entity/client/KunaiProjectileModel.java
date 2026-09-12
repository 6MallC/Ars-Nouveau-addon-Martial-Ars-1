package com.connorm.martial_ars.entity.client;// Made with Blockbench 5.1.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.entity.custom.KunaiProjectileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class KunaiProjectileModel extends EntityModel<KunaiProjectileEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MartialArs.MODID, "kunaientity"), "selection");
	private final ModelPart bb_main;

	public KunaiProjectileModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Outer_r1 = bb_main.addOrReplaceChild("Outer_r1", CubeListBuilder.create().texOffs(12, 5).addBox(-5.8374F, -1.0F, 1.863F, 4.0F, 2.0F, 4.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(-0.125F, -0.2086F, -0.05F, -3.1416F, -0.7854F, 0.0F));

        PartDefinition Handle_r1 = bb_main.addOrReplaceChild("Handle_r1", CubeListBuilder.create().texOffs(12, 0).addBox(-1.0081F, -0.5F, 0.8049F, 2.0F, 1.0F, 4.0F, new CubeDeformation(-0.75F))
                .texOffs(0, 12).addBox(-2.5081F, -0.5F, -2.4451F, 5.0F, 1.0F, 4.0F, new CubeDeformation(-0.75F))
                .texOffs(0, 6).addBox(-1.5081F, -0.5F, -5.9451F, 3.0F, 1.0F, 5.0F, new CubeDeformation(-0.75F))
                .texOffs(0, 8).addBox(-0.5081F, -0.5F, -6.4451F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(-0.125F, -0.2086F, -0.05F, -3.1416F, 0.0F, 0.0F));

        PartDefinition connector_r1 = bb_main.addOrReplaceChild("connector_r1", CubeListBuilder.create().texOffs(0, 1).addBox(-1.3733F, -0.5F, -1.3477F, 4.0F, 1.0F, 4.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(-0.125F, -0.2086F, -0.05F, -3.1416F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

        @Override
        public void setupAnim(KunaiProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        }

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}