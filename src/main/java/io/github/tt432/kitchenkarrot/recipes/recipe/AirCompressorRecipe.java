package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.registries.RecipeSerializers;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.util.RecipeMatcher;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author DustW
 **/
public class AirCompressorRecipe extends BaseRecipe {
    public static final MapCodec<AirCompressorRecipe> CODEC =
            RecordCodecBuilder.mapCodec(
                    builder ->
                            builder.group(
                                            NonNullList.codecOf(Ingredient.CODEC)
                                                    .optionalFieldOf(
                                                            "ingredient",
                                                            NonNullList.withSize(
                                                                    4, Ingredient.EMPTY))
                                                    .forGetter(AirCompressorRecipe::getIngredient),
                                            Codec.INT
                                                    .fieldOf("craftingtime")
                                                    .forGetter(
                                                            AirCompressorRecipe::getCraftingTime),
                                            Ingredient.CODEC
                                                    .fieldOf("container")
                                                    .forGetter(AirCompressorRecipe::getContainer),
                                            ItemStack.SINGLE_ITEM_CODEC
                                                    .fieldOf("result")
                                                    .forGetter(recipe -> recipe.result))
                                    .apply(builder, AirCompressorRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, AirCompressorRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.fromCodec(NonNullList.codecOf(Ingredient.CODEC)),
                    recipe -> recipe.ingredient,
                    ByteBufCodecs.INT,
                    recipe -> recipe.craftingTime,
                    Ingredient.CONTENTS_STREAM_CODEC,
                    recipe -> recipe.container,
                    ItemStack.STREAM_CODEC,
                    recipe -> recipe.result,
                    AirCompressorRecipe::new);

    public AirCompressorRecipe(
            NonNullList<Ingredient> ingredient,
            int craftingTime,
            Ingredient container,
            ItemStack result) {
        this.ingredient = ingredient;
        this.craftingTime = craftingTime;
        this.container = container;
        this.result = result;
    }

    NonNullList<Ingredient> ingredient;
    int craftingTime;
    Ingredient container;
    ItemStack result;

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return RecipeMatcher.findMatches(inputs, ingredient) != null;
    }

    @Override
    public String getId() {
        return result.getDescriptionId();
    }

    public NonNullList<Ingredient> getIngredient() {
        return ingredient;
    }

    public boolean testContainer(ItemStack stack) {
        return container == null || container.test(stack);
    }

    public Ingredient getContainer() {
        return container;
    }

    @Override
    @NotNull
    public ItemStack getResultItem(@NotNull HolderLookup.Provider provider) {
        return result.copy();
    }

    public int getCraftingTime() {
        return craftingTime;
    }

    @Override
    @NotNull
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.AIR_COMPRESSOR.get();
    }

    @Override
    @NotNull
    public RecipeType<?> getType() {
        return RecipeTypes.AIR_COMPRESSOR.get();
    }
}
