package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.tt432.kitchenkarrot.block.PlateHolderMap;
import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.registries.RecipeSerializers;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author DustW
 **/
public class PlateRecipe extends BaseRecipe {
    public static final MapCodec<PlateRecipe> CODEC =
            RecordCodecBuilder.mapCodec(
                    builder ->
                            builder.group(
                                            ItemStack.SINGLE_ITEM_CODEC
                                                    .fieldOf("input")
                                                    .forGetter(plateRecipe -> plateRecipe.input),
                                            ItemStack.SINGLE_ITEM_CODEC
                                                    .fieldOf("result")
                                                    .forGetter(plateRecipe -> plateRecipe.result),
                                            Ingredient.CODEC
                                                    .optionalFieldOf(
                                                            "tool",
                                                            Ingredient.of(ModItemTags.KNIFE_ITEM))
                                                    .forGetter(plateRecipe -> plateRecipe.tool))
                                    .apply(builder, PlateRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, PlateRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ItemStack.STREAM_CODEC,
                    recipe -> recipe.input,
                    ItemStack.STREAM_CODEC,
                    recipe -> recipe.result,
                    Ingredient.CONTENTS_STREAM_CODEC,
                    recipe -> recipe.tool,
                    PlateRecipe::new);

    public PlateRecipe(ItemStack input, ItemStack result, Ingredient tool) {
        this.input = input;
        this.result = result;
        this.tool = tool;
    }

    ItemStack input;
    ItemStack result;
    Ingredient tool;

    public ItemStack getInput() {
        return input;
    }

    public int getResultCount() {
        return PlateHolderMap.plateHolder.get(result.getItem());
    }

    public ItemStack getResultStack() {
        return new ItemStack(result.getItem(), getResultCount());
    }

    public Ingredient getTool() {
        return tool;
    }

    public boolean canCut(ItemStack tool, ItemStack input) {
        return getTool().test(tool) && this.getInput().getItem() == input.getItem();
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return inputs.get(0).is(getInput().getItem());
    }

    @Override
    public String getId() {
        return getResultItem(RegistryAccess.EMPTY).getDescriptionId();
    }

    @Override
    @NotNull
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return result != null ? new ItemStack(result.getItem()) : ItemStack.EMPTY;
    }

    @Override
    @NotNull
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.PLATE.get();
    }

    @Override
    @NotNull
    public RecipeType<?> getType() {
        return RecipeTypes.PLATE.get();
    }
}
