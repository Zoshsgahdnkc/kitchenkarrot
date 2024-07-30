package io.github.tt432.kitchenkarrot.datagen.provider.recipe;

import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class AirCompressorRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final NonNullList<Ingredient> ingredient;
    private final int craftingTime;
    private final Ingredient container;

    private AirCompressorRecipeBuilder(
            ItemLike result,
            NonNullList<Ingredient> ingredient,
            int craftingTime,
            Ingredient container) {
        this.result = result.asItem();
        this.ingredient = ingredient;
        this.craftingTime = craftingTime;
        this.container = container;
    }

    public static AirCompressorRecipeBuilder airCompressor(
            ItemLike result,
            NonNullList<Ingredient> ingredient,
            int craftingTime,
            Ingredient container) {
        return new AirCompressorRecipeBuilder(result, ingredient, craftingTime, container);
    }

    @Override
    public AirCompressorRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        return this;
    }

    @Override
    public AirCompressorRecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput recipeOutput, @NotNull ResourceLocation id) {
        AirCompressorRecipe airCompressorRecipe =
                new AirCompressorRecipe(ingredient, craftingTime, container, new ItemStack(result));
        recipeOutput.accept(id, airCompressorRecipe, null);
    }
}
