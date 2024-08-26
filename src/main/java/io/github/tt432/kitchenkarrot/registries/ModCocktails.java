package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.client.cocktail.CocktailModelRegistry;
import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;
import io.github.tt432.kitchenkarrot.item.CocktailItem;
import io.github.tt432.kitchenkarrot.recipes.object.EffectStack;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.List;

// TODO:add Kubejs support
public class ModCocktails {
    public static final ResourceKey<Registry<CocktailProperty>> COCKTAIL_REGISTRY_KEY =
            ResourceKey.createRegistryKey(Kitchenkarrot.getModRL("cocktail"));
    public static final Registry<CocktailProperty> COCKTAILS_REGISTRY =
            new RegistryBuilder<>(COCKTAIL_REGISTRY_KEY).sync(true).create();

    public static final String DEFAULT_AUTHOR = "kitchenkarrot";

    public static final DeferredRegister<CocktailProperty> COCKTAIL_PROPERTIES =
            DeferredRegister.create(COCKTAILS_REGISTRY, Kitchenkarrot.MOD_ID);

    public static final DeferredHolder<CocktailProperty, CocktailProperty> UNKNOWN_COCKTAIL =
            COCKTAIL_PROPERTIES.register("unknown", () -> CocktailItem.UNKNOWN_COCKTAIL_PROPERTY);

    public static final DeferredHolder<CocktailProperty, CocktailProperty> BANE_OF_ARTHROPODS =
            register("bane_of_arthropods", List.of(new EffectStack("minecraft:nausea", 0, 600)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> BIRCH_SAP_VODKA =
            register(
                    "birch_sap_vodka", List.of(new EffectStack("minecraft:regeneration", 0, 3000)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> BLACK_PEARL =
            register("black_pearl", List.of(new EffectStack("minecraft:glowing", 10, 10000)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> JACKS_STORY =
            register("jacks_story", List.of(new EffectStack("minecraft:conduit_power", 0, 6000)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> JULY_21 =
            register("july_21", List.of(new EffectStack("minecraft:regeneration", 3, 150)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> LIGHT_YELLOW_FIREFLY =
            register(
                    "light_yellow_firefly",
                    List.of(
                            new EffectStack("minecraft:invisibility", 0, 2400),
                            new EffectStack("minecraft:glowing", 0, 2400)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> MILK_ACORN_WINE =
            register("milk_acorn_wine", List.of(new EffectStack("minecraft:luck", 0, 2400)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> MUSHY_SUNSET =
            register("mushy_sunset", List.of());
    public static final DeferredHolder<CocktailProperty, CocktailProperty> NEBULA_CHRONICLES =
            register(
                    "nebula_chronicles", List.of(new EffectStack("minecraft:resistance", 0, 4800)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> RED_LIZARD =
            register(
                    "red_lizard",
                    List.of(
                            new EffectStack("minecraft:strength", 0, 12000),
                            new EffectStack("minecraft:blindness", 0, 100)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> SCULKED_GARDEN =
            register("sculked_garden", List.of(new EffectStack("minecraft:absorption", 2, 12000)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> SECOND_GUESS =
            register("second_guess", List.of(new EffectStack("minecraft:slow_falling", 0, 3600)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> SHANGHAI_BEACH =
            register("shanghai_beach", List.of(new EffectStack("minecraft:strength", 2, 4800)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> SHOOTING_STAR =
            register("shooting_star", List.of(new EffectStack("minecraft:night_vision", 0, 6000)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> SLIMY_BALL =
            register("slimy_ball", List.of(new EffectStack("minecraft:jump_boost", 8, 4800)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> SWEET_BERRY_MARTINI =
            register("sweet_berry_martini", List.of());
    public static final DeferredHolder<CocktailProperty, CocktailProperty> TEARS_OF_STANLEY =
            register(
                    "tears_of_stanley",
                    List.of(
                            new EffectStack("minecraft:levitation", 1, 120),
                            new EffectStack("minecraft:haste", 2, 4800)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> TSUNDERE_HEROINE =
            register(
                    "tsundere_heroine", List.of(new EffectStack("minecraft:instant_health", 3, 1)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> TWILIGHT_FOREST =
            register(
                    "twilight_forest", List.of(new EffectStack("minecraft:invisibility", 0, 4800)));
    public static final DeferredHolder<CocktailProperty, CocktailProperty> YURA_PUNK =
            register(
                    "yura_punk",
                    List.of(
                            new EffectStack("minecraft:nausea", 1, 600),
                            new EffectStack("minecraft:speed", 2, 4800),
                            new EffectStack("minecraft:jump_boost", 2, 4800)));

    public static DeferredHolder<CocktailProperty, CocktailProperty> register(
            String name, List<EffectStack> effectStacks) {
        return register(name, DEFAULT_AUTHOR, effectStacks);
    }

    // spotless:off
    public static DeferredHolder<CocktailProperty, CocktailProperty> register(
            String name, String author, List<EffectStack> effectStacks) {
        return COCKTAIL_PROPERTIES.register(
                name,
                () -> new CocktailProperty(
                        CocktailModelRegistry.cocktailRL(name),
                        author,
                        effectStacks));
    }
    // spotless:on
}
