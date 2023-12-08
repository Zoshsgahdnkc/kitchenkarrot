package io.github.tt432.kitchenkarrot.util.json.serializer;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import net.minecraft.world.item.crafting.Ingredient;

import java.lang.reflect.Type;

/**
 * @author DustW
 **/
public class IngredientSerializer implements JsonSerializer<Ingredient>, JsonDeserializer<Ingredient> {
    @Override
    public Ingredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Ingredient.CODEC.parse(JsonOps.INSTANCE, json).get().orThrow();
    }

    @Override
    public JsonElement serialize(Ingredient src, Type typeOfSrc, JsonSerializationContext context) {
        return src.toJson(true);
    }
}
