package io.github.tt432.kitchenkarrot.datagen.provider;

import com.google.common.collect.ImmutableSet;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ModItemModelProvider extends ItemModelProvider {
    private final Set<Item> skipSet = new HashSet<>();

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Kitchenkarrot.MOD_ID, existingFileHelper);
    }

    private static final ImmutableSet<RegistryObject<Item>> IGNORES =
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
                .filter(holder -> isSkip(holder.get().asItem()))
                .forEach(this::genBlockItemModel);
    }

    protected Boolean isSkip(Item item) {
        return !skipSet.contains(item);
    }

    protected void skip(Item item) {
        skipSet.add(item);
    }

    protected void initSkip() {
        skip(ModBlocks.PLATE.get().asItem());
    }

    @NotNull
    private ItemModelBuilder tool(@NotNull RegistryObject<Item> item) {
        skip(item.get());
        ResourceLocation rl = item.getId();
        return getBuilder(rl.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture(
                        "layer0",
                        new ResourceLocation(
                                Kitchenkarrot.MOD_ID, "item/" + rl.getPath()));
    }

    protected ItemModelBuilder genBlockGenerated(RegistryObject<Block> block) {
        skip(block.get().asItem());
        return withExistingParent(
                        block.getId().getPath(),
                        new ResourceLocation("item/generated"))
                .texture(
                        "layer0",
                        new ResourceLocation(
                                Kitchenkarrot.MOD_ID, ITEM_FOLDER + "/" + block.getId().getPath()));
    }

    private ItemModelBuilder genBlockItemModel(RegistryObject<Block> block) {
        String id = block.getId().getPath();
        return withExistingParent(id, Kitchenkarrot.getModRL("block/" + id));
    }
}
