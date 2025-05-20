package io.github.tt432.kitchenkarrot.datagen.provider.recipe;

import io.github.tt432.kitchenkarrot.recipes.recipe.BrewingBarrelRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BrewingBarrelRecipeBuilder implements RecipeBuilder {
    private final ItemLike result;
    private final int count;
    private final BrewingBarrelRecipe.Content content;

    private BrewingBarrelRecipeBuilder(ItemLike result, int count, BrewingBarrelRecipe.Content content) {
        this.result = result;
        this.content = content;
        this.count = count;
    }

    public static BrewingBarrelRecipeBuilder brewingBarrel(
            ItemLike result, int count, BrewingBarrelRecipe.Content content) {
        return new BrewingBarrelRecipeBuilder(result, count, content);
    }
    public static BrewingBarrelRecipeBuilder brewingBarrel(
            ItemLike result, BrewingBarrelRecipe.Content content) {
        return brewingBarrel(result, 1, content);
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
        return result.asItem();
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceLocation id) {
        BrewingBarrelRecipe brewingBarrelRecipe =
                new BrewingBarrelRecipe(new ItemStack(result, count), content);
        recipeOutput.accept(id, brewingBarrelRecipe, null);
    }
}
