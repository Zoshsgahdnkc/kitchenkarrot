package io.github.tt432.kitchenkarrot.client.plate;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.block.PlateHolderMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.neoforge.client.event.ModelEvent;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author DustW
 **/
public class PlateModelRegistry {

    public static final Map<ResourceLocation, ResourceLocation> MODEL_MAP = new HashMap<>();

    public static ResourceLocation DEFAULT_NAME =
            ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, "plate");

    static ModelResourceLocation from(ModelResourceLocation modelResourceLocation) {
        return ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(
                        modelResourceLocation.id().getNamespace(),
                        modelResourceLocation.id().getPath().split("plates/")[1]));
    }

    public static ModelResourceLocation RLtoMRL(ResourceLocation resourceLocation) {
        return ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(
                        resourceLocation.getNamespace(), "plates/" + resourceLocation.getPath()));
    }

    public static void register(ModelEvent.RegisterAdditional e) {
        PlateList.INSTANCE.plates.clear();

        Set<String> plates = new HashSet<>();
        PlateHolderMap.plateHolder.forEach(
                (key, value) -> {
                    for (int i = 1; i <= value; i++) {
                        plates.add(
                                ResourceLocation.fromNamespaceAndPath(
                                                Kitchenkarrot.MOD_ID,
                                                Objects.requireNonNull(
                                                                BuiltInRegistries.ITEM.getKey(key))
                                                        .getPath())
                                        + "_"
                                        + i);
                    }
                });

        PlateList.INSTANCE.plates.addAll(plates);

        e.register(RLtoMRL(DEFAULT_NAME));

        for (var info : PlateList.INSTANCE.plates) {
            e.register(RLtoMRL(ResourceLocation.parse(info)));
        }
    }

    static BlockModel getModel(String info) {
        ResourceManager manager = Minecraft.getInstance().getResourceManager();
        ResourceLocation name = ResourceLocation.parse(info);
        String namespace = name.getNamespace();
        String path = name.getPath();
        ResourceLocation modelName =
                ResourceLocation.fromNamespaceAndPath(namespace, "models/plates/" + path + ".json");
        if (manager.getResource(modelName).isPresent()) {
            try {
                Optional<Resource> resource = manager.getResource(modelName);
                String json = IOUtils.toString(resource.get().open(), StandardCharsets.UTF_8);
                return BlockModel.fromString(json);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }
}
