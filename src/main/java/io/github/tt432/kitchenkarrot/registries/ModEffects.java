package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Kitchenkarrot.MOD_ID);

    public static final RegistryObject<MobEffect> DISPERSE = EFFECTS.register("disperse",
            () -> new DisperseEffect(MobEffectCategory.NEUTRAL, 12777212));

    public static final RegistryObject<MobEffect> TOXIC_RESISTANCE = EFFECTS.register("toxic_resistance",
            () -> new ToxicResistanceEffect(MobEffectCategory.BENEFICIAL, 9557596));

    public static final RegistryObject<MobEffect> WITHER_RESISTANCE = EFFECTS.register("wither_resistance",
            () -> new WitherResistanceEffect(MobEffectCategory.BENEFICIAL, 4542325));

    public static final RegistryObject<MobEffect> TIDAL_AFFINITY = EFFECTS.register("tidal_affinity",
            () -> new TidalAffinityEffect(MobEffectCategory.BENEFICIAL, 6929663));

    public static final RegistryObject<MobEffect> TIPSY = EFFECTS.register("tipsy",
            () -> new TipsyEffect(MobEffectCategory.HARMFUL, 12750161)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, "86e3c73f-3878-4e5c-8b90-ce3d723868f0",
                            -0.05D, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, "4c36c2aa-8559-4332-a0fa-57aaafa9a97c",
                            -0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, "7e812602-d614-46e2-b757-3b160621e1d1",
                            0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL));

}
