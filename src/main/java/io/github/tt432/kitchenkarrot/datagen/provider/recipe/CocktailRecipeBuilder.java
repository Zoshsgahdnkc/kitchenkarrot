package io.github.tt432.kitchenkarrot.datagen.provider.recipe;

import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CocktailRecipeBuilder implements RecipeBuilder {
    private final Holder<CocktailProperty> cocktailPropertyHolder;
    private final CocktailRecipe.Content content;

    private CocktailRecipeBuilder(
            Holder<CocktailProperty> cocktailPropertyHolder, CocktailRecipe.Content content) {
        this.cocktailPropertyHolder = cocktailPropertyHolder;
        this.content = content;
    }

    public static void cocktail(
            RecipeOutput recipeOutput,
            Holder<CocktailProperty> cocktailPropertyHolder,
            CocktailRecipe.Content content) {
        new CocktailRecipeBuilder(cocktailPropertyHolder, content)
                .save(recipeOutput, cocktailPropertyHolder.value().id());
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
        return new CocktailRecipe(cocktailPropertyHolder, content)
                .getResultItem(RegistryAccess.EMPTY)
                .getItem();
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        CocktailRecipe cocktailRecipe = new CocktailRecipe(cocktailPropertyHolder, content);
        recipeOutput.accept(id, cocktailRecipe, null);
    }
}
