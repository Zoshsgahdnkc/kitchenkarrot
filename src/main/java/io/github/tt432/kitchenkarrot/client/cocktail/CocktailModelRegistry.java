package io.github.tt432.kitchenkarrot.client.cocktail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.stream.JsonReader;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import io.github.tt432.kitchenkarrot.util.json.JsonUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.event.ModelEvent;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author DustW
 **/
public class CocktailModelRegistry {

    private static final Map<ResourceLocation, BakedModel> MODEL_MAP = new HashMap<>();

    public static BakedModel get(ResourceLocation resourceLocation) {
        return MODEL_MAP.get(resourceLocation);
    }

    static ResourceLocation from(ModelResourceLocation modelResourceLocation) {
        return new ResourceLocation(modelResourceLocation.getNamespace(), modelResourceLocation.getPath().split("cocktail/")[1]);
    }

    public static ResourceLocation to(ResourceLocation resourceLocation) {
        return new ResourceLocation(resourceLocation.getNamespace(), "cocktail/" + resourceLocation.getPath());
    }

    @SuppressWarnings("unused")
    public static void register(ModelEvent.RegisterAdditional e) {
        CocktailList.INSTANCE.cocktails.clear();

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

        for (var info : CocktailList.INSTANCE.cocktails) {
            e.register(to(new ResourceLocation(info)));
        }
    }

    /*public static void bakeModel(ModelBakeEvent evt) {
        *//*MODEL_MAP.clear();

        for (String cocktailName : CocktailList.INSTANCE.cocktails) {
            ModelResourceLocation modelName = to(new ResourceLocation(cocktailName));
            MODEL_MAP.put(from(modelName), evt.getModelManager().getModel(modelName));
        }*//*

        *//*for (String cocktailName : CocktailList.INSTANCE.cocktails) {
            ResourceLocation name = new ResourceLocation(cocktailName);
            String namespace = name.getNamespace();
            String path = name.getPath();
            String json = "{\"parent\": \"minecraft:item/generated\", \"textures\": {\"layer0\":\"" + namespace + ":item/cocktail/" + path + "\"}}";
            //ForgeModelBakery.addSpecialModel(to(new ResourceLocation(cocktailName)));
            ForgeModelBakery instance = ForgeModelBakery.instance();
            BlockModel model = BlockModel.fromString(json);
            instance.unbakedCache.put(to(name), model);
            instance.topLevelModels.put(to(name), model);
        }*//*

        evt.getModelRegistry().put(new ModelResourceLocation(
                Kitchenkarrot.MOD_ID,
                "cocktail",
                "inventory"
        ), new CocktailBakedModel());
    }*/

}