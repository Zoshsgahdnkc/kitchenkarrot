package io.github.tt432.kitchenkarrot.effect;

import io.github.tt432.kitchenkarrot.config.ModCommonConfigs;
import io.github.tt432.kitchenkarrot.registries.ModEffects;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.jarjar.nio.util.Lazy;

import java.util.ArrayList;
import java.util.List;

public class TipsyEffect extends MobEffect {
    public TipsyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    // Is lazy optional necessary here?
    private static final Lazy<Integer> MAXIMUM_LEVEL =
            Lazy.of(() -> ModCommonConfigs.MAXIMUM_TIPSY_LEVEL.get());
    private static final Lazy<Integer> WASTED_DURATION =
            Lazy.of(() -> ModCommonConfigs.WASTED_DURATION.get());
    private static final Lazy<List<String>> EFFECTS =
            Lazy.of(() -> ModCommonConfigs.WASTED_EFFECTS_LIST.get());
    private static final Lazy<List<String>> LEVELS =
            Lazy.of(() -> ModCommonConfigs.WASTED_EFFECTS_LEVEL.get());

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pAmplifier >= MAXIMUM_LEVEL.orElse(3)) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 120, 0));
            //            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1200,
            // 2));
            //            pLivingEntity.addEffect(new
            // MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1200, 2));
            //            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 1200,
            // 2));
            getEffectInstanceFromConfig().forEach(pLivingEntity::addEffect);

            pLivingEntity.removeEffect(ModEffects.TIPSY);
            return false;
        }
        return true;
    }

    public List<MobEffectInstance> getEffectInstanceFromConfig() {
        List<MobEffect> effects = new ArrayList<>();
        for (String s : EFFECTS.orElse(ModCommonConfigs.WASTED_EFFECTS))
            try {
                effects.add(BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.parse(s)));
            } catch (Exception ignored) {
                effects.add(null);
            }

        List<Integer> levels = new ArrayList<>();
        for (String s : LEVELS.orElse(List.of("3", "3", "3")))
            try {
                levels.add(Integer.parseInt(s) - 1);
            } catch (Exception ignored) {
                levels.add(0);
            }

        List<MobEffectInstance> toReturn = new ArrayList<>();
        for (int index = 0; index < effects.size(); index++) {
            if (effects.get(index) == null) continue;
            int level = index < levels.size() ? levels.get(index) : 0;
            toReturn.add(
                    new MobEffectInstance(
                            Holder.direct(effects.get(index)),
                            WASTED_DURATION.orElse(1200),
                            level));
        }
        return toReturn;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
