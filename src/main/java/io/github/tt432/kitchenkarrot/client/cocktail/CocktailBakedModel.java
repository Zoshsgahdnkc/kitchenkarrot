package io.github.tt432.kitchenkarrot.client.cocktail;

import static io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry.to;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.item.CocktailItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
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
    public TextureAtlasSprite getParticleIcon() {
        return CocktailTextureManager.INSTANCE.getSprite(CocktailItem.UNKNOWN_COCKTAIL);
    }

    // spotless:off
    @Override
    public ItemOverrides getOverrides() {
        return new ItemOverrides() {
            @NotNull
            @Override
            public BakedModel resolve(
                    @NotNull BakedModel p_173465_,
                    @NotNull ItemStack p_173466_,
                    @Nullable ClientLevel p_173467_,
                    @Nullable LivingEntity p_173468_,
                    int p_173469_) {

                ResourceLocation cocktail = Objects.requireNonNull(CocktailItem.getCocktail(p_173466_)).id();
                BakedModel model;
                ModelManager modelManager = Minecraft.getInstance().getModelManager();
                if (cocktail != null
                        && modelManager.getMissingModel() != modelManager.getModel(to(cocktail))) {
                    model = modelManager.getModel(to(cocktail));
                } else {
                    if (cocktail != null) {
                        Kitchenkarrot.LOGGER.warn("Miss cocktail texture {} for {}", to(cocktail), p_173466_.getItem());
                    } else {
                        Kitchenkarrot.LOGGER.warn("Miss cocktailProperty");
                    }
                    model = modelManager.getModel(ModelResourceLocation.standalone(CocktailItem.UNKNOWN_COCKTAIL));
                }
                return model;
            }
        };
    }
    // spotless:on
}
