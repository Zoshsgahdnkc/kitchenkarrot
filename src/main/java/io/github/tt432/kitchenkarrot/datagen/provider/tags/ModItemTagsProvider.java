package io.github.tt432.kitchenkarrot.datagen.provider.tags;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Kitchenkarrot.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModItemTags.SQUIRREL_TEMPT_ITEMS).add(ModItems.ACORN.get());

        tag(ModItemTags.BREAD).add(Items.BREAD);
        tag(ModItemTags.COOKED_BEEF).add(Items.COOKED_BEEF, ModItems.COOKED_VEGAN_BEEF.get());
        tag(ModItemTags.COOKED_CHICKEN).add(Items.COOKED_CHICKEN);
        tag(ModItemTags.COOKED_MUTTON).add(Items.COOKED_MUTTON, ModItems.COOKED_VEGAN_MUTTON.get());
        tag(ModItemTags.COOKED_PORK).add(Items.COOKED_PORKCHOP, ModItems.COOKED_VEGAN_PORK.get());
        tag(ModItemTags.OIL)
                .add(
                        ModBlocks.SUNFLOWER_OIL.asItem(),
                        ModBlocks.ACORN_OIL.asItem(),
                        ModBlocks.CHORUS_OIL.asItem());
        tag(ModItemTags.FRUITS).add(Items.APPLE, Items.CHORUS_FRUIT);
        tag(ModItemTags.ICE_CUBES).add(ModItems.ICE_CUBES.get());
        tag(Tags.Items.BUCKETS_MILK).add(ModItems.MILK.get());
        tag(ModItemTags.NUTS).add(ModItems.ACORN.get());
        tag(ModItemTags.RAW_BEEF).add(Items.BEEF, ModItems.RAW_VEGAN_BEEF.get());
        tag(ModItemTags.RAW_CHICKEN).add(Items.CHICKEN);
        tag(ModItemTags.RAW_MUTTON).add(Items.MUTTON, ModItems.RAW_VEGAN_MUTTON.get());
        tag(ModItemTags.RAW_PORK).add(Items.PORKCHOP, ModItems.RAW_VEGAN_PORK.get());
        tag(ModItemTags.SALT)
                .add(
                        ModBlocks.ROCK_SALT.asItem(),
                        ModBlocks.SEA_SALT.asItem(),
                        ModBlocks.FINE_SALT.asItem());
        tag(Tags.Items.BUCKETS_WATER).add(ModItems.WATER.get());
        tag(ModItemTags.BASE)
                .add(
                        ModItems.RUM_BASE.get(),
                        ModItems.ACORN_WINE_BASE.get(),
                        ModItems.MEAD_BASE.get(),
                        ModItems.VODKA_BASE.get());
        tag(ModItemTags.CONTAINER_ITEM).add(Items.GLASS_BOTTLE, ModItems.EMPTY_CAN.get());
        tag(ModItemTags.COOKED_MEAT)
                .addTags(
                        ModItemTags.COOKED_BEEF,
                        ModItemTags.COOKED_CHICKEN,
                        ModItemTags.COOKED_MUTTON,
                        ModItemTags.COOKED_PORK,
                        Tags.Items.FOODS_COOKED_FISH);
        tag(ModItemTags.CORALS)
                .add(
                        Items.TUBE_CORAL_BLOCK,
                        Items.TUBE_CORAL,
                        Items.BRAIN_CORAL_BLOCK,
                        Items.BRAIN_CORAL,
                        Items.BUBBLE_CORAL_BLOCK,
                        Items.BUBBLE_CORAL,
                        Items.FIRE_CORAL_BLOCK,
                        Items.FIRE_CORAL,
                        Items.HORN_CORAL_BLOCK,
                        Items.HORN_CORAL);
        tag(ModItemTags.FIRE_CHARGES).add(Items.FIRE_CHARGE);
        tag(ModItemTags.GRASS_SPICES).add(Items.FERN, Items.GRASS_BLOCK);
        tag(ModItemTags.INTERACT_WITH_PLATE).addTags(ModItemTags.KNIVES);
        tag(ModItemTags.KNIFE_ITEM).add(ModItems.KNIFE.get());
        tag(ModItemTags.MEAT).addTags(ModItemTags.RAW_MEAT, ModItemTags.COOKED_MEAT);
        tag(ModItemTags.RAW_MEAT)
                .addTags(
                        ModItemTags.RAW_BEEF,
                        ModItemTags.RAW_CHICKEN,
                        ModItemTags.RAW_MUTTON,
                        ModItemTags.RAW_PORK,
                        Tags.Items.FOODS_RAW_FISH);
        tag(ModItemTags.SALT_ROCK)
                .add(Items.CALCITE, Items.TUFF, Items.MOSSY_COBBLESTONE, Items.DIORITE);
        tag(ModItemTags.VEGAN_MEAT)
                .add(
                        ModItems.RAW_VEGAN_BEEF.get(),
                        ModItems.COOKED_VEGAN_BEEF.get(),
                        ModItems.RAW_VEGAN_PORK.get(),
                        ModItems.COOKED_VEGAN_PORK.get(),
                        ModItems.RAW_VEGAN_MUTTON.get(),
                        ModItems.COOKED_VEGAN_MUTTON.get());

        // food
        tag(Tags.Items.FOODS_COOKED_FISH).add(Items.COOKED_COD, Items.COOKED_SALMON);
        tag(Tags.Items.FOODS_RAW_FISH)
                .add(Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH);
        tag(ModItemTags.FOOD_MEAT).add(ModItems.MEAD.get());

        // nuts
        tag(ModItemTags.ACORN).add(ModItems.ACORN.get());

        // tools
        tag(ModItemTags.KNIVES).add(ModItems.KNIFE.get());
    }
}
