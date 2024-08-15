package io.github.tt432.kitchenkarrot;

import io.github.tt432.kitchenkarrot.cocktail.CocktailManager;
import io.github.tt432.kitchenkarrot.components.KKDataComponents;
import io.github.tt432.kitchenkarrot.config.ModCommonConfigs;
import io.github.tt432.kitchenkarrot.glm.ModGlobalLootModifiers;
import io.github.tt432.kitchenkarrot.item.ModBlockItems;
import io.github.tt432.kitchenkarrot.recipes.RecipeManager;
import io.github.tt432.kitchenkarrot.registries.*;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author DustW
 */
@Mod(Kitchenkarrot.MOD_ID)
public class Kitchenkarrot {
    // Log
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "kitchenkarrot";

    // Mod version here.
    public static final String VERSION = "1.21-0.5.0";

    private static Kitchenkarrot INSTANCE;
    private static final CocktailManager cocktailManager = new CocktailManager();

    //    private final ModNetworking networking;

    public Kitchenkarrot(IEventBus bus, Dist dist, ModContainer container) {
        INSTANCE = this;

        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        container.registerConfig(
                ModConfig.Type.COMMON, ModCommonConfigs.COMMON, "kitchenkarrot-common.toml");
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlockItems.BLOCK_ITEMS.register(bus);
        ModTabs.TABS.register(bus);
        ModMenuTypes.MENUS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModSoundEvents.SOUNDS.register(bus);
        ModEntities.ENTITYS.register(bus);
        ModGlobalLootModifiers.GLM.register(bus);
        ModEffects.EFFECTS.register(bus);
        KKDataComponents.DATA_COMPONENTS.register(bus);

        RecipeManager.register(bus);

        NeoForge.EVENT_BUS.addListener(this::reloadDataPack);
        //        networking = new ModNetworking();
    }

    public void reloadDataPack(AddReloadListenerEvent event) {
        event.addListener(cocktailManager);
    }

    public static Kitchenkarrot getInstance() {
        return INSTANCE;
    }

    public static ResourceLocation getModRL(String path) {
        return ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, path);
    }

    public static CocktailManager getCocktailManager() {
        return cocktailManager;
    }

    //    public ModNetworking getNetworking() {
    //        return networking;
    //    }
}
