package io.github.tt432.kitchenkarrot.client;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailBakedModel;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailTextureManager;
import io.github.tt432.kitchenkarrot.client.plate.PlateBakedModel;
import io.github.tt432.kitchenkarrot.client.plate.PlateModelRegistry;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = Kitchenkarrot.MOD_ID)
public class ModModelRegistry {
//    public static void render(BakedModel bakedModel, MultiBufferSource bufferSource, BlockEntity blockEntity,
//                              PoseStack poseStack, int packedLight, int packedOverlay) {
//        bakedModel.getRenderTypes(blockEntity.getBlockState(), RandomSource.create(), ModelData.EMPTY).forEach(renderType -> {
//                    VertexConsumer buffer = bufferSource.getBuffer(RenderTypeHelper
//                            .getEntityRenderType(renderType, false));
//                    Minecraft.getInstance().getBlockRenderer().getModelRenderer()
//                            .renderModel(poseStack.last(), buffer, blockEntity.getBlockState(),
//                                    bakedModel, 1, 1, 1, packedLight, packedOverlay, ModelData.EMPTY, renderType);
//                }
//        );
////        VertexConsumer buffer = bufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(blockEntity.getBlockState(), false));
//
//
////        Minecraft.getInstance().getBlockRenderer().getModelRenderer()
////                .renderModel(poseStack.last(), buffer, blockEntity.getBlockState(),
////                        bakedModel, 1, 1, 1, packedLight, packedOverlay);
//    }

    @SubscribeEvent
    public static void registerModelUnBake(ModelEvent.RegisterAdditional e) {
        CocktailModelRegistry.register(e);
        PlateModelRegistry.register(e);
    }

    @SubscribeEvent
    public static void registerAtlasSpriteLoaders(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(CocktailTextureManager.INSTANCE);
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult evt) {
        //CocktailModelRegistry.bakeModel(evt);
        //PlateModelRegistry.bakeModel(evt);
        evt.getModels().put(new ModelResourceLocation(
                Kitchenkarrot.MOD_ID,
                "cocktail",
                "inventory"
        ), new CocktailBakedModel());

        evt.getModels().put(new ModelResourceLocation(
                Kitchenkarrot.MOD_ID,
                "plate",
                "inventory"
        ), new PlateBakedModel());
    }
//    @SubscribeEvent
//    public static void onModelBake(ModelBakeEvent evt) {
//        //CocktailModelRegistry.bakeModel(evt);
//        //PlateModelRegistry.bakeModel(evt);
//        evt.getModelRegistry().put(new ModelResourceLocation(
//                Kitchenkarrot.MOD_ID,
//                "cocktail",
//                "inventory"
//        ), new CocktailBakedModel());
//
//        evt.getModelRegistry().put(new ModelResourceLocation(
//                Kitchenkarrot.MOD_ID,
//                "plate",
//                "inventory"
//        ), new PlateBakedModel());
//    }
}
