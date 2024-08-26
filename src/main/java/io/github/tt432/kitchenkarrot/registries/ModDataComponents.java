package io.github.tt432.kitchenkarrot.registries;

import com.mojang.serialization.Codec;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
            DeferredRegister.createDataComponents(Kitchenkarrot.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> FINISH =
            DATA_COMPONENTS.register(
                    "finish",
                    () ->
                            DataComponentType.<Boolean>builder()
                                    .persistent(Codec.BOOL)
                                    .networkSynchronized(ByteBufCodecs.BOOL)
                                    .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>>
            RECIPE_TIME =
                    DATA_COMPONENTS.register(
                            "recipe_time",
                            () ->
                                    DataComponentType.<Integer>builder()
                                            .persistent(Codec.INT)
                                            .networkSynchronized(ByteBufCodecs.INT)
                                            .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> PLATE_TYPE =
            DATA_COMPONENTS.register(
                    "plate_type",
                    () ->
                            DataComponentType.<String>builder()
                                    .persistent(Codec.STRING)
                                    .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                                    .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>>
            PLATE_AMOUNT =
                    DATA_COMPONENTS.register(
                            "plate_amount",
                            () ->
                                    DataComponentType.<Integer>builder()
                                            .persistent(Codec.INT)
                                            .networkSynchronized(ByteBufCodecs.INT)
                                            .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CocktailProperty>>
            COCKTAIL =
                    DATA_COMPONENTS.register(
                            "cocktail",
                            () ->
                                    DataComponentType.<CocktailProperty>builder()
                                            .persistent(CocktailProperty.CODEC)
                                            .networkSynchronized(
                                                    ByteBufCodecs.fromCodec(CocktailProperty.CODEC))
                                            .build());
}
