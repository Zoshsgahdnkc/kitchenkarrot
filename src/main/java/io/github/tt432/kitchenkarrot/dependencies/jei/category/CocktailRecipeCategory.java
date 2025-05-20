package io.github.tt432.kitchenkarrot.dependencies.jei.category;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.dependencies.jei.JeiPlugin;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * @author DustW
 **/
public class CocktailRecipeCategory extends BaseRecipeCategory<CocktailRecipe> {
    protected static final ResourceLocation BACKGROUND =
            ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, "textures/gui/jei.png");

    public CocktailRecipeCategory(IGuiHelper helper) {
        super(
                JeiPlugin.COCKTAIL,
                helper.createDrawableIngredient(
                        VanillaTypes.ITEM_STACK, new ItemStack(ModItems.SHAKER.get())),
                helper.createDrawable(BACKGROUND, 0, 0, 119, 71));
    }

    @Override
    public void setRecipe(
            IRecipeLayoutBuilder builder, CocktailRecipe recipe, IFocusGroup focuses) {
        var ingredients = recipe.getContent().recipe();

        builder.addSlot(RecipeIngredientRole.INPUT, 44, 15).addIngredients(ingredients.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 15).addIngredients(ingredients.get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 88, 15).addIngredients(ingredients.get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 39).addIngredients(ingredients.get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 78, 39).addIngredients(ingredients.get(4));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 15, 19)
                .addItemStack(recipe.getResultItem(RegistryAccess.EMPTY));
        //        builder.addSlot(RecipeIngredientRole.OUTPUT, 15,
        // 32).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(
            CocktailRecipe recipe,
            IRecipeSlotsView recipeSlotsView,
            GuiGraphics guiGraphics,
            double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        drawShakeTime(recipe, guiGraphics);
    }

    private void drawShakeTime(CocktailRecipe recipe, GuiGraphics guiGraphics) {
        int timeInTicks = recipe.getContent().craftingTime();
        if (timeInTicks > 0) {
            int timeInSeconds = timeInTicks / 20;
            Component timeString =
                    Component.translatable("gui.jei.category.smelting.time.seconds", timeInSeconds);
            Font font = Minecraft.getInstance().font;
            int stringWidth = font.width(timeString);
            guiGraphics.drawString(
                    font, timeString, getWidth() - stringWidth - 7, 57, 0xFF2F2F2F, false);
        }
    }
}
