package io.github.tt432.kitchenkarrot.datagen;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.datagen.provider.MODGLMProvider;
import io.github.tt432.kitchenkarrot.datagen.provider.ModItemModelProvider;
import io.github.tt432.kitchenkarrot.datagen.provider.ModRecipeProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Kitchenkarrot.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Datagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = e.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = e.getLookupProvider();

        generator.addProvider(true, new MODGLMProvider(output, lookupProvider));
        generator.addProvider(true, new ModRecipeProvider(output, lookupProvider));
        generator.addProvider(true, new ModItemModelProvider(output, helper));
    }
}
