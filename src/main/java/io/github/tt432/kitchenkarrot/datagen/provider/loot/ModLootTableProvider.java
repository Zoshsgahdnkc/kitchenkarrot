package io.github.tt432.kitchenkarrot.datagen.provider.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends LootTableProvider {
    private static final List<LootTableProvider.SubProviderEntry> LootTableProviders =
            List.of(
                    new LootTableProvider.SubProviderEntry(
                            ModBlockLoot::new, LootContextParamSets.BLOCK));

    public ModLootTableProvider(
            PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), LootTableProviders, registries);
    }

    @Override
    protected void validate(
            WritableRegistry<LootTable> writableregistry,
            ValidationContext validationcontext,
            ProblemReporter.Collector problemreporter$collector) {}
}
