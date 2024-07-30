package io.github.tt432.kitchenkarrot.datagen.provider;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.datagen.provider.recipe.AirCompressorRecipeBuilder;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.conditions.TagEmptyCondition;
import net.neoforged.neoforge.registries.DeferredItem;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected static final String KK = Kitchenkarrot.MOD_ID;
    protected static final String OIL = "has_oil";
    protected static final String SALT = "has_salt";
    protected static final Criterion<InventoryChangeTrigger.TriggerInstance> HAS_OIL =
            has(ModItemTags.OIL);
    protected static final Criterion<InventoryChangeTrigger.TriggerInstance> HAS_SALT =
            has(ModItemTags.SALT);

    public ModRecipeProvider(
            PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        // Air Compressor
        genAirCompressor(recipeOutput);

        // FoodShapeless
        genFoodShapeless(recipeOutput);

        // Shapeless
        genShapeless(recipeOutput);

        // Shaped
        genShaped(recipeOutput);

        // Campfire Cooking
        genCampfireCooking(recipeOutput);

        // Smoking
        genSmokingCooking(recipeOutput);

        // stoneCutting
        genStoneCutting(recipeOutput);

        // smithingTransform
        genSmithingTransform(recipeOutput);
    }

    private void genAirCompressor(RecipeOutput recipeOutput) {
        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.CANNED_BEEF_POTATO,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(ModItemTags.OIL),
                                Ingredient.of(ModItemTags.SALT),
                                Ingredient.of(ModItemTags.COOKED_BEEF),
                                Ingredient.of(Items.POTATO)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_BEEF_POTATO)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.CANNED_CANDIED_APPLE,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(Items.SUGAR),
                                Ingredient.of(Items.HONEY_BOTTLE),
                                Ingredient.of(Items.APPLE),
                                Ingredient.of(Items.APPLE)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_CANDIED_APPLE)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.CANNED_MUTTON_PUMPKIN,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(ModItemTags.OIL),
                                Ingredient.of(ModItemTags.SALT),
                                Ingredient.of(ModItemTags.COOKED_MUTTON),
                                Ingredient.of(Items.PUMPKIN)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_MUTTON_PUMPKIN)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.CANNED_PORK_BEETROOT,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(ModItemTags.OIL),
                                Ingredient.of(ModItemTags.SALT),
                                Ingredient.of(ModItemTags.COOKED_PORK),
                                Ingredient.of(Items.BEETROOT)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_PORK_BEETROOT)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.CANNED_SWEET_BERRY_MILK,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(Items.SUGAR),
                                Ingredient.of(Items.SUGAR),
                                Ingredient.of(ModItems.SWEET_BERRY_MILK),
                                Ingredient.of(ModItems.SWEET_BERRY_MILK)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CANNED_SWEET_BERRY_MILK)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.CORAL_COKE,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(Tags.Items.BUCKETS_WATER),
                                Ingredient.of(Items.SUGAR),
                                Ingredient.of(ModItems.ICE_CUBES),
                                Ingredient.of(ModItemTags.CORALS)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CORAL_COKE)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.DANDELION_COKE,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(Tags.Items.BUCKETS_WATER),
                                Ingredient.of(Items.SUGAR),
                                Ingredient.of(ModItems.ICE_CUBES),
                                Ingredient.of(Items.DANDELION)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.DANDELION_COKE)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.DRAGON_BREATH_COKE,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(Tags.Items.BUCKETS_WATER),
                                Ingredient.of(Items.SUGAR),
                                Ingredient.of(ModItems.ICE_CUBES),
                                Ingredient.of(Items.DRAGON_BREATH)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.DRAGON_BREATH_COKE)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.KELP_SODA,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(Tags.Items.BUCKETS_WATER),
                                Ingredient.of(Tags.Items.BUCKETS_WATER),
                                Ingredient.of(Items.KELP),
                                Ingredient.of(Items.KELP)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.KELP_SODA)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.LIGHT_SODA,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(Tags.Items.BUCKETS_WATER),
                                Ingredient.of(Tags.Items.BUCKETS_WATER),
                                Ingredient.of(ItemTags.LEAVES),
                                Ingredient.of(ItemTags.LEAVES)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.LIGHT_SODA)));

        AirCompressorRecipeBuilder.airCompressor(
                        ModItems.TWISTING_SODA,
                        NonNullList.of(
                                Ingredient.EMPTY,
                                Ingredient.of(Tags.Items.BUCKETS_WATER),
                                Ingredient.of(Tags.Items.BUCKETS_WATER),
                                Ingredient.of(Items.TWISTING_VINES),
                                Ingredient.of(Items.TWISTING_VINES)),
                        100,
                        Ingredient.of(ModItems.EMPTY_CAN))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.TWISTING_SODA)));
    }

    private void genSmithingTransform(RecipeOutput recipeOutput) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.IRON_NUGGET),
                        Ingredient.of(Items.BUCKET),
                        Ingredient.of(Items.IRON_NUGGET),
                        RecipeCategory.MISC,
                        ModItems.SHAKER.get())
                .unlocks(getHasName(ModItems.SHAKER), has(ModItems.SHAKER))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SHAKER)));
    }

    private void genFoodShapeless(RecipeOutput recipeOutput) {
        foodShapeless(ModItems.PHANTOM_STEW)
                .requires(Items.BOWL)
                .requires(ModItemTags.MILK)
                .requires(ModItems.CARROT_SPICES)
                .requires(Items.BONE)
                .requires(Items.PHANTOM_MEMBRANE, 2)
                .unlockedBy(ModItemTags.MILK)
                .unlockedBy(ModItems.CARROT_SPICES)
                .unlockedBy(Items.PHANTOM_MEMBRANE)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.PHANTOM_STEW.get())));
        foodShapeless(ModItems.STONE_SHORE_QUICHE)
                .requires(Items.WHEAT, 2)
                .requires(Items.TURTLE_EGG)
                .requires(ModItems.CHEESE_SLICE)
                .requires(Items.POPPY)
                .requires(ModItems.CARROT_SPICES)
                .requires(ModItemTags.SALT)
                .unlockedBy(Items.TURTLE_EGG)
                .unlockedBy(ModItems.CHEESE_SLICE)
                .unlockedBy(Items.POPPY)
                .unlockedBy(SALT, HAS_SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.STONE_SHORE_QUICHE.get())));
        foodShapeless(ModItems.CRIMSON_FUNGI_SPRING_ROLL, 2)
                .requires(Items.WHEAT)
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
                .requires(Items.WHEAT)
                .requires(tag("forge", "eggs"))
                .requires(Items.SWEET_BERRIES)
                .requires(Items.COCOA_BEANS)
                .requires(Items.SUGAR)
                .requires(ModItemTags.ICE_CUBES)
                .unlockedBy(Items.SWEET_BERRIES)
                .unlockedBy(Items.COCOA_BEANS)
                .unlockedBy(ModItemTags.ICE_CUBES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BUCHE_DE_NOEL.get())));
        foodShapeless(ModItems.MIXED_NUTS_MOONCAKE)
                .requires(Items.WHEAT)
                .requires(ModItemTags.ACORN)
                .requires(ModItems.BIRCH_SAP)
                .requires(Items.SUGAR)
                .unlockedBy(ModItemTags.ACORN)
                .unlockedBy(ModItems.BIRCH_SAP)
                .unlockedBy(Items.WHEAT)
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
                .requires(ModItems.CHEESE_SLICE)
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

    private void genStoneCutting(RecipeOutput recipeOutput) {
        RecipeProvider.stonecutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.ACORN.get(), Items.OAK_SAPLING);
        RecipeProvider.stonecutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.PLATE_PIECES, ModItems.EMPTY_PLATE, 3);

        KKStoneCutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.ICE_CUBES, Items.ICE, 1);
        KKStoneCutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.ICE_CUBES, Items.PACKED_ICE, 4);
        KKStoneCutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.ICE_CUBES, Items.BLUE_ICE, 8);
    }

    private void genSmokingCooking(RecipeOutput recipeOutput) {
        smokingCooking(recipeOutput, 100, Items.WHEAT, ModItems.GRILLED_WHEATMEAL, 0.1F);
        smokingCooking(
                recipeOutput, 100, ModItems.RAW_VEGAN_BEEF, ModItems.COOKED_VEGAN_BEEF, 0.5F);
        smokingCooking(
                recipeOutput, 100, ModItems.RAW_BEEF_IN_DRIPLEAF, ModItems.BEEF_IN_DRIPLEAF, 0.5F);
        smokingCooking(
                recipeOutput, 100, ModItems.RAW_VEGAN_MUTTON, ModItems.COOKED_VEGAN_MUTTON, 0.5F);
        smokingCooking(
                recipeOutput, 100, ModItems.RAW_VEGAN_PORK, ModItems.COOKED_VEGAN_PORK, 0.5F);
        smokingCooking(recipeOutput, 400, ModItems.RAW_SWEET_LOAF, ModItems.SWEET_LOAF, 1F);
    }

    private void genCampfireCooking(RecipeOutput recipeOutput) {
        campfireCooking(recipeOutput, 600, ModItems.RAW_VEGAN_BEEF, ModItems.COOKED_VEGAN_BEEF);
        campfireCooking(
                recipeOutput, 600, ModItems.RAW_BEEF_IN_DRIPLEAF, ModItems.BEEF_IN_DRIPLEAF);
        campfireCooking(recipeOutput, 600, Items.WHEAT, ModItems.GRILLED_WHEATMEAL);
        campfireCooking(recipeOutput, 600, ModItems.RAW_VEGAN_MUTTON, ModItems.COOKED_VEGAN_MUTTON);
        campfireCooking(recipeOutput, 600, ModItems.RAW_VEGAN_PORK, ModItems.COOKED_VEGAN_PORK);
    }

    private void genShaped(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.EMPTY_PLATE.get(), 2)
                .group(KK)
                .define('2', Items.BRICK)
                .define('1', Items.IRON_NUGGET)
                .pattern("121")
                .unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.EMPTY_PLATE.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.KNIFE.get())
                .group(KK)
                .define('#', Items.IRON_SWORD)
                .define('n', Items.IRON_NUGGET)
                .pattern(" #")
                .pattern("n ")
                .unlockedBy(getHasName(Items.IRON_SWORD), has(Items.IRON_SWORD))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.KNIFE.get())));
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.ENCHANTED_CHEESE.get())
                .group(KK)
                .define('#', ModItems.CHEESE_SLICE.get())
                .define('-', Items.GOLD_NUGGET)
                .pattern("---")
                .pattern("-#-")
                .pattern("---")
                .unlockedBy(
                        getHasName(ModItems.CHEESE_SLICE.get()), has(ModItems.CHEESE_SLICE.get()))
                .unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ENCHANTED_CHEESE.get())));

        simpleMiscShaped(ModBlocks.ACORN_OIL, 2, ModItemTags.ACORN)
                .pattern("* ")
                .pattern(" *")
                .define('*', ModItemTags.ACORN)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.ACORN_OIL)));

        simpleMiscShaped(ModBlocks.AIR_COMPRESSOR, null)
                .pattern("1")
                .pattern("2")
                .pattern("3")
                .define('1', Items.BLUE_DYE)
                .define('2', Items.MINECART)
                .define('3', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.AIR_COMPRESSOR)));

        simpleMiscShaped(ModItems.BAMBOO_POTATO, null)
                .pattern("123")
                .pattern(" 0 ")
                .define('0', Items.BAMBOO)
                .define('1', Tags.Items.EGGS)
                .define('2', Items.BAKED_POTATO)
                .define('3', ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BAMBOO_POTATO)));

        simpleMiscShaped(ModBlocks.BREWING_BARREL, null)
                .pattern(" B ")
                .pattern("---")
                .define('B', Items.BARREL)
                .define('-', Items.COPPER_INGOT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.BREWING_BARREL)));

        simpleMiscShaped(ModItems.CHORUS_MOUSSE, null)
                .pattern("121")
                .pattern("343")
                .pattern("555")
                .define('1', Items.CHORUS_FLOWER)
                .define('2', Items.BONE_MEAL)
                .define('3', Items.SUGAR)
                .define('4', ModItems.ICE_CUBES)
                .define('5', Tags.Items.CROPS_WHEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CHORUS_MOUSSE)));

        simpleMiscShaped(ModBlocks.CHORUS_OIL, 2, null)
                .pattern("* ")
                .pattern(" *")
                .define('*', Items.CHORUS_FLOWER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.CHORUS_OIL)));

        simpleMiscShaped(ModBlocks.COASTER, null)
                .pattern("* *")
                .pattern(" # ")
                .pattern("* *")
                .define('*', Items.STICK)
                .define('#', Items.IRON_NUGGET)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.COASTER)));

        simpleMiscShaped(ModItems.DUNGEON_PIZZA, null)
                .pattern("121")
                .pattern("333")
                .pattern("444")
                .define('1', Tags.Items.BUCKETS_MILK)
                .define('2', ModItemTags.SALT)
                .define('3', Ingredient.of(Items.ROTTEN_FLESH, Items.SPIDER_EYE))
                .define('4', Tags.Items.CROPS_WHEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.DUNGEON_PIZZA)));

        simpleMiscShaped(ModItems.EMPTY_CAN, 4, null)
                .pattern(" 1 ")
                .pattern("121")
                .pattern(" 1 ")
                .define('1', Items.IRON_NUGGET)
                .define('2', Items.BOWL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.EMPTY_CAN)));

        simpleMiscShaped(ModItems.FEAST_PIZZA, null)
                .pattern("121")
                .pattern("333")
                .pattern("444")
                .define('1', Tags.Items.BUCKETS_MILK)
                .define('2', ModItemTags.OIL)
                .define('3', ModItemTags.MEAT)
                .define('4', Tags.Items.CROPS_WHEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FEAST_PIZZA)));

        simpleMiscShaped(ModItems.FISHERMENS_DELIGHT, null)
                .pattern("123")
                .pattern(" 0 ")
                .define('0', Items.BOWL)
                .define('1', ModItems.BIRCH_SAP)
                .define('2', Items.COOKED_SALMON)
                .define('3', Items.KELP)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FISHERMENS_DELIGHT)));

        simpleMiscShaped(ModItems.GRILLED_FISH_AND_CACTUS, null)
                .pattern("123")
                .pattern(" 0 ")
                .define('0', Items.CACTUS)
                .define('1', ModItemTags.SALT)
                .define('2', Ingredient.of(Tags.Items.FOODS_COOKED_FISH))
                .define('3', ModItemTags.GRASS_SPICES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.GRILLED_FISH_AND_CACTUS)));

        simpleMiscShaped(ModItems.MONSTER_LASAGNA, null)
                .pattern("11")
                .pattern("22")
                .pattern("33")
                .define('1', Tags.Items.BUCKETS_MILK)
                .define('2', Items.ROTTEN_FLESH)
                .define('3', Tags.Items.CROPS_WHEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MONSTER_LASAGNA)));

        simpleMiscShaped(ModItems.EMPTY_PLATE, null)
                .pattern("11")
                .pattern("11")
                .define('1', ModItems.PLATE_PIECES)
                .save(
                        recipeOutput,
                        RL(getConversionRecipeName(ModItems.EMPTY_PLATE, ModItems.PLATE_PIECES)));

        simpleMiscShaped(ModItems.RAW_SWEET_LOAF, null)
                .pattern("123")
                .pattern("000")
                .define('0', Items.BREAD)
                .define('1', Tags.Items.BUCKETS_MILK)
                .define('2', Items.SUGAR)
                .define('3', ModItemTags.OIL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_SWEET_LOAF)));

        simpleMiscShaped(ModBlocks.ROCK_SALT, 2, null)
                .pattern("* ")
                .pattern(" *")
                .define('*', ModItemTags.SALT_ROCK)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.ROCK_SALT)));

        simpleMiscShaped(ModBlocks.SEA_SALT, 2, null)
                .pattern("* ")
                .pattern(" #")
                .define('*', ModItems.WATER)
                .define('#', Items.BONE_MEAL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.SEA_SALT) + "_" + 2));

        simpleMiscShaped(ModItems.SHINY_PIZZA, null)
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
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SHINY_PIZZA)));

        simpleMiscShaped(ModItems.SLIME_MOUSSE, null)
                .pattern("121")
                .pattern("343")
                .pattern("555")
                .define('1', Tags.Items.SLIMEBALLS)
                .define('2', Items.BONE_MEAL)
                .define('3', Items.SUGAR)
                .define('4', ModItems.ICE_CUBES)
                .define('5', Tags.Items.CROPS_WHEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SLIME_MOUSSE)));

        simpleMiscShaped(ModBlocks.SUNFLOWER_OIL, 2, null)
                .pattern("* ")
                .pattern(" *")
                .define('*', Items.SUNFLOWER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModBlocks.SUNFLOWER_OIL)));
    }

    private void genShapeless(RecipeOutput recipeOutput) {
        RecipeOutput notTagEmptyRecipeOutput =
                recipeOutput.withConditions(
                        new NotCondition(new TagEmptyCondition(ModItemTags.CROPS_RICE)));

        ModShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.EMPTY_PLATE.get())
                .group(KK)
                .requires(ModItems.PLATE_PIECES.get(), 4)
                .unlockedBy(ModItems.PLATE_PIECES)
                .save(recipeOutput, RL("plate_from_pieces"));

        simpleMiscShapeless(ModItems.ACORN, ModItemTags.ACORN)
                .requires(Items.OAK_SAPLING)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ACORN)));

        simpleMiscShapeless(ModItems.ACORN_WINE_BASE, 4, null)
                .requires(ModItems.ACORN_WINE)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ACORN_WINE_BASE)));

        simpleMiscShapeless(ModItems.BACON_WRAPPED_POTATO, 4, null)
                .requires(ModItemTags.COOKED_PORK)
                .requires(Items.BAKED_POTATO)
                .requires(Items.BAKED_POTATO)
                .requires(ModItemTags.SALT)
                .requires(ModItemTags.OIL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BACON_WRAPPED_POTATO)));

        simpleMiscShapeless(ModItems.BEETROOT_CREPE, 2, null)
                .requires(Items.BREAD)
                .requires(Items.BEETROOT)
                .requires(Items.BEETROOT)
                .requires(Items.BEETROOT)
                .requires(ModItemTags.ACORN)
                .requires(ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BEETROOT_CREPE)));

        simpleMiscShapeless(ModItems.BEETROOT_SALAD, 2, null)
                .requires(Items.BREAD)
                .requires(Tags.Items.EGGS)
                .requires(Items.BEETROOT)
                .requires(Items.BEETROOT)
                .requires(Items.BOWL)
                .requires(Items.BOWL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BEETROOT_SALAD)));

        simpleMiscShapeless(ModItems.BIRCH_SAP, null)
                .requires(Items.BIRCH_SAPLING)
                .requires(Tags.Items.BUCKETS_WATER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BIRCH_SAP)));

        simpleMiscShapeless(ModItems.BIRCH_SAP_CHOCOLATE_BAR, null)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .requires(ModItems.BIRCH_SAP)
                .requires(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.BIRCH_SAP_CHOCOLATE_BAR)));

        simpleMiscShapeless(ModItems.CARROT_AND_CARROT, null)
                .requires(ModItems.GEM_CARROT)
                .requires(Items.CARROT)
                .requires(ModItems.CARROT_SPICES)
                .requires(Items.BOWL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CARROT_AND_CARROT)));

        simpleMiscShapeless(ModItems.CARROT_SPICES, 2, null)
                .requires(Tags.Items.CROPS_CARROT)
                .requires(Tags.Items.CROPS_CARROT)
                .requires(Items.PAPER)
                .requires(ModItemTags.GRASS_SPICES)
                .save(
                        recipeOutput,
                        RL(getConversionRecipeName(ModItems.CARROT_SPICES, Items.CARROT)));

        simpleMiscShapeless(ModItems.CARROT_SPICES, 4, null)
                .requires(ModItems.GEM_CARROT)
                .requires(Items.PAPER)
                .requires(ModItemTags.GRASS_SPICES)
                .save(
                        recipeOutput,
                        RL(getConversionRecipeName(ModItems.CARROT_SPICES, ModItems.GEM_CARROT)));

        simpleMiscShapeless(ModItems.CARROT_TART, null)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.CARROT)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CARROT_TART)));

        simpleMiscShapeless(ModItems.CHINESE_CREPE, 2, null)
                .requires(Items.BREAD)
                .requires(ModItemTags.SALT)
                .requires(ModItemTags.OIL)
                .requires(ModItems.CARROT_SPICES)
                .requires(ModItemTags.COOKED_MEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CHINESE_CREPE)));

        simpleMiscShapeless(ModItems.CHOCOLATE_CROISSANT, null)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Items.BREAD)
                .requires(Items.COCOA_BEANS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CHOCOLATE_CROISSANT)));

        //  TODO miss ModItems.CREAM_OF_MUSHROOM_SOUP

        //        simpleMiscShapeless(ModItems.CREAM_OF_MUSHROOM_SOUP, null)
        //                .requires(Tags.Items.BUCKETS_MILK)
        //                .requires(ModItemTags.OIL)
        //                .requires(Tags.Items.MUSHROOMS)
        //                .requires(Tags.Items.MUSHROOMS)
        //                .requires(Tags.Items.MUSHROOMS)
        //                .requires(Items.BOWL)
        //                .save(recipeOutput,
        // RL(getSimpleRecipeName(ModItems.CREAM_OF_MUSHROOM_SOUP)));

        simpleMiscShapeless(ModItems.CREEPER_CEREAL_PORRIDGE, null)
                .requires(Items.BOWL)
                .requires(ModItems.GRILLED_WHEATMEAL)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Items.GUNPOWDER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CREEPER_CEREAL_PORRIDGE)));

        simpleMiscShapeless(ModItems.CRISPY_BREAD_WITH_KELP, null)
                .requires(ModItemTags.SALT)
                .requires(Items.BREAD)
                .requires(Items.DRIED_KELP)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CRISPY_BREAD_WITH_KELP)));

        simpleMiscShapeless(ModItems.CROQUE_MADAME, null)
                .requires(Items.BREAD)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(ModItemTags.OIL)
                .requires(Tags.Items.EGGS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CROQUE_MADAME)));

        simpleMiscShapeless(ModItems.CURRY_UDON, null)
                .requires(Items.POTATO)
                .requires(Items.CARROT)
                .requires(Items.WHEAT)
                .requires(Items.WHEAT)
                .requires(ModItemTags.COOKED_MEAT)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .requires(ModItems.CARROT_SPICES)
                .requires(Items.BOWL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.CURRY_UDON)));

        simpleMiscShapeless(ModItems.DRUMSTICK, null)
                .requires(ModItemTags.RAW_CHICKEN)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.DRUMSTICK)));

        simpleMiscShapeless(ModItems.EGG_TART, null)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.EGG)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.EGG_TART)));

        simpleMiscShapeless(ModItems.FLOWER_CAKE, null)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.EGG)
                .requires(ItemTags.SMALL_FLOWERS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FLOWER_CAKE)));

        simpleMiscShapeless(ModItems.FRESH_SALAD, null)
                .requires(Items.BOWL)
                .requires(Items.MOSS_BLOCK)
                .requires(ItemTags.SMALL_FLOWERS)
                .requires(ItemTags.SMALL_FLOWERS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRESH_SALAD)));

        simpleMiscShapeless(ModItems.FRIED_CHICKEN_COMBO, null)
                .requires(ModItems.FRIES)
                .requires(ModItems.FRIES)
                .requires(ModItems.DRUMSTICK)
                .requires(ModItems.DRUMSTICK)
                .requires(ModItems.CARROT_SPICES)
                .requires(ModItems.CARROT_SPICES)
                .requires(Items.PAPER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRIED_CHICKEN_COMBO)));

        simpleMiscShapeless(ModItems.FRIED_PUMPKIN_CAKE, 2, null)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.PUMPKIN)
                .requires(ModItemTags.OIL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRIED_PUMPKIN_CAKE)));

        simpleMiscShapeless(ModItems.FRIES, null)
                .requires(Items.POTATO)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRIES)));

        simpleMiscShapeless(ModItems.FRUIT_CEREAL_PORRIDGE, null)
                .requires(Items.BOWL)
                .requires(ModItems.GRILLED_WHEATMEAL)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Ingredient.of(ModItemTags.FRUITS))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.FRUIT_CEREAL_PORRIDGE)));

        simpleMiscShapeless(ModItems.HI_NRG_BRULEE, null)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(ModItemTags.OIL)
                .requires(Items.REDSTONE)
                .requires(ModItemTags.FIRE_CHARGES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.HI_NRG_BRULEE)));

        simpleMiscShapeless(ModItems.HONEY_BRULEE, null)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(ModItemTags.OIL)
                .requires(Items.SUGAR)
                .requires(Items.HONEY_BOTTLE)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.HONEY_BRULEE)));

        simpleMiscShapeless(ModItems.ICE_CUBES, null)
                .requires(Items.SNOWBALL)
                .requires(ModItems.WATER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.ICE_CUBES)));

        simpleMiscShapeless(ModItems.KELP_WITH_SUNFLOWER_SEED, 8, null)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.DRIED_KELP)
                .requires(Items.SUNFLOWER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.KELP_WITH_SUNFLOWER_SEED)));

        simpleMiscShapeless(ModItems.LAVA_BRULEE, null)
                .requires(Items.LAVA_BUCKET)
                .requires(ModItemTags.OIL)
                .requires(Items.SUGAR)
                .requires(Items.MAGMA_CREAM)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.LAVA_BRULEE)));

        simpleMiscShapeless(ModItems.VERDANT_NAMA_CHOCO, 2, null)
                .requires(Items.COCOA_BEANS)
                .requires(Items.COCOA_BEANS)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(ItemTags.LEAVES)
                .requires(ItemTags.LEAVES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.VERDANT_NAMA_CHOCO)));

        simpleMiscShapeless(ModItems.LUSH_SALAD, null)
                .requires(Items.BOWL)
                .requires(Items.GLOW_BERRIES)
                .requires(
                        Ingredient.of(
                                Items.AZALEA_LEAVES,
                                Items.FLOWERING_AZALEA_LEAVES,
                                Items.AZALEA,
                                Items.FLOWERING_AZALEA))
                .requires(Tags.Items.MUSHROOMS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.LUSH_SALAD)));

        simpleMiscShapeless(ModItems.MEAD_BASE, 4, null)
                .requires(ModItems.MEAD)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MEAD_BASE)));

        simpleMiscShapeless(ModItems.MILK, 8, null)
                .requires(Items.MILK_BUCKET)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MILK)));

        simpleMiscShapeless(Items.MILK_BUCKET, null)
                .requires(Items.BUCKET)
                .requires(ModItems.MILK)
                .requires(ModItems.MILK)
                .requires(ModItems.MILK)
                .requires(ModItems.MILK)
                .requires(ModItems.MILK)
                .requires(ModItems.MILK)
                .requires(ModItems.MILK)
                .requires(ModItems.MILK)
                .save(recipeOutput, RL(getSimpleRecipeName(Items.MILK_BUCKET)));

        simpleMiscShapeless(ModItems.MOSS_FRIED_LAMB_CUTLETS, null)
                .requires(Items.MOSS_BLOCK)
                .requires(Items.MOSS_BLOCK)
                .requires(ModItemTags.SALT)
                .requires(ModItems.CARROT_SPICES)
                .requires(ModItemTags.COOKED_MUTTON)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.MOSS_FRIED_LAMB_CUTLETS)));

        simpleMiscShapeless(ModItems.PICKLED_SEA_PICKLES, 2, null)
                .requires(Items.SEA_PICKLE)
                .requires(Items.SEA_PICKLE)
                .requires(Items.SEA_PICKLE)
                .requires(Items.SEA_PICKLE)
                .requires(ModItemTags.SALT)
                .requires(ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.PICKLED_SEA_PICKLES)));

        simpleMiscShapeless(ModItems.POPACORN, null)
                .requires(ModItemTags.ACORN)
                .requires(ModItemTags.ACORN)
                .requires(ModItemTags.ACORN)
                .requires(ModItemTags.ACORN)
                .requires(Items.SUGAR)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.POPACORN)));

        simpleMiscShapeless(ModItems.RAW_BEEF_IN_DRIPLEAF, null)
                .requires(Items.BIG_DRIPLEAF)
                .requires(Items.BIG_DRIPLEAF)
                .requires(ModItemTags.RAW_BEEF)
                .requires(ModItemTags.SALT)
                .requires(ModItems.CARROT_SPICES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_BEEF_IN_DRIPLEAF)));

        simpleMiscShapeless(ModItems.RAW_VEGAN_BEEF, null)
                .requires(Items.PUMPKIN)
                .requires(Items.PUMPKIN)
                .requires(ModItems.CARROT_SPICES)
                .requires(ModItems.CARROT_SPICES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_VEGAN_BEEF)));

        simpleMiscShapeless(ModItems.RAW_VEGAN_MUTTON, null)
                .requires(ModItems.GEM_CARROT)
                .requires(ModItems.GEM_CARROT)
                .requires(ModItems.BIRCH_SAP)
                .requires(ModItems.BIRCH_SAP)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_VEGAN_MUTTON)));

        simpleMiscShapeless(ModItems.RAW_VEGAN_PORK, null)
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.OIL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RAW_VEGAN_PORK)));

        simpleMiscShapeless(ModItems.RICE_CAKE, 2, null)
                .requires(ModItemTags.CROPS_RICE)
                .requires(Items.SUGAR)
                .save(notTagEmptyRecipeOutput, RL(getSimpleRecipeName(ModItems.RICE_CAKE)));

        simpleMiscShapeless(ModItems.RUM_BASE, 4, null)
                .requires(ModItems.RUM)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.RUM_BASE)));

        simpleMiscShapeless(ModItems.SASHIMI, null)
                .requires(Tags.Items.FOODS_RAW_FISH)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SASHIMI)));

        simpleMiscShapeless(ModItems.SEED_PIE, 2, null)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.SEEDS)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Tags.Items.CROPS_WHEAT)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SEED_PIE)));

        simpleMiscShapeless(ModItems.SIRLOIN_STEAK, null)
                .requires(ModItemTags.COOKED_BEEF)
                .requires(Tags.Items.CROPS_NETHER_WART)
                .requires(ModItemTags.OIL)
                .requires(ModItemTags.SALT)
                .requires(ModItems.CARROT_SPICES)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SIRLOIN_STEAK)));

        simpleMiscShapeless(ModItems.SOOTHING_TEA, null)
                .requires(Items.SPORE_BLOSSOM)
                .requires(ModItems.ICE_CUBES)
                .requires(ModItems.ICE_CUBES)
                .requires(ModItems.WATER)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SOOTHING_TEA)));

        simpleMiscShapeless(ModItems.SWEET_BERRY_MILK, 2, null)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SWEET_BERRY_MILK)));

        simpleMiscShapeless(ModItems.SWEET_BERRY_TART, null)
                .requires(Tags.Items.CROPS_WHEAT)
                .requires(Items.SWEET_BERRIES)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Items.SUGAR)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SWEET_BERRY_TART)));

        simpleMiscShapeless(ModItems.SWEET_ROLL, null)
                .requires(ModItemTags.BREAD)
                .requires(ModItemTags.SALT)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Tags.Items.EGGS)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.SWEET_ROLL)));

        simpleMiscShapeless(ModItems.TRAVELERS_SALAD, null)
                .requires(Items.BOWL)
                .requires(Items.CACTUS)
                .requires(Items.KELP)
                .requires(Items.KELP)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.TRAVELERS_SALAD)));

        simpleMiscShapeless(ModItems.ULTRA_SUPER_DELICIOUS_CEREAL_PORRIDGE, null)
                .requires(Items.BOWL)
                .requires(ModItems.GRILLED_WHEATMEAL)
                .requires(Tags.Items.BUCKETS_MILK)
                .requires(Items.NETHER_STAR)
                .save(
                        recipeOutput,
                        RL(getSimpleRecipeName(ModItems.ULTRA_SUPER_DELICIOUS_CEREAL_PORRIDGE)));

        simpleMiscShapeless(ModItems.VODKA_BASE, 4, null)
                .requires(ModItems.VODKA)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.VODKA_BASE)));

        simpleMiscShapeless(ModItems.WATER, 8, null)
                .requires(Items.WATER_BUCKET)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.WATER)));

        simpleMiscShapeless(Items.WATER_BUCKET, null)
                .requires(Items.BUCKET)
                .requires(ModItems.WATER)
                .requires(ModItems.WATER)
                .requires(ModItems.WATER)
                .requires(ModItems.WATER)
                .requires(ModItems.WATER)
                .requires(ModItems.WATER)
                .requires(ModItems.WATER)
                .requires(ModItems.WATER)
                .save(recipeOutput, RL(getSimpleRecipeName(Items.WATER_BUCKET)));

        simpleMiscShapeless(ModItems.WOODLAND_TATER_PUREE, null)
                .requires(Items.BAKED_POTATO)
                .requires(ModItems.MILK)
                .requires(ModItems.BIRCH_SAP)
                .requires(Items.BOWL)
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.WOODLAND_TATER_PUREE)));
    }

    protected static ModShapelessRecipeBuilder foodShapeless(DeferredItem<Item> food) {
        return foodShapeless(food, 1);
    }

    protected static ModShapelessRecipeBuilder foodShapeless(DeferredItem<Item> food, int count) {
        return ModShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, food.get(), count)
                .group(KK);
    }

    protected static ResourceLocation RL(String string) {
        return ResourceLocation.fromNamespaceAndPath(KK, string);
    }

    protected static TagKey<Item> tag(String namespace, String tag) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(namespace, tag));
    }

    protected static ShapelessRecipeBuilder simpleMiscShapeless(
            ItemLike itemLike, @Nullable TagKey<Item> tagKey) {
        return simpleMiscShapeless(itemLike, 1, tagKey);
    }

    protected static ShapelessRecipeBuilder simpleMiscShapeless(
            ItemLike itemLike, int count, @Nullable TagKey<Item> tagKey) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemLike, count)
                .group(KK)
                .unlockedBy(getHasName(itemLike), tagKey == null ? has(itemLike) : has(tagKey));
    }

    protected static ShapedRecipeBuilder simpleMiscShaped(
            ItemLike itemLike, @Nullable TagKey<Item> tagKey) {
        return simpleMiscShaped(itemLike, 1, tagKey);
    }

    protected static ShapedRecipeBuilder simpleMiscShaped(
            ItemLike itemLike, int count, @Nullable TagKey<Item> tagKey) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, itemLike, count)
                .group(KK)
                .unlockedBy(getHasName(itemLike), tagKey == null ? has(itemLike) : has(tagKey));
    }

    protected static void smokingCooking(
            RecipeOutput recipeOutput,
            int cookingTime,
            ItemLike material,
            ItemLike result,
            float experience) {
        RecipeProvider.simpleCookingRecipe(
                recipeOutput,
                "smoking",
                RecipeSerializer.SMOKING_RECIPE,
                SmokingRecipe::new,
                cookingTime,
                material,
                result,
                experience);
    }

    protected static void campfireCooking(
            RecipeOutput recipeOutput, int cookingTime, ItemLike material, ItemLike result) {
        RecipeProvider.simpleCookingRecipe(
                recipeOutput,
                "campfire_cooking",
                RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                CampfireCookingRecipe::new,
                cookingTime,
                material,
                result,
                0.35F);
    }

    protected static void KKStoneCutterResultFromBase(
            RecipeOutput recipeOutput,
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
