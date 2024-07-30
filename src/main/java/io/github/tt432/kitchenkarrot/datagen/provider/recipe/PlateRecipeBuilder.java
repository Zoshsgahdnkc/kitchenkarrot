package io.github.tt432.kitchenkarrot.datagen.provider.recipe;

import io.github.tt432.kitchenkarrot.recipes.recipe.PlateRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlateRecipeBuilder implements RecipeBuilder {
    private final ItemStack input;
    private final ItemStack result;
    private final Ingredient tool;

    private PlateRecipeBuilder(ItemStack input, ItemStack result, Ingredient tool) {
        this.input = input;
        this.result = result;
        this.tool = tool;
    }

    public static PlateRecipeBuilder plate(ItemStack input, ItemStack result, Ingredient tool) {
        return new PlateRecipeBuilder(input, result, tool);
    }

    @Override
    @NotNull
    public RecipeBuilder unlockedBy(@NotNull String name, @NotNull Criterion<?> criterion) {
        return this;
    }

    @Override
    @NotNull
    public RecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    @NotNull
    public Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceLocation id) {
        PlateRecipe plateRecipe = new PlateRecipe(input, result, tool);
        recipeOutput.accept(id, plateRecipe, null);
    }
}
