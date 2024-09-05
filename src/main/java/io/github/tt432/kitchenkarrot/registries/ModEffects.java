package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.effect.*;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Kitchenkarrot.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> DISPERSE =
            EFFECTS.register(
                    "disperse", () -> new DisperseEffect(MobEffectCategory.NEUTRAL, 0xC2F6FC));

    public static final DeferredHolder<MobEffect, MobEffect> TOXIC_RESISTANCE =
            EFFECTS.register(
                    "toxic_resistance",
                    () -> new ToxicResistanceEffect(MobEffectCategory.BENEFICIAL, 0x91D65C));

    public static final DeferredHolder<MobEffect, MobEffect> WITHER_RESISTANCE =
            EFFECTS.register(
                    "wither_resistance",
                    () -> new WitherResistanceEffect(MobEffectCategory.BENEFICIAL, 0x454F75));

    public static final DeferredHolder<MobEffect, MobEffect> TIDAL_AFFINITY =
            EFFECTS.register(
                    "tidal_affinity",
                    () -> new TidalAffinityEffect(MobEffectCategory.BENEFICIAL, 0x69BCFF));

    public static final DeferredHolder<MobEffect, MobEffect> TIPSY =
            EFFECTS.register(
                    "tipsy",
                    () ->
                            new TipsyEffect(MobEffectCategory.HARMFUL, 0xC28D51)
                                    .addAttributeModifier(
                                            Attributes.MOVEMENT_SPEED,
                                            ResourceLocation.withDefaultNamespace("effect.tipsy"),
                                            -0.05D,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                                    .addAttributeModifier(
                                            Attributes.ATTACK_SPEED,
                                            ResourceLocation.withDefaultNamespace("effect.tipsy"),
                                            -0.1D,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                                    .addAttributeModifier(
                                            Attributes.ATTACK_DAMAGE,
                                            ResourceLocation.withDefaultNamespace("effect.tipsy"),
                                            0.1D,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
}
