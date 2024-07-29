package io.github.tt432.kitchenkarrot.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * @author DustW
 **/
public class ModItemTags {
    // mod Tags
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
    public static final TagKey<Item> COOKED_MEAT =
            ItemTags.create(ResourceLocation.parse("kitchenkarrot:cooked_meat"));

    // neoforge Tags
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
}
