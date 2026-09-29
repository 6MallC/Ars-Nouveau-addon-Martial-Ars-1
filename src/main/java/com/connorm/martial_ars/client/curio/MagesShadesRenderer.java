package com.connorm.martial_ars.client.curio;

import com.connorm.martial_ars.MartialArs;
import com.connorm.martial_ars.entity.client.MagesShadesModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
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
    private static final ResourceLocation TEXTURE = MartialArs.prefix("textures/entity/mages_shades/magesshadesentity.png");
private final MagesShadesModel model= new MagesShadesModel(Minecraft.getInstance().getEntityModels().bakeLayer(MagesShadesModel.LAYER_LOCATION));


    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext context, PoseStack matrixStack,
                                                                          RenderLayerParent<T, M> parent, MultiBufferSource renderTypeBuffer,
                                                                          int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {


        if (!(parent.getModel() instanceof HumanoidModel<?> humanoid) || context.entity().isInvisible()){
            return;}
        matrixStack.pushPose();
        try {
            model.renderToBuffer(matrixStack, renderTypeBuffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)),
                    light, OverlayTexture.NO_OVERLAY, -1);
        }finally {
            matrixStack.popPose();
        }

        Minecraft mc = Minecraft.getInstance();
        mc.getItemRenderer()
                .renderStatic(stack, ItemDisplayContext.HEAD, light, OverlayTexture.NO_OVERLAY, matrixStack,
                        renderTypeBuffer, mc.level, 0);
        matrixStack.popPose();

    }
}
