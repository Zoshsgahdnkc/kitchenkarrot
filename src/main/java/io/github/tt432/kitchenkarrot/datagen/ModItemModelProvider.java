package io.github.tt432.kitchenkarrot.datagen;

import com.google.common.collect.ImmutableSet;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ModItemModelProvider extends ItemModelProvider {
    private final Set<Item> skipSet = new HashSet<>();

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Kitchenkarrot.MOD_ID, existingFileHelper);
    }

    private static final ImmutableSet<DeferredHolder<Item, Item>> IGNORES =
            ImmutableSet.of(ModItems.KNIFE, ModItems.COCKTAIL);

    @Override
    protected void registerModels() {
        initSkip();

        for (var entry :
                ModItems.ITEMS.getEntries().stream()
                        .filter(e -> !(e.get() instanceof BlockItem))
                        .toList()) {
            if (!IGNORES.contains(entry)) {
                basicItem(entry.get());
            }
        }
        tool(ModItems.KNIFE);

        genBlockGenerated(ModBlocks.ACORN_OIL);
        genBlockGenerated(ModBlocks.CHORUS_OIL);
        genBlockGenerated(ModBlocks.SUNFLOWER_OIL);
        genBlockGenerated(ModBlocks.FINE_SALT);
        genBlockGenerated(ModBlocks.ROCK_SALT);
        genBlockGenerated(ModBlocks.SEA_SALT);

        ModBlocks.BLOCKS.getEntries().stream()
                .map(holder -> (DeferredBlock<Block>) holder)
                .filter(holder -> isSkip(holder.asItem()))
                .forEach(this::genBlockItemModel);
    }

    protected Boolean isSkip(Item item) {
        return !skipSet.contains(item);
    }

    protected void skip(Item item) {
        skipSet.add(item);
    }

    protected void initSkip() {
        skip(ModBlocks.PLATE.asItem());
    }

    @NotNull
    private ItemModelBuilder tool(@NotNull DeferredItem<Item> item) {
        skip(item.get());
        ResourceLocation rl = item.getId();
        return getBuilder(rl.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture(
                        "layer0",
                        ResourceLocation.fromNamespaceAndPath(
                                Kitchenkarrot.MOD_ID, "item/" + rl.getPath()));
    }

    protected ItemModelBuilder genBlockGenerated(DeferredBlock<Block> block) {
        skip(block.asItem());
        return withExistingParent(
                        block.getId().getPath(),
                        ResourceLocation.withDefaultNamespace("item/generated"))
                .texture(
                        "layer0",
                        ResourceLocation.fromNamespaceAndPath(
                                Kitchenkarrot.MOD_ID, ITEM_FOLDER + "/" + block.getId().getPath()));
    }

    private ItemModelBuilder genBlockItemModel(DeferredBlock<Block> block) {
        String id = block.getId().getPath();
        return withExistingParent(id, Kitchenkarrot.getModRL("block/" + id));
    }
}
