package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.registries.RecipeSerializers;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipeCodecs;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.RecipeMatcher;

import java.util.List;

/**
 * @author DustW
 **/
public class AirCompressorRecipe extends BaseRecipe<AirCompressorRecipe> {
    public static final Codec<AirCompressorRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredient").flatXmap(ingredientList -> {
                        Ingredient[] aingredient = ingredientList.stream().filter(i -> !i.isEmpty()).toArray(Ingredient[]::new);
                        return DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
                    }, DataResult::success)
                    .orElse(NonNullList.withSize(4, Ingredient.EMPTY))
                    .forGetter(AirCompressorRecipe::getIngredient),
            Codec.INT.fieldOf("craftingTime").forGetter(AirCompressorRecipe::getCraftingTime),
            Ingredient.CODEC.fieldOf("container").forGetter(AirCompressorRecipe::getContainer),
            CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
    ).apply(builder, AirCompressorRecipe::new));

    public AirCompressorRecipe(NonNullList<Ingredient> ingredient, int craftingTime, Ingredient container, ItemStack result) {
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
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return result.copy();
    }

    public int getCraftingTime() {
        return craftingTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.AIR_COMPRESSOR.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypes.AIR_COMPRESSOR.get();
    }
}
