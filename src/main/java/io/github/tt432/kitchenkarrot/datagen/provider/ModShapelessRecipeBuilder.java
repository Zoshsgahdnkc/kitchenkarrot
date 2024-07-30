package io.github.tt432.kitchenkarrot.datagen.provider;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.Nullable;

public class ModShapelessRecipeBuilder extends ShapelessRecipeBuilder {
    public ModShapelessRecipeBuilder(RecipeCategory p_250837_, ItemLike p_251897_, int p_252227_) {
        super(p_250837_, p_251897_, p_252227_);
    }

    public static ModShapelessRecipeBuilder shapeless(
            RecipeCategory p_250714_, ItemLike p_249659_) {
        return new ModShapelessRecipeBuilder(p_250714_, p_249659_, 1);
    }

    public static ModShapelessRecipeBuilder shapeless(
            RecipeCategory p_252339_, ItemLike p_250836_, int p_249928_) {
        return new ModShapelessRecipeBuilder(p_252339_, p_250836_, p_249928_);
    }

    public ModShapelessRecipeBuilder requires(TagKey<Item> p_206420_) {
        return this.requires(Ingredient.of(p_206420_));
    }

    public ModShapelessRecipeBuilder requires(ItemLike p_126210_) {
        return this.requires(p_126210_, 1);
    }

    public ModShapelessRecipeBuilder requires(ItemLike p_126212_, int p_126213_) {
        for (int i = 0; i < p_126213_; ++i) {
            this.requires(Ingredient.of(p_126212_));
        }

        return this;
    }

    public ModShapelessRecipeBuilder requires(DeferredItem<Item> itemRegistryObject) {
        return requires(itemRegistryObject.get());
    }

    public ModShapelessRecipeBuilder requires(Ingredient p_126185_) {
        return this.requires(p_126185_, 1);
    }

    public ModShapelessRecipeBuilder requires(Ingredient p_126187_, int p_126188_) {
        return (ModShapelessRecipeBuilder) super.requires(p_126187_, p_126188_);
    }

    public ModShapelessRecipeBuilder unlockedBy(String p_126197_, Criterion<?> p_126198_) {
        return (ModShapelessRecipeBuilder) super.unlockedBy(p_126197_, p_126198_);
    }

    public ModShapelessRecipeBuilder unlockedBy(Item item) {
        return unlockedBy("has_" + item, InventoryChangeTrigger.TriggerInstance.hasItems(item));
    }

    public ModShapelessRecipeBuilder unlockedBy(DeferredItem<Item> item) {
        return unlockedBy(item.get());
    }

    public ModShapelessRecipeBuilder unlockedBy(TagKey<Item> tag) {
        return unlockedBy(
                "has_" + tag,
                InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(tag)));
    }

    public ModShapelessRecipeBuilder group(@Nullable String p_126195_) {
        return (ModShapelessRecipeBuilder) super.group(p_126195_);
    }
}
