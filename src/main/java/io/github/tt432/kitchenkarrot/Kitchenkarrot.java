package io.github.tt432.kitchenkarrot;

import io.github.tt432.kitchenkarrot.registries.*;
import io.github.tt432.kitchenkarrot.config.ModCommonConfigs;
import io.github.tt432.kitchenkarrot.glm.ModGlobalLootModifiers;
import io.github.tt432.kitchenkarrot.item.ModBlockItems;
import io.github.tt432.kitchenkarrot.networking.ModNetworking;
import io.github.tt432.kitchenkarrot.recipes.RecipeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author DustW
 */
@Mod(Kitchenkarrot.MOD_ID)
public class Kitchenkarrot {
    //Log
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "kitchenkarrot";

    // Mod version here.
    public static final String VERSION = "1.20.1-0.5.0";

    private static Kitchenkarrot INSTANCE;

    private final ModNetworking networking;

    public Kitchenkarrot() {
        INSTANCE = this;

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,ModCommonConfigs.COMMON,"kitchenkarrot-common.toml");
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

        RecipeManager.register(bus);

        networking = new ModNetworking();
    }

    public static Kitchenkarrot getInstance() {
        return INSTANCE;
    }

    public ModNetworking getNetworking() {
        return networking;
    }
}
