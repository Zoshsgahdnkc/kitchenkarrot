package io.github.tt432.kitchenkarrot.client.renderer.be;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.tt432.kitchenkarrot.block.CoasterBlock;
import io.github.tt432.kitchenkarrot.blockentity.CoasterBlockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;

import org.joml.Quaternionf;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author DustW
 **/
public class CoasterBlockEntityRenderer implements BlockEntityRenderer<CoasterBlockEntity> {

    // spotless:off
    @Override
    @ParametersAreNonnullByDefault
    public void render(
            CoasterBlockEntity coasterBlockEntity,
            float partialTick,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int packedOverlay) {
        if (coasterBlockEntity.getLevel() != null) {
            IItemHandler capability = coasterBlockEntity.getItem();
            poseStack.pushPose();
            poseStack.translate(0.5F, (float) 4 / 16, 0.5);
            poseStack.scale(1.7F, 1.7F, 1.7F);
            BlockState state = coasterBlockEntity.getBlockState();
            poseStack.mulPose(
                    new Quaternionf()
                            .rotateY(
                                    switch (state.getValue(CoasterBlock.FACING)) {
                                        case EAST -> 90 * (float) Math.PI / 4;
                                        case WEST -> -90 * (float) Math.PI / 4;
                                        case SOUTH -> 180 * (float) Math.PI / 2;
                                        default -> -180 * (float) Math.PI / 4;
                                    }));
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            ItemStack itemStack = capability.getStackInSlot(0);
            itemRenderer.renderStatic(
                    itemStack,
                    ItemDisplayContext.GROUND,
                    LightTexture.FULL_BRIGHT,
                    packedOverlay,
                    poseStack,
                    bufferSource,
                    Minecraft.getInstance().level,
                    BuiltInRegistries.ITEM.getKey(itemStack.getItem()).hashCode());
            poseStack.popPose();
        }
    }
    // spotless:on
}
