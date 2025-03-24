package io.github.tt432.kitchenkarrot.dependencies.jei;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.dependencies.jei.category.*;
import io.github.tt432.kitchenkarrot.gui.AirCompressorGui;
import io.github.tt432.kitchenkarrot.gui.BrewingBarrelGui;
import io.github.tt432.kitchenkarrot.gui.ShakerGui;
import io.github.tt432.kitchenkarrot.item.ModBlockItems;
import io.github.tt432.kitchenkarrot.recipes.recipe.PlateRecipe;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.menu.AirCompressorMenu;
import io.github.tt432.kitchenkarrot.menu.BrewingBarrelMenu;
import io.github.tt432.kitchenkarrot.menu.ShakerMenu;
import io.github.tt432.kitchenkarrot.registries.ModMenuTypes;
import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.BrewingBarrelRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

/**
 * @author DustW
 **/
@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
    public static final RecipeType<AirCompressorRecipe> AIR_COMPRESSOR =
            new RecipeType<>(new ResourceLocation(Kitchenkarrot.MOD_ID, "air_compressor"),
                    AirCompressorRecipe.class);

    public static final RecipeType<CocktailRecipe> COCKTAIL =
            new RecipeType<>(new ResourceLocation(Kitchenkarrot.MOD_ID, "cocktail"),
                    CocktailRecipe.class);

    public static final RecipeType<BrewingBarrelRecipe> BREWING_BARREL =
            new RecipeType<>(new ResourceLocation(Kitchenkarrot.MOD_ID, "brewing_barrel"),
                    BrewingBarrelRecipe.class);

    public static final RecipeType<PlateRecipe> PLATE =
            new RecipeType<>(new ResourceLocation(Kitchenkarrot.MOD_ID, "plate"),
                    PlateRecipe.class);

    protected <C extends Container, T extends Recipe<C>> List<T> getRecipe(net.minecraft.world.item.crafting.RecipeType<T> recipeType) {
        return Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(recipeType);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new AirCompressorRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CocktailRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new BrewingBarrelRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new PlateRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(AIR_COMPRESSOR, getRecipe(RecipeTypes.AIR_COMPRESSOR.get()));
        registration.addRecipes(COCKTAIL, getRecipe(RecipeTypes.COCKTAIL.get()));
        registration.addRecipes(BREWING_BARREL, getRecipe(RecipeTypes.BREWING_BARREL.get()));
        registration.addRecipes(PLATE, getRecipe(RecipeTypes.PLATE.get()));
        registration.addIngredientInfo(new ItemStack(ModItems.GEM_CARROT.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.info.kitchenkarrot.gem_carrot"));
        registration.addIngredientInfo(new ItemStack(ModBlockItems.FINE_SALT.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.info.kitchenkarrot.fine_salt"));
        registration.addIngredientInfo(new ItemStack(ModItems.PILLAGER_PIE.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.info.kitchenkarrot.pillager_pie"));
        registration.addIngredientInfo(new ItemStack(ModItems.WATER.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.info.kitchenkarrot.water"));
        registration.addIngredientInfo(new ItemStack(ModItems.SHAKER.get()), VanillaTypes.ITEM_STACK, Component.translatable("jei.info.kitchenkarrot.shaker"));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlockItems.AIR_COMPRESSOR.get()), AIR_COMPRESSOR);
        registration.addRecipeCatalyst(new ItemStack(ModItems.SHAKER.get()), COCKTAIL);
        registration.addRecipeCatalyst(new ItemStack(ModBlockItems.BREWING_BARREL.get()), BREWING_BARREL);
        registration.addRecipeCatalyst(new ItemStack(ModItems.KNIFE.get()), PLATE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AirCompressorGui.class, 105, 52, 21, 19, AIR_COMPRESSOR);
        registration.addRecipeClickArea(BrewingBarrelGui.class, 133, 53, 4, 4, BREWING_BARREL);
        registration.addRecipeClickArea(ShakerGui.class, 155, 61, 14, 16, COCKTAIL);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(AirCompressorMenu.class, ModMenuTypes.AIR_COMPRESSOR.get(), AIR_COMPRESSOR,
                36, 5, 0, 36);
        registration.addRecipeTransferHandler(ShakerMenu.class, ModMenuTypes.SHAKER.get(), COCKTAIL,
                36, 5, 0, 36);
        registration.addRecipeTransferHandler(BrewingBarrelMenu.class, ModMenuTypes.BREWING_BARREL.get(), BREWING_BARREL,
                36, 6, 0, 36);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(ModItems.COCKTAIL.get(), CocktailSubtypeInterpreter.INSTANCE);
    }

    public static final ResourceLocation UID = new ResourceLocation(Kitchenkarrot.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }
}
