package io.github.tt432.kitchenkarrot.datagen;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredItem;

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
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.EMPTY_PLATE.get(), 2)
                .group(KK)
                .define('b', Items.BRICK)
                .define('n', Items.IRON_NUGGET)
                .pattern("nbn")
                .unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.EMPTY_PLATE.get())));
        ModShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.EMPTY_PLATE.get())
                .group(KK)
                .requires(ModItems.PLATE_PIECES.get(), 4)
                .unlockedBy(ModItems.PLATE_PIECES)
                .save(recipeOutput, RL("plate_from_pieces"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.KNIFE.get())
                .group(KK)
                .define('#', Items.IRON_SWORD)
                .define('n', Items.IRON_NUGGET)
                .pattern(" #")
                .pattern("n ")
                .unlockedBy(getHasName(Items.IRON_SWORD), has(Items.IRON_SWORD))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(recipeOutput, RL(getSimpleRecipeName(ModItems.KNIFE.get())));

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

        // Shapeless
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

        // Shaped
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

        // Campfire Cooking
        campfireCooking(recipeOutput, 600, ModItems.RAW_VEGAN_BEEF, ModItems.COOKED_VEGAN_BEEF);
        campfireCooking(
                recipeOutput, 600, ModItems.RAW_BEEF_IN_DRIPLEAF, ModItems.BEEF_IN_DRIPLEAF);
        campfireCooking(recipeOutput, 600, Items.WHEAT, ModItems.GRILLED_WHEATMEAL);
        campfireCooking(recipeOutput, 600, ModItems.RAW_VEGAN_MUTTON, ModItems.COOKED_VEGAN_MUTTON);
        campfireCooking(recipeOutput, 600, ModItems.RAW_VEGAN_PORK, ModItems.COOKED_VEGAN_PORK);

        RecipeProvider.stonecutterResultFromBase(
                recipeOutput, RecipeCategory.MISC, ModItems.ACORN.get(), Items.OAK_SAPLING);
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
