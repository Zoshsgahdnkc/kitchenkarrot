package io.github.tt432.kitchenkarrot.datagen.provider.tags;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.registries.ModBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;

import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Kitchenkarrot.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.BREWING_BARREL.get(), ModBlocks.COASTER.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.PLATE.get(),
                        ModBlocks.FINE_SALT.get(),
                        ModBlocks.SEA_SALT.get(),
                        ModBlocks.ROCK_SALT.get(),
                        ModBlocks.CHORUS_OIL.get(),
                        ModBlocks.ACORN_OIL.get(),
                        ModBlocks.SUNFLOWER_OIL.get(),
                        ModBlocks.AIR_COMPRESSOR.get());
    }
}
