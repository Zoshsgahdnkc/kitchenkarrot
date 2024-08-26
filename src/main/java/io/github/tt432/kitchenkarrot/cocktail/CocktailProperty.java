package io.github.tt432.kitchenkarrot.cocktail;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.tt432.kitchenkarrot.recipes.object.EffectStack;

import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Objects;

public record CocktailProperty(ResourceLocation id, String author, List<EffectStack> effectStack) {
    public static final Codec<CocktailProperty> CODEC =
            RecordCodecBuilder.create(
                    inst ->
                            inst.group(
                                            ResourceLocation.CODEC
                                                    .fieldOf("id")
                                                    .forGetter(CocktailProperty::id),
                                            Codec.STRING
                                                    .fieldOf("author")
                                                    .forGetter(CocktailProperty::author),
                                            EffectStack.CODEC
                                                    .listOf()
                                                    .fieldOf("effect")
                                                    .forGetter(CocktailProperty::effectStack))
                                    .apply(inst, CocktailProperty::new));

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailProperty that = (CocktailProperty) o;
        return Objects.equals(author, that.author)
                && Objects.equals(id, that.id)
                && Objects.equals(effectStack, that.effectStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, effectStack);
    }
}
