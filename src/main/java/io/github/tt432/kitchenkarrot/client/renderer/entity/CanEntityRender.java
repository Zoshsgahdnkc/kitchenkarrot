package io.github.tt432.kitchenkarrot.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.entity.CanEntity;
import io.github.tt432.kitchenkarrot.entity.CanEntityModel;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

public class CanEntityRender extends EntityRenderer<CanEntity> {
    CanEntityModel<?> canEntityModel;

    public CanEntityRender(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
        canEntityModel = new CanEntityModel<>(CanEntityModel.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(
            @NotNull CanEntity canEntity,
            float entityYaw,
            float partialTick,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource bufferSource,
            int packedLight) {
        super.render(canEntity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.pushPose();
        poseStack.translate(0, -1.2, 0);
        this.canEntityModel.renderToBuffer(
                poseStack,
                bufferSource.getBuffer(RenderType.entityCutout(getTextureLocation(canEntity))),
                packedLight,
                OverlayTexture.NO_OVERLAY,
                0xFFFFFF);
        poseStack.popPose();
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(@NotNull CanEntity canEntity) {
        return ResourceLocation.fromNamespaceAndPath(
                Kitchenkarrot.MOD_ID, "textures/entity/can.png");
    }
}
