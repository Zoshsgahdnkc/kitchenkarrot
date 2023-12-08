package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.tt432.kitchenkarrot.block.PlateHolderMap;
import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.registries.RecipeSerializers;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipeCodecs;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author DustW
 **/
public class PlateRecipe extends BaseRecipe<PlateRecipe> {
    public static final Codec<PlateRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Input.CODEC.fieldOf("input").forGetter(plateRecipe -> plateRecipe.input),
            Output.CODEC.fieldOf("result").forGetter(plateRecipe -> plateRecipe.result),
            Ingredient.CODEC.fieldOf("tool")
                    .orElse(Ingredient.of(ModItemTags.KNIFE_ITEM))
                    .forGetter(plateRecipe -> plateRecipe.tool)
    ).apply(builder, PlateRecipe::new));

    public PlateRecipe(Input input, Output result, Ingredient tool) {
        this.input = input;
        this.result = result;
        this.tool = tool;
    }

    Input input;
    Output result;
    Ingredient tool;

    public record Input(ItemStack item) {
        public static final Codec<Input> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("item").forGetter(Input::item)
        ).apply(builder, Input::new));
    }

    public record Output(ItemStack item) {
        public static final Codec<Output> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("item").forGetter(Output::item)
        ).apply(builder, Output::new));
    }

    public ItemStack getInput() {
        return input.item();
    }

    public int getResultCount() {
        return PlateHolderMap.plateHolder.get(result.item().getItem());
    }

    public ItemStack getResultStack() {
        return new ItemStack(result.item().getItem(), getResultCount());
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
    @NotNull
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return result.item != null ? new ItemStack(result.item().getItem()) : ItemStack.EMPTY;
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
