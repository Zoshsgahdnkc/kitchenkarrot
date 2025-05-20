package io.github.tt432.kitchenkarrot.item;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class EffectEntry {
    public Supplier<MobEffectInstance> effect;
    public float probability;

    private EffectEntry(Supplier<MobEffectInstance> effect, float probability) {
        this.effect = effect;
        this.probability = probability;
    }

    public static EffectEntry of(Holder<MobEffect> effect, float time, float probability) {
        return new EffectEntry(() -> new MobEffectInstance(effect, (int) (time * 20)), probability);
    }

    public static EffectEntry of(Holder<MobEffect> effect, int time, int level, float probability) {
        return new EffectEntry(
                () -> new MobEffectInstance(effect, time * 20, level - 1), probability);
    }
}
