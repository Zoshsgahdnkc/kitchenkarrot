package io.github.tt432.kitchenkarrot.effect;

import io.github.tt432.kitchenkarrot.registries.ModEffects;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class DisperseEffect extends MobEffect {
    public DisperseEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    private boolean disperse(LivingEntity entity) {
        List<Holder<MobEffect>> effects =
                entity.getActiveEffectsMap().keySet().stream()
                        .filter(e -> e != ModEffects.DISPERSE.get())
                        .toList();
        if (!effects.isEmpty()) {
            effects.forEach(entity::removeEffect);
            return true;
        }
        return false;
    }

    /*
    Every time the effect disperses other effects, reduce its amplifier by 1.
    if the level is 1 already or the duration is less than 5 ticks, remove the effect
     */
    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (disperse(pLivingEntity)) {
            Holder<MobEffect> disperse = ModEffects.DISPERSE;
            MobEffectInstance mobEffectInstance = pLivingEntity.getEffect(disperse);
            if (pAmplifier > 0 && mobEffectInstance != null && mobEffectInstance.getDuration() > 5) {
                pLivingEntity.forceAddEffect(
                        new MobEffectInstance(
                                disperse, mobEffectInstance.getDuration(), pAmplifier - 1),
                        pLivingEntity);
            } else {
                pLivingEntity.removeEffect(disperse);
            }
        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
