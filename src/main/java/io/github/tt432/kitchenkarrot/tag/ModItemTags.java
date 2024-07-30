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
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:salt_rock"));
    public static final TagKey<Item> FIRE_CHARGES =
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:fire_charges"));
    public static final TagKey<Item> CONTAINER_ITEM =
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:container_item"));
    public static final TagKey<Item> KNIFE_ITEM =
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:knife_item"));
    public static final TagKey<Item> BASE =
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:base"));
    public static final TagKey<Item> INTERACT_WITH_PLATE =
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:interact_with_plate"));
    public static final TagKey<Item> MEAT =
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:meat"));
    public static final TagKey<Item> GRASS_SPICES =
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:grass_spices"));
    public static final TagKey<Item> RAW_MEAT = ItemTags.create(Kitchenkarrot.getModRL("raw_meat"));
    public static final TagKey<Item> COOKED_MEAT =
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:cooked_meat"));

    // neoforge Tags
    public static final TagKey<Item> KNIVES =
            ItemTags.create(ResourceLocation.parse("c:tools/knives"));
    public static final TagKey<Item> NUTS = ItemTags.create(ResourceLocation.parse("c:nuts"));
    public static final TagKey<Item> CROPS_RICE =
            ItemTags.create(ResourceLocation.parse("c:crops/rice"));
    public static final TagKey<Item> COOKED_MUTTON =
            ItemTags.create(ResourceLocation.parse("c:cooked_mutton"));
    public static final TagKey<Item> FRUITS = ItemTags.create(ResourceLocation.parse("c:fruits"));
    public static final TagKey<Item> RAW_PORK =
            ItemTags.create(ResourceLocation.parse("c:raw_pork"));
    public static final TagKey<Item> RAW_MUTTON =
            ItemTags.create(ResourceLocation.parse("c:raw_mutton"));
    public static final TagKey<Item> RAW_BEEF =
            ItemTags.create(ResourceLocation.parse("c:raw_beef"));
    public static final TagKey<Item> RAW_CHICKEN =
            ItemTags.create(ResourceLocation.parse("c:raw_chicken"));
    public static final TagKey<Item> COOKED_CHICKEN =
            ItemTags.create(ResourceLocation.parse("c:cooked_chicken"));
    public static final TagKey<Item> COOKED_BEEF =
            ItemTags.create(ResourceLocation.parse("c:cooked_beef"));
    public static final TagKey<Item> COOKED_PORK =
            ItemTags.create(ResourceLocation.parse("c:cooked_pork"));
    public static final TagKey<Item> OIL = ItemTags.create(ResourceLocation.parse("c:cooking_oil"));
    public static final TagKey<Item> SALT = ItemTags.create(ResourceLocation.parse("c:salt"));
    public static final TagKey<Item> ICE_CUBES =
            ItemTags.create(ResourceLocation.parse("c:ice_cubes"));
    public static final TagKey<Item> ACORN =
            ItemTags.create(ResourceLocation.parse("c:nuts/acorn"));
    public static final TagKey<Item> BREAD = ItemTags.create(ResourceLocation.parse("c:bread"));
    public static final TagKey<Item> MILK = ItemTags.create(ResourceLocation.parse("c:milk"));

    public static final TagKey<Item> FOOD_MEAT =
            ItemTags.create(ResourceLocation.parse("c:food/meat"));

    public static final TagKey<Item> SQUIRREL_TEMPT_ITEMS =
            ItemTags.create(ResourceLocation.parse("ecologics:squirrel_tempt_items"));
}
