package io.github.tt432.kitchenkarrot.cocktail;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.DynamicOps;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry;
import io.github.tt432.kitchenkarrot.item.CocktailItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Collection;
import java.util.Map;

public class CocktailManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();
    private Map<ResourceLocation, CocktailProperty> registeredCocktailProperty = ImmutableMap.of();

    public CocktailManager() {
        super(GSON, Registries.elementsDirPath(CocktailModelRegistry.COCKTAIL_REGISTRY_KEY));
    }

    @Override
    protected Map<ResourceLocation, JsonElement> prepare(
            ResourceManager resourceManager, ProfilerFiller profiler) {
        Map<ResourceLocation, JsonElement> resourceList = super.prepare(resourceManager, profiler);

        // copy form neoforge
        DynamicOps<JsonElement> ops = this.makeConditionalOps();
        ImmutableMap.Builder<ResourceLocation, CocktailProperty> builder = ImmutableMap.builder();

        builder.put(CocktailItem.UNKNOWN_COCKTAIL, CocktailItem.UNKNOWN_COCKTAIL_PROPERTY);

        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceList.entrySet()) {
            ResourceLocation location = entry.getKey();
            JsonElement json = entry.getValue();
            CocktailProperty.CODEC
                    .parse(ops, json)
                    .resultOrPartial(
                            errorMsg ->
                                    Kitchenkarrot.LOGGER.warn(
                                            "Could not decode CocktailProperty with json id {} - error: {}",
                                            location,
                                            errorMsg))
                    .ifPresent(cocktailProperty -> builder.put(location, cocktailProperty));
        }
        this.registeredCocktailProperty = builder.build();

        return resourceList;
    }

    @Override
    protected void apply(
            Map<ResourceLocation, JsonElement> resourceList,
            ResourceManager resourceManager,
            ProfilerFiller profiler) {}

    public Collection<CocktailProperty> getAllCocktailProperty() {
        return registeredCocktailProperty.values();
    }
}
