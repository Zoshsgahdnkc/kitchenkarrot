package io.github.tt432.kitchenkarrot.effect;

import io.github.tt432.kitchenkarrot.registries.ModEffects;
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
        List<MobEffect> effects = entity.getActiveEffectsMap().keySet().stream().filter(e -> e != ModEffects.DISPERSE.get()).toList();
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
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (disperse(pLivingEntity)) {
            MobEffect disperse = ModEffects.DISPERSE.get();
            MobEffectInstance mobEffectInstance = pLivingEntity.getEffect(disperse);
            if (pAmplifier > 0 && mobEffectInstance.getDuration() > 5) {
                pLivingEntity.forceAddEffect(new MobEffectInstance(disperse,
                        mobEffectInstance.getDuration(), pAmplifier - 1), pLivingEntity);
            } else {
                pLivingEntity.removeEffect(disperse);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
