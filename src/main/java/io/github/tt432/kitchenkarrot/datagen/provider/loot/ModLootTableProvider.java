package io.github.tt432.kitchenkarrot.datagen.provider.loot;

import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModLootTableProvider extends LootTableProvider {
    private static final List<LootTableProvider.SubProviderEntry> LootTableProviders =
            List.of(
                    new LootTableProvider.SubProviderEntry(
                            ModBlockLoot::new, LootContextParamSets.BLOCK));

    public ModLootTableProvider(
            PackOutput output) {
        super(output, Set.of(), LootTableProviders);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {
    }
}
