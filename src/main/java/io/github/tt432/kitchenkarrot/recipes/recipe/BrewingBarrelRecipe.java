package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.google.gson.annotations.Expose;
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
public class BrewingBarrelRecipe extends BaseRecipe<BrewingBarrelRecipe> {
    public static final Codec<BrewingBarrelRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
            Content.CODEC.fieldOf("content").forGetter(recipe -> recipe.content)
    ).apply(builder, BrewingBarrelRecipe::new));

    public BrewingBarrelRecipe(ItemStack result, Content content) {
        this.result = result;
        this.content = content;
    }

    @Expose
    ItemStack result;
    @Expose
    Content content;

    public record Content(NonNullList<Ingredient> recipe, int craftingTime) {
        public static final Codec<Content> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("recipe").flatXmap(list -> {
                            Ingredient[] aingredient = list.stream().filter(i -> !i.isEmpty()).toArray(Ingredient[]::new);
                            return DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
                        }, DataResult::success)
                        .orElse(NonNullList.withSize(6, Ingredient.EMPTY))
                        .forGetter(Content::recipe),
                Codec.INT.fieldOf("craftingTime").forGetter(Content::craftingTime)
        ).apply(builder, Content::new));

    }

    public NonNullList<Ingredient> getIngredient() {
        return content.recipe;
    }

    public int getCraftingTime() {
        return content.craftingTime;
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return RecipeMatcher.findMatches(inputs, getIngredient()) != null;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.BREWING_BARREL.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypes.BREWING_BARREL.get();
    }
}
