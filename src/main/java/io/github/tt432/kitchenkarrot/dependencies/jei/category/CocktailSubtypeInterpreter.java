package io.github.tt432.kitchenkarrot.dependencies.jei.category;

import io.github.tt432.kitchenkarrot.item.CocktailItem;

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CocktailSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
    public static final CocktailSubtypeInterpreter INSTANCE = new CocktailSubtypeInterpreter();

    @Override
    @Nullable
    public Object getSubtypeData(ItemStack ingredient, @NotNull UidContext context) {
        if (ingredient.getItem() instanceof CocktailItem) {
            return CocktailItem.getCocktail(ingredient).toString();
        }
        return null;
    }

    @Override
    @NotNull
    public String getLegacyStringSubtypeInfo(
            @NotNull ItemStack ingredient, @NotNull UidContext context) {
        return "";
    }
}
