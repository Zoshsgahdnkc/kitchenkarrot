package io.github.tt432.kitchenkarrot.effect;

import io.github.tt432.kitchenkarrot.registries.ModEffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class TipsyEffect extends MobEffect {
    public TipsyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pAmplifier >= 3) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 1200, 1));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1200, 3));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1200, 3));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 1200, 3));
            pLivingEntity.removeEffect(ModEffects.TIPSY);
        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
