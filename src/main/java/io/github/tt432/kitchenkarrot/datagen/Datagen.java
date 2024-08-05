package io.github.tt432.kitchenkarrot.datagen;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.datagen.provider.ModGLMProvider;
import io.github.tt432.kitchenkarrot.datagen.provider.ModItemModelProvider;
import io.github.tt432.kitchenkarrot.datagen.provider.ModRecipeProvider;
import io.github.tt432.kitchenkarrot.datagen.provider.loot.ModLootTableProvider;
import io.github.tt432.kitchenkarrot.datagen.provider.tags.ModBlockTagsProvider;
import io.github.tt432.kitchenkarrot.datagen.provider.tags.ModItemTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Kitchenkarrot.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = e.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = e.getLookupProvider();

        ModBlockTagsProvider modBlockTagsProvider =
                generator.addProvider(
                        true, new ModBlockTagsProvider(output, lookupProvider, helper));
        generator.addProvider(
                true,
                new ModItemTagsProvider(
                        output, lookupProvider, modBlockTagsProvider.contentsGetter(), helper));

        generator.addProvider(true, new ModLootTableProvider(output));
        generator.addProvider(true, new ModGLMProvider(output));
        generator.addProvider(true, new ModRecipeProvider(output));
        generator.addProvider(true, new ModItemModelProvider(output, helper));
    }
}
