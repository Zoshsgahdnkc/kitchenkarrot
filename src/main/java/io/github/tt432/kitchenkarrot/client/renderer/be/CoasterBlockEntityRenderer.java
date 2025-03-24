package io.github.tt432.kitchenkarrot.client.renderer.be;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.tt432.kitchenkarrot.block.CoasterBlock;
import io.github.tt432.kitchenkarrot.blockentity.CoasterBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Quaternionf;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author DustW
 **/
public class CoasterBlockEntityRenderer implements BlockEntityRenderer<CoasterBlockEntity> {

    @Override
    @ParametersAreNonnullByDefault
    public void render(CoasterBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            poseStack.pushPose();
            poseStack.translate(0.5, 4 / 16., 0.5);
            poseStack.scale(1.7f, 1.7f, 1.7f);
            BlockState state = blockEntity.getBlockState();
            poseStack.mulPose(new Quaternionf().rotateY(
                    switch (state.getValue(CoasterBlock.FACING)) {
                        case EAST -> 90 * (float) Math.PI / 4;
                        case WEST -> -90 * (float) Math.PI / 4;
                        case SOUTH -> 180 * (float) Math.PI / 2;
                        default -> -180 * (float) Math.PI / 4;
                    }
            ));
//            poseStack.mulPose(Vector3f.YP.rotationDegrees(
//                switch (state.getValue(CoasterBlock.FACING)) {
//                    case EAST -> 90;
//                    case WEST -> -90;
//                    case SOUTH -> 180;
//                    default -> 0;
//                }
//            ));

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            ItemStack item = handler.getStackInSlot(0);
            itemRenderer.renderStatic(item, ItemDisplayContext.GROUND,
                    getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()), packedOverlay,
                    poseStack, bufferSource, Minecraft.getInstance().level, ForgeRegistries.ITEMS.getKey(item.getItem()).hashCode());
//            itemRenderer.renderStatic(item, ItemTransforms.TransformType.GROUND,
//                    LightTexture.FULL_BRIGHT, packedOverlay,
//                    poseStack, bufferSource, ForgeRegistries.ITEMS.getKey(item.getItem()).hashCode());

            poseStack.popPose();
        });
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int block = level.getBrightness(LightLayer.BLOCK, pos);
        int sky = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(block, sky);
    }

}