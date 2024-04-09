package io.github.tt432.kitchenkarrot.client.cocktail;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.TextureAtlasHolder;
import net.minecraft.resources.ResourceLocation;

public class CocktailTextureManager extends TextureAtlasHolder {
    public static final CocktailTextureManager INSTANCE = new CocktailTextureManager(Minecraft.getInstance().getTextureManager());

    private CocktailTextureManager(TextureManager p_262057_) {
        super(p_262057_, new ResourceLocation(Kitchenkarrot.MOD_ID, "textures/atlas/cocktail.png"), new ResourceLocation(Kitchenkarrot.MOD_ID, "cocktail"));
    }

    @Override
    public TextureAtlasSprite getSprite(ResourceLocation p_118902_) {
        return super.getSprite(p_118902_);
    }
}
