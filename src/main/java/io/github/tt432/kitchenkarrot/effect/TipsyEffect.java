package io.github.tt432.kitchenkarrot.effect;

import io.github.tt432.kitchenkarrot.registries.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class TipsyEffect extends MobEffect {
    public TipsyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pAmplifier >= 3) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 1200, 1));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1200, 3));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1200, 3));
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 1200, 3));
            pLivingEntity.removeEffect(ModEffects.TIPSY.get());
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
