package io.github.tt432.kitchenkarrot.tag;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * @author DustW
 **/
public class ModItemTags {
    // mod Tags
    public static final TagKey<Item> VEGAN_MEAT =
            ItemTags.create(Kitchenkarrot.getModRL("vegan_meat"));
    public static final TagKey<Item> CORALS = ItemTags.create(Kitchenkarrot.getModRL("corals"));
    public static final TagKey<Item> SALT_ROCK =
            ItemTags.create(new ResourceLocation("kitchenkarrot:salt_rock"));
    public static final TagKey<Item> FIRE_CHARGES =
            ItemTags.create(new ResourceLocation("kitchenkarrot:fire_charges"));
    public static final TagKey<Item> CONTAINER_ITEM =
            ItemTags.create(new ResourceLocation("kitchenkarrot:container_item"));
    public static final TagKey<Item> KNIFE_ITEM =
            ItemTags.create(new ResourceLocation("kitchenkarrot:knife_item"));
    public static final TagKey<Item> BASE =
            ItemTags.create(new ResourceLocation("kitchenkarrot:base"));
    public static final TagKey<Item> INTERACT_WITH_PLATE =
            ItemTags.create(new ResourceLocation("kitchenkarrot:interact_with_plate"));
    public static final TagKey<Item> MEAT =
            ItemTags.create(new ResourceLocation("kitchenkarrot:meat"));
    public static final TagKey<Item> GRASS_SPICES =
            ItemTags.create(new ResourceLocation("kitchenkarrot:grass_spices"));
    public static final TagKey<Item> RAW_MEAT = ItemTags.create(Kitchenkarrot.getModRL("raw_meat"));
    public static final TagKey<Item> COOKED_MEAT =
            ItemTags.create(new ResourceLocation("kitchenkarrot:cooked_meat"));

    // neoforge Tags
    public static final TagKey<Item> KNIVES =
            ItemTags.create(new ResourceLocation("forge:tools/knives"));
    public static final TagKey<Item> NUTS = ItemTags.create(new ResourceLocation("forge:nuts"));
    public static final TagKey<Item> CROPS_RICE =
            ItemTags.create(new ResourceLocation("forge:crops/rice"));
    public static final TagKey<Item> COOKED_MUTTON =
            ItemTags.create(new ResourceLocation("forge:cooked_mutton"));
    public static final TagKey<Item> FRUITS = ItemTags.create(new ResourceLocation("forge:fruits"));
    public static final TagKey<Item> RAW_PORK =
            ItemTags.create(new ResourceLocation("forge:raw_pork"));
    public static final TagKey<Item> RAW_MUTTON =
            ItemTags.create(new ResourceLocation("forge:raw_mutton"));
    public static final TagKey<Item> RAW_BEEF =
            ItemTags.create(new ResourceLocation("forge:raw_beef"));
    public static final TagKey<Item> RAW_CHICKEN =
            ItemTags.create(new ResourceLocation("forge:raw_chicken"));
    public static final TagKey<Item> COOKED_CHICKEN =
            ItemTags.create(new ResourceLocation("forge:cooked_chicken"));
    public static final TagKey<Item> COOKED_BEEF =
            ItemTags.create(new ResourceLocation("forge:cooked_beef"));
    public static final TagKey<Item> COOKED_PORK =
            ItemTags.create(new ResourceLocation("forge:cooked_pork"));
    public static final TagKey<Item> OIL = ItemTags.create(new ResourceLocation("forge:cooking_oil"));
    public static final TagKey<Item> SALT = ItemTags.create(new ResourceLocation("forge:salt"));
    public static final TagKey<Item> ICE_CUBES =
            ItemTags.create(new ResourceLocation("forge:ice_cubes"));
    public static final TagKey<Item> ACORN =
            ItemTags.create(new ResourceLocation("forge:nuts/acorn"));
    public static final TagKey<Item> BREAD = ItemTags.create(new ResourceLocation("forge:bread"));
    public static final TagKey<Item> MILK = ItemTags.create(new ResourceLocation("forge:milk"));
    public static final TagKey<Item> WATER = ItemTags.create(new ResourceLocation("forge:water"));

    public static final TagKey<Item> FOOD_COOKED_FISH = ItemTags.create(new ResourceLocation("forge:food/cooked_fish"));
    public static final TagKey<Item> FOOD_RAW_FISH = ItemTags.create(new ResourceLocation("forge:food/raw_fish"));
    public static final TagKey<Item> FOOD_MEAT =
            ItemTags.create(new ResourceLocation("forge:food/meat"));

    public static final TagKey<Item> SQUIRREL_TEMPT_ITEMS =
            ItemTags.create(new ResourceLocation("ecologics:squirrel_tempt_items"));

}
