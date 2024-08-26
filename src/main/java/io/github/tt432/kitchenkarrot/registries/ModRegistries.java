package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Kitchenkarrot.MOD_ID)
public class ModRegistries {
    @SubscribeEvent
    public static void registry(NewRegistryEvent event) {
        event.register(ModCocktails.COCKTAILS_REGISTRY);
    }
}
