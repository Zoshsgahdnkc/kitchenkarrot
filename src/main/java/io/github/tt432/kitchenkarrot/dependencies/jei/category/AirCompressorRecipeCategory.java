package io.github.tt432.kitchenkarrot.dependencies.jei.category;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.dependencies.jei.JeiPlugin;
import io.github.tt432.kitchenkarrot.item.ModBlockItems;
import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author DustW
 **/
public class AirCompressorRecipeCategory extends BaseRecipeCategory<AirCompressorRecipe> {
    protected final IDrawableAnimated arrow;
    protected final IDrawable power;
    protected final ITickTimer timer;
    protected static final ResourceLocation BACKGROUND =
            ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, "textures/gui/jei.png");

    public AirCompressorRecipeCategory(IGuiHelper helper) {
        super(
                JeiPlugin.AIR_COMPRESSOR,
                helper.createDrawableIngredient(
                        VanillaTypes.ITEM_STACK, new ItemStack(ModBlockItems.AIR_COMPRESSOR.get())),
                helper.createDrawable(BACKGROUND, 0, 141, 104, 78));
        arrow =
                helper.drawableBuilder(BACKGROUND, 176, 0, 20, 19)
                        .buildAnimated(40, IDrawableAnimated.StartDirection.BOTTOM, false);
        power = helper.drawableBuilder(BACKGROUND, 176, 32, 8, 5).build();
        timer = helper.createTickTimer(180, 194, false);
    }

    @Override
    public void setRecipe(
            IRecipeLayoutBuilder builder, AirCompressorRecipe recipe, IFocusGroup focuses) {
        var ingredient = recipe.getIngredient();

        builder.addSlot(RecipeIngredientRole.INPUT, 28, 32).addIngredients(ingredient.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 32).addIngredients(ingredient.get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 28, 53).addIngredients(ingredient.get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 53).addIngredients(ingredient.get(3));
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 28, 9)
                .addIngredients(Ingredient.of(Items.REDSTONE));

        var container = Objects.requireNonNullElse(recipe.getContainer(), Ingredient.EMPTY);
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 9).addIngredients(container);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 75, 32)
                .addItemStack(recipe.getResultItem(RegistryAccess.EMPTY));
    }

    @Override
    public void draw(
            @NotNull AirCompressorRecipe recipe,
            @NotNull IRecipeSlotsView recipeSlotsView,
            @NotNull GuiGraphics guiGraphics,
            double mouseX,
            double mouseY) {
        arrow.draw(guiGraphics, 70, 52);
        for (int i = 0; i < timer.getValue() / 15; i++) {
            power.draw(guiGraphics, 14, 64 - i * 5);
        }
    }
}
