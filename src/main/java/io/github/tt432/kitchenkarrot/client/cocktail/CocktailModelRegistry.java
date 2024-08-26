package io.github.tt432.kitchenkarrot.client.cocktail;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.item.CocktailItem;
import io.github.tt432.kitchenkarrot.registries.ModCocktails;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class CocktailModelRegistry {
    private static final Map<ResourceLocation, BakedModel> MODEL_MAP = new HashMap<>();

    public static BakedModel get(ResourceLocation resourceLocation) {
        return MODEL_MAP.get(resourceLocation);
    }

    static ModelResourceLocation from(ModelResourceLocation modelResourceLocation) {
        return ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(
                        modelResourceLocation.id().getNamespace(),
                        modelResourceLocation.id().getPath().split("cocktail/")[1]));
    }

    public static ResourceLocation cocktailRL(String path) {
        return cocktailRL(Kitchenkarrot.MOD_ID, path);
    }

    public static ResourceLocation cocktailRL(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, "cocktails/" + path);
    }

    public static ModelResourceLocation to(ResourceLocation resourceLocation) {
        return ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(
                        resourceLocation.getNamespace(), "cocktail/" + resourceLocation.getPath()));
    }

    @SuppressWarnings("unused")
    public static void register(ModelEvent.RegisterAdditional e) {
        e.register(to(CocktailItem.UNKNOWN_COCKTAIL));

        ModCocktails.COCKTAIL_PROPERTIES.getEntries().stream()
                .map(DeferredHolder::get)
                .forEach(cocktailProperty -> e.register(to(cocktailProperty.id())));
    }
}
