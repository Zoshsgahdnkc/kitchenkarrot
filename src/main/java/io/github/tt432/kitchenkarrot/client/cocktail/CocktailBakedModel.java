package io.github.tt432.kitchenkarrot.client.cocktail;

import static io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry.RLtoMRL;

import io.github.tt432.kitchenkarrot.item.CocktailItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author DustW
 **/
public class CocktailBakedModel implements BakedModel {
    @Override
    public List<BakedQuad> getQuads(
            @Nullable BlockState p_119123_, @Nullable Direction p_119124_, RandomSource p_119125_) {
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
    @NotNull
    public TextureAtlasSprite getParticleIcon() {
        return CocktailTextureManager.INSTANCE.getSprite(CocktailItem.UNKNOWN_COCKTAIL);
    }

    // spotless:off
    @Override
    @NotNull
    public ItemOverrides getOverrides() {
        return new ItemOverrides() {
            @NotNull
            @Override
            public BakedModel resolve(
                    @NotNull BakedModel model,
                    @NotNull ItemStack stack,
                    @Nullable ClientLevel level,
                    @Nullable LivingEntity entity,
                    int seed) {

                ResourceLocation cocktail = Objects.requireNonNull(CocktailItem.getCocktail(stack)).id();
                BakedModel cocktailModel;
                ModelManager modelManager = Minecraft.getInstance().getModelManager();
                if (cocktail != null
                        && modelManager.getMissingModel() != modelManager.getModel(RLtoMRL(cocktail))) {
                    cocktailModel = modelManager.getModel(RLtoMRL(cocktail));
                } else {
                    cocktailModel = modelManager.getModel(RLtoMRL(CocktailItem.UNKNOWN_COCKTAIL));
                }
                return cocktailModel;
            }
        };
    }
    // spotless:on
}
