package io.github.tt432.kitchenkarrot.datagen.provider.loot;

import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ModBlockLoot extends BlockLootSubProvider {
    private final Set<Block> skipBlocks = new HashSet<>();

    protected ModBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void add(@NotNull Block block, @NotNull LootTable.Builder builder) {
        if (skipBlocks.contains(block)) return;
        super.add(block, builder);
        skipBlocks.add(block);
    }

    @Override
    protected void generate() {
        skip();

        add(
                ModBlocks.PLATE.get(),
                LootTable.lootTable()
                        .withPool(
                                applyExplosionCondition(
                                        ModItems.PLATE_PIECES,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1))
                                                .add(
                                                        LootItem.lootTableItem(
                                                                        ModItems.PLATE_PIECES)
                                                                .apply(
                                                                        SetItemCountFunction
                                                                                .setCount(
                                                                                        BinomialDistributionGenerator
                                                                                                .binomial(
                                                                                                        3,
                                                                                                        0.5F),
                                                                                        true))))));

        ModBlockEntities.BLOCK_ENTITIES
                .getEntries()
                .forEach(
                        holder ->
                                holder.get()
                                        .getValidBlocks()
                                        .forEach(
                                                block ->
                                                        add(
                                                                block,
                                                                createNameableBlockEntityTable(
                                                                        block))));

        ModBlocks.BLOCKS
                .getEntries()
                .forEach(holder -> add(holder.get(), createSingleItemTable((ItemLike) holder)));
    }

    protected void skip() {}

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        return skipBlocks;
    }
}
