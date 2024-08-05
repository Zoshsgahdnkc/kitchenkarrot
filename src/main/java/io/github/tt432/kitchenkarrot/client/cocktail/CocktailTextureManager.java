package io.github.tt432.kitchenkarrot.client.cocktail;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.util.json.JsonUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.TextureAtlasHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Stream;

public class CocktailTextureManager extends TextureAtlasHolder {
    public static final CocktailTextureManager INSTANCE = new CocktailTextureManager(Minecraft.getInstance().getTextureManager());

    private CocktailTextureManager(TextureManager p_262057_) {
        super(p_262057_, new ResourceLocation(Kitchenkarrot.MOD_ID, "textures/atlas/cocktail.png"), "item/cocktail");
    }

    @Override
    protected Stream<ResourceLocation> getResourcesToLoad() {
        //因加载顺序问题 这边 需要先读一遍list.json
        if (CocktailList.INSTANCE.cocktails.isEmpty()){
            ResourceManager manager = Minecraft.getInstance().getResourceManager();
            for (String namespace : manager.getNamespaces()) {
                try {
                    ResourceLocation resourceName = new ResourceLocation(namespace, "cocktail/list.json");
                    if (manager.getResource(resourceName).isPresent()) {
                        Optional<Resource> resource = manager.getResource(resourceName);
                        if (resource.isPresent()) {
                            InputStreamReader reader = new InputStreamReader(resource.get().open());
                            CocktailList list = JsonUtils.INSTANCE.noExpose.fromJson(reader, CocktailList.class);
                            CocktailList.INSTANCE.cocktails.addAll(list.cocktails);
                        }
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return CocktailList.INSTANCE.cocktails.stream().map(ResourceLocation::new);
    }

    @Override
    public TextureAtlasSprite getSprite(ResourceLocation p_118902_) {
        return super.getSprite(p_118902_);
    }
}
