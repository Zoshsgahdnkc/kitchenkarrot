package io.github.tt432.kitchenkarrot.registries;

import static io.github.tt432.kitchenkarrot.Kitchenkarrot.MOD_ID;

import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;
import io.github.tt432.kitchenkarrot.item.CocktailItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
            TABS.register(
                    "main",
                    () ->
                            CreativeModeTab.builder()
                                    .icon(() -> new ItemStack(ModItems.CARROT_SPICES.get()))
                                    .title(Component.translatable("itemGroup.kitchenkarrot.main"))
                                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                                    .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COCKTAIL_TAB =
            TABS.register(
                    "cocktail",
                    () ->
                            CreativeModeTab.builder()
                                    .icon(() -> new ItemStack(ModItems.SHAKER.get()))
                                    .title(
                                            Component.translatable(
                                                    "itemGroup.kitchenkarrot.cocktail"))
                                    .withTabsBefore(MAIN_TAB.getId())
                                    .build());

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ModTabs.COCKTAIL_TAB.get()) {
            event.accept(ModItems.SHAKER);
            event.accept(ModItems.ACORN_WINE_BASE);
            event.accept(ModItems.MEAD_BASE);
            event.accept(ModItems.RUM_BASE);
            event.accept(ModItems.VODKA_BASE);

            ModCocktails.COCKTAIL_PROPERTIES
                    .getEntries()
                    .forEach(
                            holder -> {
                                CocktailProperty cocktailProperty = holder.get();
                                ItemStack itemStack = new ItemStack(ModItems.COCKTAIL.get());
                                CocktailItem.setCocktail(itemStack, cocktailProperty);
                                event.accept(itemStack);
                            });
        }
        if (event.getTab() == ModTabs.MAIN_TAB.get()) {
            event.accept(ModItems.EMPTY_PLATE);
            event.accept(ModItems.KNIFE);
            event.accept(ModBlocks.AIR_COMPRESSOR);
            event.accept(ModBlocks.BREWING_BARREL);
            event.accept(ModBlocks.COASTER);
            event.accept(ModItems.GEM_CARROT);
            event.accept(ModItems.CARROT_SPICES);
            event.accept(ModItems.ICE_CUBES);
            event.accept(ModItems.ACORN);
            event.accept(ModItems.EMPTY_CAN);
            event.accept(ModBlocks.SEA_SALT);
            event.accept(ModBlocks.ROCK_SALT);
            event.accept(ModBlocks.FINE_SALT);
            event.accept(ModBlocks.ACORN_OIL);
            event.accept(ModBlocks.SUNFLOWER_OIL);
            event.accept(ModBlocks.CHORUS_OIL);
            event.accept(ModItems.WATER);
            event.accept(ModItems.MILK);

            event.accept(ModItems.BIRCH_SAP);
            event.accept(ModItems.KELP_WITH_SUNFLOWER_SEED);
            event.accept(ModItems.FRIED_PUMPKIN_CAKE);
            event.accept(ModItems.SEED_PIE);
            event.accept(ModItems.CHEESE_WHEEL);
            event.accept(ModItems.CHEESE_SLICE);
            event.accept(ModItems.ENCHANTED_CHEESE);
            event.accept(ModItems.RAW_BEEF_IN_DRIPLEAF);
            event.accept(ModItems.BEEF_IN_DRIPLEAF);
            event.accept(ModItems.SMALL_BEEF_IN_DRIPLEAF);
            event.accept(ModItems.BAMBOO_POTATO);
            event.accept(ModItems.PICKLED_SEA_PICKLES);
            event.accept(ModItems.BIRCH_SAP_CHOCOLATE_BAR);
            event.accept(ModItems.CHOCOLATE_CROISSANT);
            event.accept(ModItems.BEETROOT_CREPE);
            event.accept(ModItems.CHINESE_CREPE);
            event.accept(ModItems.CROQUE_MADAME);
            event.accept(ModItems.GRILLED_WHEATMEAL);
            event.accept(ModItems.GRILLED_FISH_AND_CACTUS);
            event.accept(ModItems.FLOWER_CAKE);
            event.accept(ModItems.PILLAGER_PIE);
            event.accept(ModItems.SWEET_ROLL);
            event.accept(ModItems.MONSTER_LASAGNA);
            event.accept(ModItems.SMALL_MONSTER_LASAGNA);
            event.accept(ModItems.SWEET_BERRY_MILK);
            event.accept(ModItems.BACON_WRAPPED_POTATO);
            event.accept(ModItems.CRISPY_BREAD_WITH_KELP);
            event.accept(ModItems.STONE_SHORE_QUICHE);
            event.accept(ModItems.WOODLAND_TATER_PUREE);
            event.accept(ModItems.FISHERMENS_DELIGHT);
            event.accept(ModItems.PHANTOM_STEW);

            event.accept(ModItems.LUSH_SALAD);
            event.accept(ModItems.FRESH_SALAD);
            event.accept(ModItems.TRAVELERS_SALAD);
            event.accept(ModItems.BEETROOT_SALAD);
            event.accept(ModItems.FRUIT_CEREAL_PORRIDGE);
            event.accept(ModItems.CREEPER_CEREAL_PORRIDGE);
            event.accept(ModItems.ULTRA_SUPER_DELICIOUS_CEREAL_PORRIDGE);

            event.accept(ModItems.CARROT_AND_CARROT);
            event.accept(ModItems.SOOTHING_TEA);
            event.accept(ModItems.CHORUS_MOUSSE);
            event.accept(ModItems.SMALL_CHORUS_MOUSSE);
            event.accept(ModItems.SLIME_MOUSSE);
            event.accept(ModItems.SMALL_SLIME_MOUSSE);
            event.accept(ModItems.FEAST_PIZZA);
            event.accept(ModItems.FEAST_PIZZA_SLICE);
            event.accept(ModItems.SHINY_PIZZA);
            event.accept(ModItems.SHINY_PIZZA_SLICE);
            event.accept(ModItems.DUNGEON_PIZZA);
            event.accept(ModItems.DUNGEON_PIZZA_SLICE);
            event.accept(ModItems.RAW_SWEET_LOAF);
            event.accept(ModItems.SWEET_LOAF);
            event.accept(ModItems.SWEET_LOAF_SLICE);
            event.accept(ModItems.SIRLOIN_STEAK);
            event.accept(ModItems.BEEF_GRAINS);
            event.accept(ModItems.SASHIMI);
            event.accept(ModItems.MOSS_FRIED_LAMB_CUTLETS);
            event.accept(ModItems.FRIES);
            event.accept(ModItems.DRUMSTICK);
            event.accept(ModItems.FRIED_CHICKEN_COMBO);
            event.accept(ModItems.POPACORN);
            event.accept(ModItems.CURRY_UDON);
            event.accept(ModItems.RICE_CAKE);
            event.accept(ModItems.VERDANT_NAMA_CHOCO);
            event.accept(ModItems.HONEY_BRULEE);
            event.accept(ModItems.LAVA_BRULEE);
            event.accept(ModItems.HI_NRG_BRULEE);
            event.accept(ModItems.EGG_TART);
            event.accept(ModItems.SWEET_BERRY_TART);
            event.accept(ModItems.CARROT_TART);
            event.accept(ModItems.CRIMSON_FUNGI_SPRING_ROLL);
            event.accept(ModItems.MIXED_NUTS_MOONCAKE);
            event.accept(ModItems.BIRCH_SAP_DONUT);
            event.accept(ModItems.BUCHE_DE_NOEL);
            event.accept(ModItems.BIKINI_BOTTOM_SUB);

            event.accept(ModItems.CANNED_MUTTON_PUMPKIN);
            event.accept(ModItems.CANNED_PORK_BEETROOT);
            event.accept(ModItems.CANNED_BEEF_POTATO);
            event.accept(ModItems.CANNED_CANDIED_APPLE);
            event.accept(ModItems.CANNED_SWEET_BERRY_MILK);
            event.accept(ModItems.CANNED_HOGLIN_CONFIT);

            event.accept(ModItems.ICED_MELON_LAGER);
            event.accept(ModItems.GLOW_BERRY_LAGER);

            event.accept(ModItems.ACORN_WINE);
            event.accept(ModItems.MEAD);
            event.accept(ModItems.RUM);
            event.accept(ModItems.VODKA);
            event.accept(ModItems.LIGHT_SODA);
            event.accept(ModItems.KELP_SODA);
            event.accept(ModItems.TWISTING_SODA);
            event.accept(ModItems.DANDELION_COKE);
            event.accept(ModItems.CORAL_COKE);
            event.accept(ModItems.DRAGON_BREATH_COKE);

            event.accept(ModItems.RAW_VEGAN_MUTTON);
            event.accept(ModItems.COOKED_VEGAN_MUTTON);
            event.accept(ModItems.RAW_VEGAN_PORK);
            event.accept(ModItems.COOKED_VEGAN_PORK);
            event.accept(ModItems.RAW_VEGAN_BEEF);
            event.accept(ModItems.COOKED_VEGAN_BEEF);

            event.accept(ModItems.PLATE_PIECES);
        }
    }
}
