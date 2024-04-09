package io.github.tt432.kitchenkarrot.client.plate;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author DustW
 **/
public class PlateBakedModel implements BakedModel {
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState p_119123_, @Nullable Direction p_119124_, Random p_119125_) {
        return Collections.emptyList();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return true;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(ModBlocks.PLATE.getId());
    }

    @Override
    @NotNull
    public ItemOverrides getOverrides() {
        return new ItemOverrides() {
            @Nullable
            @Override
            public BakedModel resolve(BakedModel model, ItemStack stack, @Nullable ClientLevel p_173467_, @Nullable LivingEntity p_173468_, int p_173469_) {
                var tag = stack.getTag();

                if (tag != null && tag.contains("plate_type") && !tag.getString("plate_type").equals("minecraft:air") && tag.contains("plate_amount")) {
                    var plateType = new ResourceLocation(Kitchenkarrot.MOD_ID, new ResourceLocation(tag.getString("plate_type")).getPath());
                    var location = new ResourceLocation(
                            plateType + "_" + tag.getInt("plate_amount")
                    );

                    BakedModel model1 = Minecraft.getInstance().getModelManager().getModel(PlateModelRegistry.to(location));
                    return model1;
                }

                BakedModel model1 = Minecraft.getInstance().getModelManager().getModel(PlateModelRegistry.to(PlateModelRegistry.DEFAULT_NAME));
                return model1;
            }
        };
    }
}
