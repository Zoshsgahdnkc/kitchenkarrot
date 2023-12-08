package io.github.tt432.kitchenkarrot.util.json.serializer;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;

import java.lang.reflect.Type;

/**
 * @author DustW
 **/
public class NonNullListSerializer implements JsonSerializer<NonNullList<Ingredient>>, JsonDeserializer<NonNullList<Ingredient>> {

    @Override
    public NonNullList<Ingredient> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var list = json.getAsJsonArray();
        var result = NonNullList.withSize(list.size(), Ingredient.EMPTY);

        for (int i = 0; i < list.size(); i++) {
            result.set(i, Ingredient.CODEC_NONEMPTY.parse(JsonOps.INSTANCE, list.get(i)).get().orThrow());
        }

        return result;
    }

    @Override
    public JsonElement serialize(NonNullList<Ingredient> src, Type typeOfSrc, JsonSerializationContext context) {
        var list = new JsonArray();

        for (Ingredient ingredient : src) {
            list.add(ingredient.toJson(false));
        }

        return list;
    }
}
