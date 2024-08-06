package io.github.tt432.kitchenkarrot.datagen.provider.loot;

import io.github.tt432.kitchenkarrot.registries.ModBlocks;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ModBlockLoot extends BlockLootSubProvider {
    private final Set<Block> skipBlocks = new HashSet<>();

    protected ModBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
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
                                        ModItems.PLATE_PIECES.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1))
                                                .add(
                                                        LootItem.lootTableItem(
                                                                        ModItems.PLATE_PIECES.get())
                                                                .apply(
                                                                        SetItemCountFunction
                                                                                .setCount(
                                                                                        BinomialDistributionGenerator
                                                                                                .binomial(
                                                                                                        3,
                                                                                                        0.5F),
                                                                                        true))))));

        ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(block -> block instanceof BaseEntityBlock)
                .forEach(block -> add(block,createNameableBlockEntityTable(block)));

        ModBlocks.BLOCKS
                .getEntries()
                .forEach(holder -> add(holder.get(), createSingleItemTable(holder.get())));
    }

    protected void skip() {}

    @Override
    @NotNull
    protected Iterable<Block> getKnownBlocks() {
        return skipBlocks;
    }
}
