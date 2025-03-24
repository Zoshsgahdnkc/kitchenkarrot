package io.github.tt432.kitchenkarrot.datagen.provider;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.datagen.provider.recipe.ModShapelessRecipeBuilder;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

import javax.annotation.Nullable;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected static final String KK = Kitchenkarrot.MOD_ID;
    protected static final String OIL = "has_oil";
    protected static final String SALT = "has_salt";
    protected static final String MEAT = "has_meat";
    protected static final String CHEESE = "has_cheese";
    protected static final InventoryChangeTrigger.TriggerInstance HAS_OIL =
            has(ModItemTags.OIL);
    protected static final InventoryChangeTrigger.TriggerInstance HAS_SALT =
            has(ModItemTags.SALT);

    public ModRecipeProvider(
            PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_251297_) {
        // Air Compressor
//        genAirCompressor(p_251297_);

        // Brewing Barrel
//        genBrewingBarrel(p_251297_);

        // Cocktails
        genCocktails(p_251297_);

        // Compat
        //        genCompat(recipeOutput);

        // Plate
//        genPlate(p_251297_);

        // Food Shapeless
        genFoodShapeless(p_251297_);

        // Shapeless
        genShapeless(p_251297_);

        // Shaped
        genShaped(p_251297_);

        // Campfire Cooking
        genCampfireCooking(p_251297_);

        // Smoking
        genSmokingCooking(p_251297_);

        // stoneCutting
        genStoneCutting(p_251297_);

        // smithingTransform
        genSmithingTransform(p_251297_);
    }
//
//    private void genPlate(Consumer<FinishedRecipe> recipeOutput) {
//        PlateRecipeBuilder.plate(
//                        ModItems.BEEF_IN_DRIPLEAF.get().get().getDefaultInstance(),
//                        ModItems.SMALL_BEEF_IN_DRIPLEAF.get().getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(ModItems.SMALL_BEEF_IN_DRIPLEAF.get())));
//
//        PlateRecipeBuilder.plate(
//                        ModItems.CHORUS_MOUSSE.get().get().getDefaultInstance(),
//                        ModItems.SMALL_CHORUS_MOUSSE.get().getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(ModItems.SMALL_CHORUS_MOUSSE.get())));
//
//        PlateRecipeBuilder.plate(
//                        ModItems.DUNGEON_PIZZA.get().get().getDefaultInstance(),
//                        ModItems.DUNGEON_PIZZA.get()_SLICE.get().getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(ModItems.DUNGEON_PIZZA.get()_SLICE.get())));
//
//        PlateRecipeBuilder.plate(
//                        ModItems.FEAST_PIZZA.get().getDefaultInstance(),
//                        ModItems.FEAST_PIZZA_SLICE.get().getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(ModItems.FEAST_PIZZA_SLICE.get())));
//
//        PlateRecipeBuilder.plate(
//                        Items.MELON_SLICE.getDefaultInstance(),
//                        Items.MELON_SLICE.getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(Items.MELON_SLICE)));
//
//        PlateRecipeBuilder.plate(
//                        ModItems.MONSTER_LASAGNA.get().getDefaultInstance(),
//                        ModItems.SMALL_MONSTER_LASAGNA.get().getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(ModItems.SMALL_MONSTER_LASAGNA.get())));
//
//        PlateRecipeBuilder.plate(
//                        ModItems.SHINY_PIZZA.get().getDefaultInstance(),
//                        ModItems.SHINY_PIZZA_SLICE.get().getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(ModItems.SHINY_PIZZA_SLICE.get())));
//
//        PlateRecipeBuilder.plate(
//                        ModItems.SIRLOIN_STEAK.get().getDefaultInstance(),
//                        ModItems.BEEF_GRAINS.get().getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(ModItems.BEEF_GRAINS.get())));
//
//        PlateRecipeBuilder.plate(
//                        ModItems.SLIME_MOUSSE.get().getDefaultInstance(),
//                        ModItems.SMALL_SLIME_MOUSSE.get().getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(ModItems.SMALL_SLIME_MOUSSE.get())));
//
//        PlateRecipeBuilder.plate(
//                        ModItems.SWEET_LOAF.get().get().getDefaultInstance(),
//                        ModItems.SWEET_LOAF.get()_SLICE.get().getDefaultInstance(),
//                        Ingredient.of(ModItemTags.KNIFE_ITEM))
//                .save(recipeOutput, RL(getPlateRecipeName(ModItems.SWEET_LOAF.get()_SLICE.get())));
//    }

    //
    //    private void genCompat(Consumer<FinishedRecipe> recipeOutput) {
    //        Consumer<FinishedRecipe> loadArsNouveauOutput = recipeOutput.withConditions(
    //                new ModLoadedCondition("ars_nouveau"));
    //    }

    private void genCocktails(Consumer<FinishedRecipe> recipeOutput) {
        // TODO use Cocktails Registries
    }

//    private void genBrewingBarrel(Consumer<FinishedRecipe> recipeOutput) {
//        BrewingBarrelRecipeBuilder.brewingBarrel(
//                        ModItems.ACORN_WINE,
//                        new BrewingBarrelRecipe.Content(
//                                NonNullList.of(
//                                        Ingredient.EMPTY,
//                                        Ingredient.of(ModItemTags.ACORN),
//                                        Ingredient.of(ModItemTags.ACORN),
//                                        Ingredient.of(ModItems.CARROT_SPICES),
//                                        Ingredient.of(ModItems.CARROT_SPICES),
//                                        Ingredient.of(Tags.Items.CROPS_WHEAT),
//                                        Ingredient.of(Tags.Items.CROPS_WHEAT)),
//                                6000,
//                                500))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ACORN_WINE)));
//
//        BrewingBarrelRecipeBuilder.brewingBarrel(
//                        ModItems.CHEESE_WHEEL,
//                        new BrewingBarrelRecipe.Content(
//                                NonNullList.of(
//                                        Ingredient.EMPTY,
//                                        Ingredient.of(ModItemTags.MILK),
//                                        Ingredient.of(ModItemTags.MILK),
//                                        Ingredient.of(ModItemTags.MILK),
//                                        Ingredient.of(ModItemTags.MILK),
//                                        Ingredient.of(ModItemTags.MILK),
//                                        Ingredient.of(ModItemTags.MILK)),
//                                3000,
//                                0))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CHEESE_WHEEL)));
//
//        BrewingBarrelRecipeBuilder.brewingBarrel(
//                        ModItems.GLOW_BERRY_LAGER,
//                        new BrewingBarrelRecipe.Content(
//                                NonNullList.of(
//                                        Ingredient.EMPTY,
//                                        Ingredient.of(Items.WHEAT),
//                                        Ingredient.of(Items.WHEAT),
//                                        Ingredient.of(Items.WHEAT),
//                                        Ingredient.of(Items.SWEET_BERRIES),
//                                        Ingredient.of(Items.SWEET_BERRIES),
//                                        Ingredient.of(Items.SWEET_BERRIES)),
//                                2000,
//                                250))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.GLOW_BERRY_LAGER)));
//
//        BrewingBarrelRecipeBuilder.brewingBarrel(
//                        ModItems.ICED_MELON_LAGER,
//                        new BrewingBarrelRecipe.Content(
//                                NonNullList.of(
//                                        Ingredient.EMPTY,
//                                        Ingredient.of(Items.MELON_SLICE),
//                                        Ingredient.of(Items.MELON_SLICE),
//                                        Ingredient.of(Items.WHEAT),
//                                        Ingredient.of(Items.WHEAT),
//                                        Ingredient.of(ModItemTags.ICE_CUBES),
//                                        Ingredient.of(ModItemTags.ICE_CUBES)),
//                                2000,
//                                250))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ICED_MELON_LAGER)));
//
//        BrewingBarrelRecipeBuilder.brewingBarrel(
//                        ModItems.MEAD.get(),
//                        new BrewingBarrelRecipe.Content(
//                                NonNullList.of(
//                                        Ingredient.EMPTY,
//                                        Ingredient.of(Items.HONEY_BOTTLE),
//                                        Ingredient.of(Items.HONEY_BOTTLE),
//                                        Ingredient.of(Items.HONEY_BOTTLE),
//                                        Ingredient.of(ModItems.WATER.get()),
//                                        Ingredient.of(ModItems.WATER.get()),
//                                        Ingredient.of(ModItems.WATER.get())),
//                                6000,
//                                500))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MEAD.get())));
//
//        BrewingBarrelRecipeBuilder.brewingBarrel(
//                        ModItems.RUM,
//                        new BrewingBarrelRecipe.Content(
//                                NonNullList.of(
//                                        Ingredient.EMPTY,
//                                        Ingredient.of(Items.SUGAR_CANE),
//                                        Ingredient.of(Items.SUGAR_CANE),
//                                        Ingredient.of(Items.SUGAR_CANE),
//                                        Ingredient.of(Items.SUGAR_CANE),
//                                        Ingredient.of(Tags.Items.CROPS_WHEAT),
//                                        Ingredient.of(Tags.Items.CROPS_WHEAT)),
//                                6000,
//                                500))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RUM)));
//
//        BrewingBarrelRecipeBuilder.brewingBarrel(
//                        ModItems.VODKA,
//                        new BrewingBarrelRecipe.Content(
//                                NonNullList.of(
//                                        Ingredient.EMPTY,
//                                        Ingredient.of(Tags.Items.CROPS_POTATO),
//                                        Ingredient.of(Tags.Items.CROPS_POTATO),
//                                        Ingredient.of(Tags.Items.CROPS_POTATO),
//                                        Ingredient.of(Tags.Items.CROPS_POTATO),
//                                        Ingredient.of(Tags.Items.CROPS_BEETROOT),
//                                        Ingredient.of(Tags.Items.CROPS_BEETROOT)),
//                                6000,
//                                500))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.VODKA)));
//    }
//
//    private void genAirCompressor(Consumer<FinishedRecipe> recipeOutput) {
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.CANNED_BEEF_POTATO,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(ModItemTags.OIL),
//                                Ingredient.of(ModItemTags.SALT),
//                                Ingredient.of(ModItemTags.COOKED_BEEF),
//                                Ingredient.of(Items.POTATO)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_BEEF_POTATO)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.CANNED_CANDIED_APPLE,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(Items.SUGAR),
//                                Ingredient.of(Items.HONEY_BOTTLE),
//                                Ingredient.of(Items.APPLE),
//                                Ingredient.of(Items.APPLE)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_CANDIED_APPLE)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.CANNED_MUTTON_PUMPKIN,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(ModItemTags.OIL),
//                                Ingredient.of(ModItemTags.SALT),
//                                Ingredient.of(ModItemTags.COOKED_MUTTON),
//                                Ingredient.of(Items.PUMPKIN)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_MUTTON_PUMPKIN)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.CANNED_PORK_BEETROOT,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(ModItemTags.OIL),
//                                Ingredient.of(ModItemTags.SALT),
//                                Ingredient.of(ModItemTags.COOKED_PORK),
//                                Ingredient.of(Items.BEETROOT)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_PORK_BEETROOT)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.CANNED_SWEET_BERRY_MILK,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(Items.SUGAR),
//                                Ingredient.of(Items.SUGAR),
//                                Ingredient.of(ModItems.SWEET_BERRY_MILK),
//                                Ingredient.of(ModItems.SWEET_BERRY_MILK)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_SWEET_BERRY_MILK)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.CORAL_COKE,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(ModItemTags.WATER),
//                                Ingredient.of(Items.SUGAR),
//                                Ingredient.of(ModItems.ICE_CUBES.get()),
//                                Ingredient.of(ModItemTags.CORALS)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CORAL_COKE)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.DANDELION_COKE,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(ModItemTags.WATER),
//                                Ingredient.of(Items.SUGAR),
//                                Ingredient.of(ModItems.ICE_CUBES.get()),
//                                Ingredient.of(Items.DANDELION)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.DANDELION_COKE)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.DRAGON_BREATH_COKE,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(ModItemTags.WATER),
//                                Ingredient.of(Items.SUGAR),
//                                Ingredient.of(ModItems.ICE_CUBES.get()),
//                                Ingredient.of(Items.DRAGON_BREATH)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.DRAGON_BREATH_COKE)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.KELP_SODA,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(ModItemTags.WATER),
//                                Ingredient.of(ModItemTags.WATER),
//                                Ingredient.of(Items.KELP),
//                                Ingredient.of(Items.KELP)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.KELP_SODA)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.LIGHT_SODA,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(ModItemTags.WATER),
//                                Ingredient.of(ModItemTags.WATER),
//                                Ingredient.of(ItemTags.LEAVES),
//                                Ingredient.of(ItemTags.LEAVES)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.LIGHT_SODA)));
//
//        AirCompressorRecipeBuilder.airCompressor(
//                        ModItems.TWISTING_SODA,
//                        NonNullList.of(
//                                Ingredient.EMPTY,
//                                Ingredient.of(ModItemTags.WATER),
//                                Ingredient.of(ModItemTags.WATER),
//                                Ingredient.of(Items.TWISTING_VINES),
//                                Ingredient.of(Items.TWISTING_VINES)),
//                        100,
//                        Ingredient.of(ModItems.EMPTY_CAN))
//                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.TWISTING_SODA)));
//    }

    private void genSmithingTransform(Consumer<FinishedRecipe> recipeOutput) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.IRON_NUGGET),
                        Ingredient.of(Items.BUCKET),
                        Ingredient.of(Items.IRON_NUGGET),
                        RecipeCategory.MISC,
                        ModItems.SHAKER.get())
                .unlocks(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .unlocks(getHasName(Items.BUCKET), has(Items.BUCKET))
                .unlocks(getHasName(ModItems.SHAKER.get()), has(ModItems.SHAKER.get()))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SHAKER.get())));
    }

    private void genFoodShapeless(Consumer<FinishedRecipe> recipeOutput) {
        foodShapeless(ModItems.PHANTOM_STEW)
                .requires(Items.MUSHROOM_STEW)
                .requires(ModItems.CARROT_SPICES)
                .requires(Items.BONE)
                .requires(Items.PHANTOM_MEMBRANE)
                .unlockedBy(Items.MUSHROOM_STEM)
                .unlockedBy(ModItems.CARROT_SPICES)
                .unlockedBy(Items.PHANTOM_MEMBRANE)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.PHANTOM_STEW.get())));
        foodShapeless(ModItems.STONE_SHORE_QUICHE)
                .requires(Ingredient.of(Tags.Items.CROPS_WHEAT), 2)
                .requires(Items.TURTLE_EGG)
                .requires(ModItemTags.CHEESE)
                .requires(Items.POPPY)
                .requires(ModItems.CARROT_SPICES)
                .requires(ModItemTags.SALT)
                .unlockedBy(Items.TURTLE_EGG)
                .unlockedBy(ModItems.CHEESE_WHEEL)
                .unlockedBy(Items.POPPY)
                .unlockedBy(SALT, HAS_SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.STONE_SHORE_QUICHE.get())));
        foodShapeless(ModItems.CRIMSON_FUNGI_SPRING_ROLL, 2)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.CRIMSON_FUNGUS, 2)
                .requires(ModItems.CARROT_SPICES)
                .requires(ModItemTags.MEAT)
                .unlockedBy(Items.WHEAT)
                .unlockedBy(Items.CRIMSON_FUNGUS)
                .unlockedBy(ModItems.CARROT_SPICES)
                .save(
                        recipeOutput,
                        RL(getSimpleRecipeName(ModItems.CRIMSON_FUNGI_SPRING_ROLL.get())));
        foodShapeless(ModItems.BUCHE_DE_NOEL)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(tag("forge", "eggs"))
                .requires(Items.SWEET_BERRIES)
                .requires(ItemTags.LOGS)
                .requires(Items.SUGAR)
                .requires(ModItemTags.ICE_CUBES)
                .unlockedBy(Items.SWEET_BERRIES)
                .unlockedBy(Items.SUGAR)
                .unlockedBy(Items.WHEAT)
                .unlockedBy(ModItemTags.ICE_CUBES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BUCHE_DE_NOEL.get())));
        foodShapeless(ModItems.MIXED_NUTS_MOONCAKE)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(ModItemTags.ACORN)
                .requires(ModItems.BIRCH_SAP)
                .requires(Items.SUGAR)
                .unlockedBy(Items.WHEAT)
                .unlockedBy(ModItemTags.ACORN)
                .unlockedBy(ModItems.BIRCH_SAP)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MIXED_NUTS_MOONCAKE.get())));
        foodShapeless(ModItems.BIRCH_SAP_DONUT)
                .requires(Items.WHEAT)
                .requires(ModItems.BIRCH_SAP)
                .requires(ModItemTags.OIL)
                .requires(Items.SUGAR, 2)
                .unlockedBy(Items.WHEAT)
                .unlockedBy(ModItems.BIRCH_SAP)
                .unlockedBy(Items.SUGAR)
                .unlockedBy(OIL, HAS_OIL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BIRCH_SAP_DONUT.get())));
        foodShapeless(ModItems.BIKINI_BOTTOM_SUB)
                .requires(ModItemTags.BREAD)
                .requires(ModItems.SASHIMI)
                .requires(ModItems.PICKLED_SEA_PICKLES)
                .requires(ModItemTags.CHEESE)
                .requires(ModItems.CARROT_SPICES)
                .requires(Items.INK_SAC)
                .unlockedBy(ModItemTags.BREAD)
                .unlockedBy(ModItems.SASHIMI)
                .unlockedBy(ModItems.PICKLED_SEA_PICKLES)
                .unlockedBy(ModItems.CHEESE_SLICE)
                .unlockedBy(ModItems.CARROT_SPICES)
                .unlockedBy(Items.INK_SAC)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BIKINI_BOTTOM_SUB.get())));
    }

    private void genStoneCutting(Consumer<FinishedRecipe> recipeOutput) {
        RecipeProvider.stonecutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.ACORN.get(), Items.OAK_SAPLING);
        RecipeProvider.stonecutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.PLATE_PIECES.get(), ModItems.EMPTY_PLATE.get(), 3);

        KKStoneCutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.ICE_CUBES.get(), Items.ICE, 1);
        KKStoneCutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.ICE_CUBES.get(), Items.PACKED_ICE, 4);
        KKStoneCutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.ICE_CUBES.get(), Items.BLUE_ICE, 8);
    }

    private void genSmokingCooking(Consumer<FinishedRecipe> recipeOutput) {
        smokingCooking(recipeOutput, 100, Items.WHEAT, ModItems.GRILLED_WHEATMEAL.get(), 0.1F);
        smokingCooking(
                recipeOutput, 100, ModItems.RAW_VEGAN_BEEF.get(), ModItems.COOKED_VEGAN_BEEF.get(), 0.5F);
        smokingCooking(
                recipeOutput, 100, ModItems.RAW_BEEF_IN_DRIPLEAF.get(), ModItems.BEEF_IN_DRIPLEAF.get(), 0.5F);
        smokingCooking(
                recipeOutput, 100, ModItems.RAW_VEGAN_MUTTON.get(), ModItems.COOKED_VEGAN_MUTTON.get(), 0.5F);
        smokingCooking(
                recipeOutput, 100, ModItems.RAW_VEGAN_PORK.get(), ModItems.COOKED_VEGAN_PORK.get(), 0.5F);
        smokingCooking(recipeOutput, 400, ModItems.RAW_SWEET_LOAF.get(), ModItems.SWEET_LOAF.get(), 1F);
    }

    private void genCampfireCooking(Consumer<FinishedRecipe> recipeOutput) {
        campfireCooking(recipeOutput, 600, ModItems.RAW_VEGAN_BEEF.get(), ModItems.COOKED_VEGAN_BEEF.get());
        campfireCooking(
                recipeOutput, 600, ModItems.RAW_BEEF_IN_DRIPLEAF.get(), ModItems.BEEF_IN_DRIPLEAF.get());
        campfireCooking(recipeOutput, 600, Items.WHEAT, ModItems.GRILLED_WHEATMEAL.get());
        campfireCooking(recipeOutput, 600, ModItems.RAW_VEGAN_MUTTON.get(), ModItems.COOKED_VEGAN_MUTTON.get());
        campfireCooking(recipeOutput, 600, ModItems.RAW_VEGAN_PORK.get(), ModItems.COOKED_VEGAN_PORK.get());
    }

    private void genShaped(Consumer<FinishedRecipe> recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.EMPTY_PLATE.get(), 2)
                .define('2', Items.BRICK)
                .define('1', Items.IRON_NUGGET)
                .pattern("121")
                .unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.EMPTY_PLATE.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.KNIFE.get())
                .define('#', Items.IRON_SWORD)
                .define('n', Items.IRON_NUGGET)
                .pattern(" #")
                .pattern("n ")
                .unlockedBy(getHasName(Items.IRON_SWORD), has(Items.IRON_SWORD))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.KNIFE.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GOLDEN_CHEESE.get())
                .define('#', ModItems.CHEESE_SLICE.get())
                .define('-', Items.GOLD_NUGGET)
                .pattern("---")
                .pattern("-#-")
                .pattern("---")
                .unlockedBy(
                        getHasName(ModItems.CHEESE_SLICE.get()), has(ModItems.CHEESE_SLICE.get()))
                .unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.GOLDEN_CHEESE.get())));

        simpleMiscShaped(ModBlocks.ACORN_OIL.get(), 2, ModItemTags.ACORN)
                .pattern("* ")
                .pattern(" *")
                .define('*', ModItemTags.ACORN)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.ACORN_OIL.get())));

        simpleMiscShaped(ModBlocks.AIR_COMPRESSOR.get(), null)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .define('1', Items.BLUE_DYE)
                .define('2', Items.MINECART)
                .define('3', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .unlockedBy(getHasName(Items.BLUE_DYE), has(Items.BLUE_DYE))
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .unlockedBy(getHasName(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), has(Items.HEAVY_WEIGHTED_PRESSURE_PLATE))
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.AIR_COMPRESSOR.get())));

        simpleFoodShaped(ModItems.BAMBOO_POTATO.get(), null)
                .pattern("123")
                .pattern(" 0 ")
                .define('0', Items.BAMBOO)
                .define('1', Tags.Items.EGGS)
                .define('2', Items.BAKED_POTATO)
                .define('3', ModItemTags.SALT)
                .unlockedBy(getHasName(Items.BAMBOO), has(Items.BAMBOO))
                .unlockedBy(getHasName(Items.EGG), has(Tags.Items.EGGS))
                .unlockedBy(getHasName(Items.BAKED_POTATO), has(Items.BAKED_POTATO))
                .unlockedBy(SALT, HAS_SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BAMBOO_POTATO.get())));

        simpleMiscShaped(ModBlocks.BREWING_BARREL.get(), null)
                .pattern(" B ")
                .pattern("---")
                .define('B', Items.BARREL)
                .define('-', Items.COPPER_INGOT)
                .unlockedBy(getHasName(Items.BARREL), has(Items.BARREL))
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.BREWING_BARREL.get())));

        simpleFoodShaped(ModItems.CHORUS_MOUSSE.get(), null)
                .pattern("121")
                .pattern("343")
                .pattern("555")
                .define('1', Items.CHORUS_FRUIT)
                .define('2', Items.BONE_MEAL)
                .define('3', Items.SUGAR)
                .define('4', ModItems.ICE_CUBES.get())
                .define('5', Tags.Items.CROPS_WHEAT)
                .unlockedBy(getHasName(Items.CHORUS_FRUIT), has(Items.CHORUS_FRUIT))
                .unlockedBy(getHasName(Items.BONE_MEAL), has(Items.BONE_MEAL))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(ModItems.ICE_CUBES.get()), has(ModItems.ICE_CUBES.get()))
                .unlockedBy(getHasName(Items.WHEAT), has(Tags.Items.CROPS_WHEAT))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CHORUS_MOUSSE.get())));

        simpleMiscShaped(ModBlocks.CHORUS_OIL.get(), 2, null)
                .pattern("* ")
                .pattern(" *")
                .define('*', Items.CHORUS_FRUIT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.CHORUS_OIL.get())));

        simpleMiscShaped(ModBlocks.COASTER.get(), null)
                .pattern("* *")
                .pattern(" # ")
                .pattern("* *")
                .define('*', Items.STICK)
                .define('#', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.COASTER.get())));

        simpleFoodShaped(ModItems.DUNGEON_PIZZA.get(), null)
                .pattern("121")
                .pattern("333")
                .pattern("444")
                .define('1', ModItemTags.CHEESE)
                .define('2', ModItemTags.SALT)
                .define('3', Ingredient.of(Items.ROTTEN_FLESH, Items.SPIDER_EYE))
                .define('4', Tags.Items.CROPS_WHEAT)
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH))
                .unlockedBy(getHasName(Items.SPIDER_EYE), has(Items.SPIDER_EYE))
                .unlockedBy(getHasName(Items.WHEAT), has(Tags.Items.CROPS_WHEAT))
                .unlockedBy(CHEESE, has(ModItemTags.CHEESE))
                .unlockedBy(SALT, HAS_SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.DUNGEON_PIZZA.get())));

        simpleMiscShaped(ModItems.EMPTY_CAN.get(), 4, null)
                .pattern(" 1 ")
                .pattern("121")
                .pattern(" 1 ")
                .define('1', Items.IRON_NUGGET)
                .define('2', Items.BOWL)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .unlockedBy(getHasName(Items.BOWL), has(Items.BOWL))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.EMPTY_CAN.get())));

        simpleFoodShaped(ModItems.FEAST_PIZZA.get(), null)
                .pattern("121")
                .pattern("333")
                .pattern("444")
                .define('1', ModItemTags.CHEESE)
                .define('2', ModItemTags.OIL)
                .define('3', ModItemTags.MEAT)
                .define('4', Tags.Items.CROPS_WHEAT)
                .unlockedBy(getHasName(Items.WHEAT), has(Tags.Items.CROPS_WHEAT))
                .unlockedBy(MEAT, has(ModItemTags.MEAT))
                .unlockedBy(CHEESE, has(ModItemTags.CHEESE))
                .unlockedBy(OIL, HAS_OIL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FEAST_PIZZA.get())));

        simpleFoodShaped(ModItems.FISHERMENS_DELIGHT.get(), null)
                .pattern("123")
                .pattern(" 0 ")
                .define('0', Items.BOWL)
                .define('1', ModItems.BIRCH_SAP.get())
                .define('2', Items.COOKED_SALMON)
                .define('3', Items.KELP)
                .unlockedBy(getHasName(Items.BOWL), has(Items.BOWL))
                .unlockedBy(getHasName(Items.COOKED_SALMON), has(Items.COOKED_SALMON))
                .unlockedBy(getHasName(Items.KELP), has(Items.KELP))
                .unlockedBy(getHasName(ModItems.BIRCH_SAP.get()), has(ModItems.BIRCH_SAP.get()))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FISHERMENS_DELIGHT.get())));

        simpleFoodShaped(ModItems.GRILLED_FISH_AND_CACTUS.get(), null)
                .pattern("123")
                .pattern(" 0 ")
                .define('0', Items.CACTUS)
                .define('1', ModItemTags.SALT)
                .define('2', ModItemTags.FOOD_COOKED_FISH)
                .define('3', ModItemTags.GRASS_SPICES)
                .unlockedBy(getHasName(Items.CACTUS), has(Items.CACTUS))
                .unlockedBy("has_fish", has(ModItemTags.FOOD_COOKED_FISH))
                .unlockedBy("has_spices", has(ModItemTags.GRASS_SPICES))
                .unlockedBy(SALT, HAS_SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.GRILLED_FISH_AND_CACTUS.get())));

        simpleFoodShaped(ModItems.MONSTER_LASAGNA.get(), null)
                .pattern("11")
                .pattern("22")
                .pattern("33")
                .define('1', ModItemTags.MILK)
                .define('2', Items.ROTTEN_FLESH)
                .define('3', Tags.Items.CROPS_WHEAT)
                .unlockedBy("has_milk", has(ModItemTags.MILK))
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ROTTEN_FLESH))
                .unlockedBy(getHasName(Items.WHEAT), has(Tags.Items.CROPS_WHEAT))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MONSTER_LASAGNA.get())));

        simpleMiscShaped(ModItems.EMPTY_PLATE.get(), null)
                .pattern("11")
                .pattern("11")
                .define('1', ModItems.PLATE_PIECES.get())
                .unlockedBy(getHasName(ModItems.PLATE_PIECES.get()), has(ModItems.PLATE_PIECES.get()))
                .save(
                        recipeOutput,
                        RL(getConversionRecipeName(ModItems.EMPTY_PLATE.get(), ModItems.PLATE_PIECES.get())));

        simpleMiscShaped(ModItems.RAW_SWEET_LOAF.get(), null)
                .pattern("123")
                .pattern("000")
                .define('0', Items.BREAD)
                .define('1', ModItemTags.MILK)
                .define('2', Items.SUGAR)
                .define('3', ModItemTags.OIL)
                .unlockedBy(OIL, HAS_OIL)
                .unlockedBy(getHasName(Items.BREAD), has(Items.BREAD))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy("has_milk", has(ModItemTags.MILK))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_SWEET_LOAF.get())));

        simpleMiscShaped(ModBlocks.ROCK_SALT, 2, null)
                .pattern("* ")
                .pattern(" *")
                .define('*', ModItemTags.SALT_ROCK)
                .unlockedBy("has_rock", has(ModItemTags.SALT_ROCK))
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.ROCK_SALT.get())));

        simpleMiscShaped(ModBlocks.ROCK_SALT, 2, null)
                .pattern("* ")
                .pattern(" *")
                .define('*', Items.TUFF)
                .unlockedBy(getHasName(Items.TUFF), has(Items.TUFF))
                .save(recipeOutput, RL("rock_salt_with_tuff"));

        simpleMiscShaped(ModBlocks.SEA_SALT, 2, null)
                .pattern("* ")
                .pattern(" #")
                .define('*', ModItems.WATER.get())
                .define('#', Items.BONE_MEAL)
                .unlockedBy(getHasName(Items.BONE_MEAL), has(Items.BONE_MEAL))
                .unlockedBy(getHasName(ModItems.WATER.get()), has(ModItems.WATER.get()))
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.SEA_SALT.get()) + "_" + 2));

        simpleFoodShaped(ModItems.SHINY_PIZZA.get(), null)
                .pattern("123")
                .pattern("456")
                .pattern("777")
                .define('1', Tags.Items.GEMS_DIAMOND)
                .define('2', Tags.Items.DUSTS_REDSTONE)
                .define('3', Items.EMERALD)
                .define('4', Items.COPPER_INGOT)
                .define('5', Items.IRON_INGOT)
                .define('6', Items.GOLD_INGOT)
                .define('7', Tags.Items.CROPS_WHEAT)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(Items.REDSTONE), has(Tags.Items.DUSTS_REDSTONE))
                .unlockedBy(getHasName(Items.DIAMOND), has(Tags.Items.GEMS_DIAMOND))
                .unlockedBy(getHasName(Items.WHEAT), has(Tags.Items.CROPS_WHEAT))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SHINY_PIZZA.get())));

        simpleFoodShaped(ModItems.SLIME_MOUSSE.get(), null)
                .pattern("121")
                .pattern("343")
                .pattern("555")
                .define('1', Tags.Items.SLIMEBALLS)
                .define('2', Items.BONE_MEAL)
                .define('3', Items.SUGAR)
                .define('4', ModItems.ICE_CUBES.get())
                .define('5', Tags.Items.CROPS_WHEAT)
                .unlockedBy(getHasName(Items.BONE_MEAL), has(Items.BONE_MEAL))
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .unlockedBy(getHasName(ModItems.ICE_CUBES.get()), has(ModItems.ICE_CUBES.get()))
                .unlockedBy(getHasName(Items.WHEAT), has(Tags.Items.CROPS_WHEAT))
                .unlockedBy(getHasName(Items.SLIME_BALL), has(Tags.Items.SLIMEBALLS))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SLIME_MOUSSE.get())));

        simpleMiscShaped(ModBlocks.SUNFLOWER_OIL, 2, null)
                .pattern("* ")
                .pattern(" *")
                .define('*', Items.SUNFLOWER)
                .unlockedBy(getHasName(Items.SUNFLOWER), has(Items.SUNFLOWER))
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.SUNFLOWER_OIL.get())));
    }

    private void genShapeless(Consumer<FinishedRecipe> recipeOutput) {
        Consumer<FinishedRecipe> notTagEmptyConsumer =
                recipeOutput.andThen(c-> new NotCondition(new TagEmptyCondition(ModItemTags.CROPS_RICE.location())));

        ModShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.EMPTY_PLATE.get())
                .requires(ModItems.PLATE_PIECES.get(), 4)
                .unlockedBy(ModItems.PLATE_PIECES.get())
                .save(recipeOutput, RL("plate_from_pieces"));

        simpleMiscShapeless(ModItems.ACORN, ModItemTags.ACORN)
                .requires(Items.OAK_SAPLING)
                .unlockedBy(getHasName(Items.OAK_SAPLING), has(Items.OAK_SAPLING))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ACORN.get())));

        simpleMiscShapeless(ModItems.ACORN_WINE_BASE, 4, null)
                .group("Base")
                .requires(ModItems.ACORN_WINE.get())
                .unlockedBy(getHasName(ModItems.ACORN_WINE.get()), has(ModItems.ACORN_WINE.get()))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ACORN_WINE_BASE.get())));

        foodShapeless(ModItems.BACON_WRAPPED_POTATO, 4)
                .requires(ModItemTags.COOKED_PORK)
                .requires(Items.BAKED_POTATO)
                .requires(Items.BAKED_POTATO)
                .requires(ModItemTags.SALT)
                .requires(ModItemTags.OIL)
                .unlockedBy(ModItemTags.COOKED_PORK)
                .unlockedBy(Items.BAKED_POTATO)
                .unlockedBy(OIL, HAS_OIL)
                .unlockedBy(SALT, HAS_SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BACON_WRAPPED_POTATO.get())));

        foodShapeless(ModItems.BEETROOT_CREPE, 2)
                .requires(Items.BREAD)
                .requires(Items.BEETROOT)
                .requires(Items.BEETROOT)
                .requires(Items.BEETROOT)
                .requires(ModItemTags.ACORN)
                .requires(ModItemTags.SALT)
                .unlockedBy(Items.BREAD)
                .unlockedBy(Items.BEETROOT)
                .unlockedBy(ModItems.ACORN)
                .unlockedBy(SALT, HAS_SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BEETROOT_CREPE.get())));

        foodShapeless(ModItems.BEETROOT_SALAD, 2)
                .requires(Items.BREAD)
                .requires(Tags.Items.EGGS)
                .requires(Items.BEETROOT)
                .requires(Items.BEETROOT)
                .requires(Items.BOWL)
                .requires(Items.BOWL)
                .unlockedBy(Items.BREAD)
                .unlockedBy(Items.BEETROOT)
                .unlockedBy(Items.BOWL)
                .unlockedBy(Tags.Items.EGGS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BEETROOT_SALAD.get())));

        foodShapeless(ModItems.BIRCH_SAP)
                .requires(Items.BIRCH_SAPLING)
                .requires(ModItemTags.WATER)
                .unlockedBy(Items.BIRCH_SAPLING)
                .unlockedBy(ModItemTags.WATER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BIRCH_SAP.get())));

        foodShapeless(ModItems.BIRCH_SAP_CHOCOLATE_BAR)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .requires(ModItems.BIRCH_SAP.get())
                .requires(Items.SUGAR)
                .unlockedBy(Items.COCOA_BEANS)
                .unlockedBy(ModItems.BIRCH_SAP.get())
                .unlockedBy(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BIRCH_SAP_CHOCOLATE_BAR.get())));

        foodShapeless(ModItems.CARROT_AND_CARROT)
                .requires(ModItems.GEM_CARROT.get())
                .requires(Items.CARROT)
                .requires(ModItems.CARROT_SPICES.get())
                .requires(Items.BOWL)
                .unlockedBy(ModItems.GEM_CARROT.get())
                .unlockedBy(Items.CARROT)
                .unlockedBy(ModItems.CARROT_SPICES.get())
                .unlockedBy(Items.BOWL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CARROT_AND_CARROT.get())));

        foodShapeless(ModItems.CARROT_SPICES, 2)
                .requires(Tags.Items.CROPS_CARROT)
                .requires(Tags.Items.CROPS_CARROT)
                .requires(Items.PAPER)
                .requires(ModItemTags.GRASS_SPICES)
                .unlockedBy(Tags.Items.CROPS_CARROT)
                .unlockedBy(Items.PAPER)
                .unlockedBy(ModItemTags.GRASS_SPICES)
                .save(
                        recipeOutput,
                        RL(getConversionRecipeName(ModItems.CARROT_SPICES.get(), Items.CARROT)));

        foodShapeless(ModItems.CARROT_SPICES, 4)
                .requires(ModItems.GEM_CARROT.get())
                .requires(Items.PAPER)
                .requires(ModItemTags.GRASS_SPICES)
                .unlockedBy(ModItems.GEM_CARROT.get())
                .unlockedBy(Items.PAPER)
                .unlockedBy(ModItemTags.GRASS_SPICES)
                .save(
                        recipeOutput,
                        RL(getConversionRecipeName(ModItems.CARROT_SPICES.get(), ModItems.GEM_CARROT.get())));

        foodShapeless(ModItems.CARROT_TART)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.CARROT)
                .requires(ModItemTags.MILK)
                .requires(Items.SUGAR)
                .unlockedBy(Tags.Items.CROPS_WHEAT)
                .unlockedBy(Items.CARROT)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CARROT_TART.get())));

        foodShapeless(ModItems.CHINESE_CREPE, 2)
                .requires(Items.BREAD)
                .requires(ModItemTags.SALT)
                .requires(ModItemTags.OIL)
                .requires(ModItems.CARROT_SPICES.get())
                .requires(ModItemTags.COOKED_MEAT)
                .unlockedBy(Items.BREAD)
                .unlockedBy(ModItemTags.SALT)
                .unlockedBy(ModItemTags.OIL)
                .unlockedBy(ModItems.CARROT_SPICES.get())
                .unlockedBy(ModItemTags.COOKED_MEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CHINESE_CREPE.get())));

        foodShapeless(ModItems.CHOCOLATE_CROISSANT)
                .requires(ModItemTags.MILK)
                .requires(Items.BREAD)
                .requires(Items.COCOA_BEANS)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(Items.BREAD)
                .unlockedBy(Items.COCOA_BEANS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CHOCOLATE_CROISSANT.get())));

        //  TODO miss ModItems.CREAM_OF_MUSHROOM_SOUP

        //        foodShapeless(ModItems.CREAM_OF_MUSHROOM_SOUP, null)
        //                .requires(ModItemTags.MILK)
        //                .requires(ModItemTags.OIL)
        //                .requires(Tags.Items.MUSHROOMS)
        //                .requires(Tags.Items.MUSHROOMS)
        //                .requires(Tags.Items.MUSHROOMS)
        //                .requires(Items.BOWL)
        //                .save(recipeOutput,
        // RL(getSimpleRecipeName(ModItems.CREAM_OF_MUSHROOM_SOUP)));

        foodShapeless(ModItems.CREEPER_CEREAL_PORRIDGE)
                .requires(Items.BOWL)
                .requires(ModItems.GRILLED_WHEATMEAL.get())
                .requires(ModItemTags.MILK)
                .requires(Items.GUNPOWDER)
                .unlockedBy(Items.BOWL)
                .unlockedBy(ModItems.GRILLED_WHEATMEAL.get())
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(Items.GUNPOWDER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CREEPER_CEREAL_PORRIDGE.get())));

        foodShapeless(ModItems.CRISPY_BREAD_WITH_KELP)
                .requires(ModItemTags.SALT)
                .requires(Items.BREAD)
                .requires(Items.DRIED_KELP)
                .unlockedBy(ModItemTags.SALT)
                .unlockedBy(Items.BREAD)
                .unlockedBy(Items.DRIED_KELP)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CRISPY_BREAD_WITH_KELP.get())));

        foodShapeless(ModItems.CROQUE_MADAME)
                .requires(Items.BREAD)
                .requires(ModItemTags.CHEESE)
                .requires(ModItemTags.OIL)
                .requires(Tags.Items.EGGS)
                .unlockedBy(Items.BREAD)
                .unlockedBy(ModItemTags.CHEESE)
                .unlockedBy(ModItemTags.OIL)
                .unlockedBy(Tags.Items.EGGS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CROQUE_MADAME.get())));

        foodShapeless(ModItems.CURRY_UDON)
                .requires(Items.POTATO)
                .requires(Items.CARROT)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .requires(ModItemTags.COOKED_MEAT)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .requires(ModItems.CARROT_SPICES.get())
                .requires(Items.BOWL)
                .unlockedBy(ModItems.CARROT_SPICES.get())
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CURRY_UDON.get())));

        foodShapeless(ModItems.DRUMSTICK)
                .requires(ModItemTags.RAW_CHICKEN)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .unlockedBy(ModItemTags.RAW_CHICKEN)
                .unlockedBy(ModItemTags.OIL)
                .unlockedBy(ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.DRUMSTICK.get())));

        foodShapeless(ModItems.EGG_TART)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.EGG)
                .requires(ModItemTags.MILK)
                .requires(Items.SUGAR)
                .unlockedBy(Tags.Items.CROPS_WHEAT)
                .unlockedBy(Items.EGG)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.EGG_TART.get())));

        foodShapeless(ModItems.FLOWER_CAKE)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.EGG)
                .requires(ItemTags.SMALL_FLOWERS)
                .unlockedBy(Tags.Items.CROPS_WHEAT)
                .unlockedBy(Items.EGG)
                .unlockedBy(ItemTags.SMALL_FLOWERS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FLOWER_CAKE.get())));

        foodShapeless(ModItems.FRESH_SALAD)
                .requires(Items.BOWL)
                .requires(Items.MOSS_BLOCK)
                .requires(ItemTags.SMALL_FLOWERS)
                .requires(ItemTags.SMALL_FLOWERS)
                .unlockedBy(Items.BOWL)
                .unlockedBy(Items.MOSS_BLOCK)
                .unlockedBy(ItemTags.SMALL_FLOWERS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRESH_SALAD.get())));

        foodShapeless(ModItems.FRIED_CHICKEN_COMBO)
                .requires(ModItems.FRIES.get())
                .requires(ModItems.FRIES.get())
                .requires(ModItems.DRUMSTICK.get())
                .requires(ModItems.DRUMSTICK.get())
                .requires(ModItems.CARROT_SPICES.get())
                .requires(ModItems.CARROT_SPICES.get())
                .requires(Items.PAPER)
                .unlockedBy(ModItems.FRIES.get())
                .unlockedBy(ModItems.DRUMSTICK.get())
                .unlockedBy(ModItems.CARROT_SPICES.get())
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRIED_CHICKEN_COMBO.get())));

        foodShapeless(ModItems.FRIED_PUMPKIN_CAKE, 2)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.PUMPKIN)
                .requires(ModItemTags.OIL)
                .unlockedBy(Tags.Items.CROPS_WHEAT)
                .unlockedBy(Items.PUMPKIN)
                .unlockedBy(ModItemTags.OIL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRIED_PUMPKIN_CAKE.get())));

        foodShapeless(ModItems.FRIES)
                .requires(Items.POTATO)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .unlockedBy(Items.POTATO)
                .unlockedBy(ModItemTags.OIL)
                .unlockedBy(ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRIES.get())));

        foodShapeless(ModItems.FRUIT_CEREAL_PORRIDGE)
                .requires(Items.BOWL)
                .requires(ModItems.GRILLED_WHEATMEAL.get())
                .requires(ModItemTags.MILK)
                .requires(Ingredient.of(ModItemTags.MOD_FRUITS))
                .unlockedBy(Items.BOWL)
                .unlockedBy(ModItems.GRILLED_WHEATMEAL.get())
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(ModItemTags.MOD_FRUITS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRUIT_CEREAL_PORRIDGE.get())));

        foodShapeless(ModItems.HI_NRG_BRULEE)
                .requires(ModItemTags.MILK)
                .requires(ModItemTags.OIL)
                .requires(Tags.Items.DUSTS_REDSTONE)
                .requires(ModItemTags.FIRE_CHARGES)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(ModItemTags.OIL)
                .unlockedBy(Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(ModItemTags.FIRE_CHARGES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.HI_NRG_BRULEE.get())));

        foodShapeless(ModItems.HONEY_BRULEE)
                .requires(ModItemTags.MILK)
                .requires(ModItemTags.OIL)
                .requires(Items.SUGAR)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(ModItemTags.OIL)
                .unlockedBy(Items.SUGAR)
                .unlockedBy(Items.HONEY_BOTTLE)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.HONEY_BRULEE.get())));

        foodShapeless(ModItems.ICE_CUBES)
                .requires(Items.SNOWBALL)
                .requires(ModItems.WATER.get())
                .unlockedBy(Items.SNOWBALL)
                .unlockedBy(ModItems.WATER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ICE_CUBES.get())));

        foodShapeless(ModItems.KELP_WITH_SUNFLOWER_SEED, 8)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.SUNFLOWER)
                .unlockedBy(Items.DRIED_KELP)
                .unlockedBy(Items.SUNFLOWER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.KELP_WITH_SUNFLOWER_SEED.get())));

        foodShapeless(ModItems.LAVA_BRULEE)
                .requires(Items.LAVA_BUCKET)
                .requires(ModItemTags.OIL)
                .requires(Items.SUGAR)
                .requires(Items.MAGMA_CREAM)
                .unlockedBy(Items.LAVA_BUCKET)
                .unlockedBy(ModItemTags.OIL)
                .unlockedBy(Items.SUGAR)
                .unlockedBy(Items.MAGMA_CREAM)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.LAVA_BRULEE.get())));

        foodShapeless(ModItems.VERDANT_NAMA_CHOCO, 2)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .requires(ModItemTags.MILK)
                .requires(ItemTags.LEAVES)
                .requires(ItemTags.LEAVES)
                .unlockedBy(Items.COCOA_BEANS)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(ItemTags.LEAVES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.VERDANT_NAMA_CHOCO.get())));

        foodShapeless(ModItems.LUSH_SALAD)
                .requires(Items.BOWL)
                .requires(Items.GLOW_BERRIES)
                .requires(
                        Ingredient.of(
                                Items.AZALEA_LEAVES,
                                Items.FLOWERING_AZALEA_LEAVES,
                                Items.AZALEA,
                                Items.FLOWERING_AZALEA))
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy(Tags.Items.MUSHROOMS)
                .unlockedBy(Items.BOWL)
                .unlockedBy(Items.GLOW_BERRIES)
                .unlockedBy(Items.AZALEA)
                .unlockedBy(Items.AZALEA_LEAVES)
                .unlockedBy(Items.FLOWERING_AZALEA)
                .unlockedBy(Items.FLOWERING_AZALEA_LEAVES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.LUSH_SALAD.get())));

        simpleMiscShapeless(ModItems.MEAD_BASE.get(), 4, null)
                .group("Base")
                .requires(ModItems.MEAD.get())
                .unlockedBy(getHasName(ModItems.MEAD.get()), has(ModItems.MEAD.get()))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MEAD_BASE.get())));

        foodShapeless(ModItems.MILK, 8)
                .requires(Items.MILK_BUCKET)
                .unlockedBy(Items.MILK_BUCKET)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MILK.get())));

        simpleMiscShapeless(Items.MILK_BUCKET, null)
                .requires(Items.BUCKET)
                .requires(ModItems.MILK.get())
                .requires(ModItems.MILK.get())
                .requires(ModItems.MILK.get())
                .requires(ModItems.MILK.get())
                .requires(ModItems.MILK.get())
                .requires(ModItems.MILK.get())
                .requires(ModItems.MILK.get())
                .requires(ModItems.MILK.get())
                .unlockedBy(getHasName(ModItems.MILK.get()), has(ModItems.MILK.get()))
                .save(recipeOutput, RL(getSimpleRecipeName(Items.MILK_BUCKET)));

        foodShapeless(ModItems.MOSS_FRIED_LAMB_CUTLETS)
                .requires(Items.MOSS_BLOCK)
                .requires(Items.MOSS_BLOCK)
                .requires(ModItemTags.SALT)
                .requires(ModItems.CARROT_SPICES.get())
                .requires(ModItemTags.COOKED_MUTTON)
                .unlockedBy(Items.MOSS_BLOCK)
                .unlockedBy(ModItemTags.SALT)
                .unlockedBy(ModItems.CARROT_SPICES.get())
                .unlockedBy(ModItemTags.COOKED_MUTTON)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MOSS_FRIED_LAMB_CUTLETS.get())));

        foodShapeless(ModItems.PICKLED_SEA_PICKLES, 2)
                .requires(Items.SEA_PICKLE)
                .requires(Items.SEA_PICKLE)
                .requires(Items.SEA_PICKLE)
                .requires(Items.SEA_PICKLE)
                .requires(ModItemTags.SALT)
                .requires(ModItemTags.SALT)
                .unlockedBy(Items.SEA_PICKLE)
                .unlockedBy(ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.PICKLED_SEA_PICKLES.get())));

        foodShapeless(ModItems.POPACORN)
                .requires(ModItemTags.ACORN)
                .requires(ModItemTags.ACORN)
                .requires(ModItemTags.ACORN)
                .requires(ModItemTags.ACORN)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .unlockedBy(ModItemTags.ACORN)
                .unlockedBy(ModItemTags.OIL)
                .unlockedBy(ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.POPACORN.get())));

        foodShapeless(ModItems.RAW_BEEF_IN_DRIPLEAF)
                .requires(Items.BIG_DRIPLEAF)
                .requires(Items.BIG_DRIPLEAF)
                .requires(ModItemTags.RAW_BEEF)
                .requires(ModItemTags.SALT)
                .requires(ModItems.CARROT_SPICES.get())
                .unlockedBy(Items.BIG_DRIPLEAF)
                .unlockedBy(ModItemTags.RAW_BEEF)
                .unlockedBy(ModItemTags.SALT)
                .unlockedBy(ModItems.CARROT_SPICES.get())
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_BEEF_IN_DRIPLEAF.get())));

        foodShapeless(ModItems.RAW_VEGAN_BEEF)
                .requires(Items.PUMPKIN)
                .requires(Items.PUMPKIN)
                .requires(ModItems.CARROT_SPICES.get())
                .requires(ModItems.CARROT_SPICES.get())
                .unlockedBy(Items.PUMPKIN)
                .unlockedBy(ModItems.CARROT_SPICES.get())
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_VEGAN_BEEF.get())));

        foodShapeless(ModItems.RAW_VEGAN_MUTTON)
                .requires(ModItems.GEM_CARROT.get())
                .requires(ModItems.GEM_CARROT.get())
                .requires(ModItems.BIRCH_SAP.get())
                .requires(ModItems.BIRCH_SAP.get())
                .unlockedBy(ModItems.GEM_CARROT.get())
                .unlockedBy(ModItems.BIRCH_SAP.get())
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_VEGAN_MUTTON.get())));

        foodShapeless(ModItems.RAW_VEGAN_PORK)
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.OIL)
                .unlockedBy(Tags.Items.MUSHROOMS)
                .unlockedBy(ModItemTags.OIL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_VEGAN_PORK.get())));

        foodShapeless(ModItems.RICE_CAKE, 2)
                .requires(ModItemTags.CROPS_RICE)
                .requires(Items.SUGAR)
                .unlockedBy(ModItemTags.CROPS_RICE)
                .unlockedBy(Items.SUGAR)
                .save(notTagEmptyConsumer, RL(getSimpleRecipeName(ModItems.RICE_CAKE.get())));

        simpleMiscShapeless(ModItems.RUM_BASE, 4, null)
                .group("Base")
                .requires(ModItems.RUM.get())
                .unlockedBy(getHasName(ModItems.RUM.get()), has(ModItems.RUM.get()))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RUM_BASE.get())));

        foodShapeless(ModItems.SASHIMI)
                .requires(ModItemTags.FOOD_RAW_FISH)
                .unlockedBy(ModItemTags.FOOD_RAW_FISH)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SASHIMI.get())));

        foodShapeless(ModItems.SEED_PIE, 2)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Tags.Items.CROPS_WHEAT)
                .unlockedBy(Tags.Items.SEEDS)
                .unlockedBy(Tags.Items.CROPS_WHEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SEED_PIE.get())));

        foodShapeless(ModItems.SIRLOIN_STEAK)
                .requires(ModItemTags.COOKED_BEEF)
                .requires(Tags.Items.CROPS_NETHER_WART)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .requires(ModItems.CARROT_SPICES.get())
                .unlockedBy(ModItemTags.COOKED_BEEF)
                .unlockedBy(Tags.Items.CROPS_NETHER_WART)
                .unlockedBy(ModItemTags.OIL)
                .unlockedBy(ModItemTags.SALT)
                .unlockedBy(ModItems.CARROT_SPICES.get())
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SIRLOIN_STEAK.get())));

        foodShapeless(ModItems.SOOTHING_TEA)
                .requires(Items.SPORE_BLOSSOM)
                .requires(ModItems.ICE_CUBES.get())
                .requires(ModItems.ICE_CUBES.get())
                .requires(ModItems.WATER.get())
                .unlockedBy(Items.SPORE_BLOSSOM)
                .unlockedBy(ModItems.ICE_CUBES)
                .unlockedBy(ModItems.WATER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SOOTHING_TEA.get())));

        foodShapeless(ModItems.SWEET_BERRY_MILK, 2)
                .requires(ModItemTags.MILK)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SUGAR)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(Items.SWEET_BERRIES)
                .unlockedBy(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SWEET_BERRY_MILK.get())));

        foodShapeless(ModItems.SWEET_BERRY_TART)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.SWEET_BERRIES)
                .requires(ModItemTags.MILK)
                .requires(Items.SUGAR)
                .unlockedBy(Tags.Items.CROPS_WHEAT)
                .unlockedBy(Items.SWEET_BERRIES)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SWEET_BERRY_TART.get())));

        foodShapeless(ModItems.SWEET_ROLL)
                .requires(ModItemTags.BREAD)
                .requires(ModItemTags.SALT)
                .requires(Tags.Items.EGGS)
                .unlockedBy(ModItemTags.BREAD)
                .unlockedBy(ModItemTags.SALT)
                .unlockedBy(Tags.Items.EGGS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SWEET_ROLL.get())));

        foodShapeless(ModItems.TRAVELERS_SALAD)
                .requires(Items.BOWL)
                .requires(Items.CACTUS)
                .requires(Items.KELP)
                .requires(Items.KELP)
                .unlockedBy(Items.BOWL)
                .unlockedBy(Items.CACTUS)
                .unlockedBy(Items.KELP)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.TRAVELERS_SALAD.get())));

        foodShapeless(ModItems.ULTRA_SUPER_DELICIOUS_CEREAL_PORRIDGE)
                .requires(Items.BOWL)
                .requires(ModItems.GRILLED_WHEATMEAL.get())
                .requires(ModItemTags.MILK)
                .requires(Items.NETHER_STAR)
                .unlockedBy(Items.BOWL)
                .unlockedBy(ModItems.GRILLED_WHEATMEAL.get())
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(Items.NETHER_STAR)
                .save(
                        recipeOutput,
                        RL(getSimpleRecipeName(ModItems.ULTRA_SUPER_DELICIOUS_CEREAL_PORRIDGE.get())));

        simpleMiscShapeless(ModItems.VODKA_BASE, 4, null)
                .group("Base")
                .requires(ModItems.VODKA.get())
                .unlockedBy(getHasName(ModItems.VODKA.get()), has(ModItems.VODKA.get()))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.VODKA_BASE.get())));

        simpleMiscShapeless(ModItems.WATER.get(), 8, null)
                .requires(Items.WATER_BUCKET)
                .unlockedBy(getHasName(Items.WATER_BUCKET), has(Items.WATER_BUCKET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.WATER.get())));

        simpleMiscShapeless(Items.WATER_BUCKET, null)
                .requires(Items.BUCKET)
                .requires(ModItems.WATER.get())
                .requires(ModItems.WATER.get())
                .requires(ModItems.WATER.get())
                .requires(ModItems.WATER.get())
                .requires(ModItems.WATER.get())
                .requires(ModItems.WATER.get())
                .requires(ModItems.WATER.get())
                .requires(ModItems.WATER.get())
                .unlockedBy(getHasName(ModItems.WATER.get()), has(ModItems.WATER.get()))
                .save(recipeOutput, RL(getSimpleRecipeName(Items.WATER_BUCKET)));

        foodShapeless(ModItems.WOODLAND_TATER_PUREE)
                .requires(Items.BAKED_POTATO)
                .requires(ModItems.MILK.get())
                .requires(ModItems.BIRCH_SAP.get())
                .requires(Items.BOWL)
                .unlockedBy(Items.BAKED_POTATO)
                .unlockedBy(ModItems.MILK.get())
                .unlockedBy(ModItems.BIRCH_SAP.get())
                .unlockedBy(Items.BOWL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.WOODLAND_TATER_PUREE.get())));
    }

    protected static ModShapelessRecipeBuilder foodShapeless(RegistryObject<Item> food) {
        return foodShapeless(food, 1);
    }

    protected static ModShapelessRecipeBuilder foodShapeless(RegistryObject<Item> food, int count) {
        return ModShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, food.get(), count);
    }

    protected static ResourceLocation RL(String string) {
        return new ResourceLocation(KK, string);
    }

    protected static TagKey<Item> tag(String namespace, String tag) {
        return ItemTags.create(new ResourceLocation(namespace, tag));
    }

    protected static ShapelessRecipeBuilder simpleMiscShapeless(
            RegistryObject<?> registryObject, @Nullable TagKey<Item> tagKey) {
        return simpleMiscShapeless(registryObject, 1, tagKey);
    }

    protected static ShapelessRecipeBuilder simpleMiscShapeless(
            RegistryObject<?> registryObject, int count, @Nullable TagKey<Item> tagKey) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, (ItemLike) registryObject.get(), count)
                .unlockedBy(getHasName((ItemLike) registryObject.get()), tagKey == null ? has((ItemLike) registryObject.get()) : has(tagKey));
    }

    protected static ShapelessRecipeBuilder simpleMiscShapeless(
            ItemLike itemLike, @Nullable TagKey<Item> tagKey) {
        return simpleMiscShapeless(itemLike, 1, tagKey);
    }

    protected static ShapelessRecipeBuilder simpleMiscShapeless(
            ItemLike itemLike, int count, @Nullable TagKey<Item> tagKey) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemLike, count)
                .unlockedBy(getHasName(itemLike), tagKey == null ? has(itemLike) : has(tagKey));
    }


    protected static ShapedRecipeBuilder simpleMiscShaped(
            RegistryObject<?> registryObject, @Nullable TagKey<Item> tagKey) {
        return simpleMiscShaped((ItemLike) registryObject.get(), 1, tagKey);
    }

    protected static ShapedRecipeBuilder simpleMiscShaped(
            RegistryObject<?> registryObject, int count, @Nullable TagKey<Item> tagKey) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, (ItemLike) registryObject.get(), count)
                .unlockedBy(getHasName((ItemLike) registryObject.get()), tagKey == null ? has((ItemLike) registryObject.get()) : has(tagKey));
    }

    protected static ShapedRecipeBuilder simpleMiscShaped(
            ItemLike itemLike, @Nullable TagKey<Item> tagKey) {
        return simpleMiscShaped(itemLike, 1, tagKey);
    }
    protected static ShapedRecipeBuilder simpleFoodShaped(
            ItemLike itemLike, @Nullable TagKey<Item> tagKey) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, itemLike, 1)
                .unlockedBy(getHasName(itemLike), tagKey == null ? has(itemLike) : has(tagKey));
    }

    protected static ShapedRecipeBuilder simpleMiscShaped(
            ItemLike itemLike, int count, @Nullable TagKey<Item> tagKey) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, itemLike, count)
                .unlockedBy(getHasName(itemLike), tagKey == null ? has(itemLike) : has(tagKey));
    }

    protected static void smokingCooking(
            Consumer<FinishedRecipe> recipeOutput,
            int cookingTime,
            ItemLike material,
            ItemLike result,
            float experience) {
        RecipeProvider.simpleCookingRecipe(
                recipeOutput,
                "smoking",
                RecipeSerializer.SMOKING_RECIPE,
                cookingTime,
                material,
                result,
                experience);
    }

    protected static void campfireCooking(
            Consumer<FinishedRecipe> recipeOutput, int cookingTime, ItemLike material, ItemLike result) {
        RecipeProvider.simpleCookingRecipe(
                recipeOutput,
                "campfire_cooking",
                RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                cookingTime,
                material,
                result,
                0.35F);
    }

    protected static void KKStoneCutterResultFromBase(
            Consumer<FinishedRecipe> recipeOutput,
            RecipeCategory category,
            ItemLike result,
            ItemLike material,
            int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, resultCount)
                .unlockedBy(getHasName(material), has(material))
                .save(
                        recipeOutput,
                        getConversionRecipeName(result, material)
                                + "_stonecutting"
                                + "_"
                                + resultCount);
    }

    public static String getPlateRecipeName(ItemLike itemLike) {
        return "plate_" + getSimpleRecipeName(itemLike);
    }

    //    protected static void createIngotRecipes(Consumer<FinishedRecipe> c, RegistryObject<Item>
    // nugget, RegistryObject<Item> ingot, RegistryObject<Block> block) {
    //        Item n = nugget.get();
    //        Item i = ingot.get();
    //        Block b = block.get();
    //        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, i,
    // 9).requires(b).group(null).unlockedBy(getHasName(b), has(b)).save(c, new
    // ResourceLocation(NebulaChronicles.MODID, getConversionRecipeName(i, b)));
    //        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, b).define('#',
    // i).pattern("###").pattern("###").pattern("###").group(null).unlockedBy(getHasName(i),
    // has(i)).save(c, new ResourceLocation(NebulaChronicles.MODID, getSimpleRecipeName(b)));
    //        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, n,
    // 9).requires(i).group(null).unlockedBy(getHasName(i), has(i)).save(c, new
    // ResourceLocation(NebulaChronicles.MODID, getConversionRecipeName(n, i)));
    //        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, i).define('#',
    // n).pattern("###").pattern("###").pattern("###").group(null).unlockedBy(getHasName(n),
    // has(n)).save(c, new ResourceLocation(NebulaChronicles.MODID, getConversionRecipeName(i, n)));
    //    }
}
