// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
/*

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class EnchantersKunaiEntity extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "enchanter's_kunai_gold_palette_2_(1)_converted"), "main");
	private final ModelPart Ring;
	private final ModelPart bone;

	public Enchanters_Kunai_Gold_Palette_2_(1)_Converted(ModelPart root) {
		this.Ring = root.getChild("Ring");
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Ring = partdefinition.addOrReplaceChild("Ring", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 3.0F));

		PartDefinition Inner_r1 = Ring.addOrReplaceChild("Inner_r1", CubeListBuilder.create().texOffs(1, 2).addBox(0.4F, -0.4F, -0.4F, -0.8F, 0.8F, 0.8F, new CubeDeformation(0.0F))
		.texOffs(1, 2).addBox(-0.8F, -0.4F, -0.8F, 1.6F, 0.8F, 1.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -0.4414F, 0.0F, 0.7854F, 0.0F));

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(4, 4).addBox(-8.5F, -8.3F, 8.0F, 1.0F, 0.6F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(7, 2).addBox(-8.9F, -8.1F, 6.7201F, 1.8F, 0.2F, 1.8F, new CubeDeformation(0.0F))
		.texOffs(8, 2).addBox(-8.55F, -8.1F, 4.4201F, 1.1F, 0.2F, 2.3F, new CubeDeformation(0.0F))
		.texOffs(9, 8).addBox(-8.175F, -8.1F, 3.5201F, 0.35F, 0.2F, 0.9F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

		PartDefinition Weird blade connector thing?_r1 = bone.addOrReplaceChild("Weird blade connector thing?_r1", CubeListBuilder.create().texOffs(0, 5).addBox(-0.7F, -0.7F, -0.7F, 1.5F, 0.4F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -7.5F, 8.5F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Ring.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}

    */