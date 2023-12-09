package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.BrewingBarrelRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.PlateRecipe;
import io.github.tt432.kitchenkarrot.recipes.RecipeManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class RecipeTypes {
    private static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, RecipeManager.MOD_ID);
//    private static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registry.RECIPE_TYPE.key(), RecipeManager.MOD_ID);

    public static final RegistryObject<RecipeType<CocktailRecipe>> COCKTAIL = register("cocktail");
    public static final RegistryObject<RecipeType<AirCompressorRecipe>> AIR_COMPRESSOR = register("air_compressing");
    public static final RegistryObject<RecipeType<BrewingBarrelRecipe>> BREWING_BARREL = register("brewing_barrel");
    public static final RegistryObject<RecipeType<PlateRecipe>> PLATE = register("plate");

    private static <TYPE extends BaseRecipe> RegistryObject<RecipeType<TYPE>> register(String name) {
        return TYPES.register(name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return new ResourceLocation(RecipeManager.MOD_ID, name).toString();
            }
        });
    }

    public static void register(IEventBus bus) {
        TYPES.register(bus);
    }
}
