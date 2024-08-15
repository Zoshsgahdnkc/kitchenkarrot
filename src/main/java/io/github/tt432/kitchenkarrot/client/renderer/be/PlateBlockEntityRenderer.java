package io.github.tt432.kitchenkarrot.client.renderer.be;

import static io.github.tt432.kitchenkarrot.client.plate.PlateModelRegistry.to;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.block.PlateBlock;
import io.github.tt432.kitchenkarrot.blockentity.PlateBlockEntity;
import io.github.tt432.kitchenkarrot.client.plate.PlateModelRegistry;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.client.RenderTypeHelper;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.items.IItemHandler;

import org.joml.Quaternionf;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author DustW
 **/
public class PlateBlockEntityRenderer implements BlockEntityRenderer<PlateBlockEntity> {

    private final ModelManager modelManager;
    private final ModelBlockRenderer modelRenderer;

    public PlateBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.modelManager =
                context.getBlockRenderDispatcher().getBlockModelShaper().getModelManager();
        this.modelRenderer = context.getBlockRenderDispatcher().getModelRenderer();
    }

    // TODO 重写渲染系统
    @Override
    @ParametersAreNonnullByDefault
    public void render(
            PlateBlockEntity plateBlockEntity,
            float v,
            PoseStack poseStack,
            MultiBufferSource multiBufferSource,
            int packedLight,
            int packedOverlay) {
        if (plateBlockEntity.getLevel() != null) {
            IItemHandler capability =
                    plateBlockEntity
                            .getLevel()
                            .getCapability(
                                    Capabilities.ItemHandler.BLOCK,
                                    plateBlockEntity.getBlockPos(),
                                    null);
            if (capability != null) {
                ItemStack stack = capability.getStackInSlot(0);
                BakedModel model =
                        this.modelManager.getModel(
                                to(
                                        stack.isEmpty()
                                                ? PlateModelRegistry.DEFAULT_NAME
                                                : ResourceLocation.fromNamespaceAndPath(
                                                        Kitchenkarrot.MOD_ID,
                                                        ResourceLocation.parse(
                                                                                stack.getItem()
                                                                                        .toString())
                                                                        .getPath()
                                                                + "_"
                                                                + stack.getCount())));

                poseStack.pushPose();
                BlockState state = plateBlockEntity.getBlockState();
                poseStack.translate(0.5, 0.5, 0.5);
                poseStack.mulPose(
                        new Quaternionf()
                                .rotateY(
                                        -(state.getValue(PlateBlock.DEGREE) - 180)
                                                * (float) Math.PI
                                                / 180));
                poseStack.translate(-0.5, -0.5, -0.5);
                model.getRenderTypes(
                                plateBlockEntity.getBlockState(),
                                RandomSource.create(),
                                ModelData.EMPTY)
                        .forEach(
                                renderType -> {
                                    this.modelRenderer.renderModel(
                                            poseStack.last(),
                                            multiBufferSource.getBuffer(
                                                    RenderTypeHelper.getEntityRenderType(
                                                            renderType, false)),
                                            plateBlockEntity.getBlockState(),
                                            model,
                                            1.0F,
                                            1.0F,
                                            1.0F,
                                            packedLight,
                                            packedOverlay,
                                            ModelData.EMPTY,
                                            renderType);
                                });
                poseStack.popPose();
            }
        }
    }
}
