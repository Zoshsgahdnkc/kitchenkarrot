package io.github.tt432.kitchenkarrot.effect;

import io.github.tt432.kitchenkarrot.config.ModCommonConfigs;
import io.github.tt432.kitchenkarrot.registries.ModEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class TipsyEffect extends MobEffect {
    public TipsyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    // Is lazy optional necessary here?
    private static final LazyOptional<Integer> MAXIMUM_LEVEL = LazyOptional.of(() ->ModCommonConfigs.MAXIMUM_TIPSY_LEVEL.get());
    private static final LazyOptional<Integer> WASTED_DURATION = LazyOptional.of(() -> ModCommonConfigs.WASTED_DURATION.get());
    private static final LazyOptional<List<String>> EFFECTS = LazyOptional.of(() -> ModCommonConfigs.WASTED_EFFECTS_LIST.get());
    private static final LazyOptional<List<String>> LEVELS = LazyOptional.of(() -> ModCommonConfigs.WASTED_EFFECTS_LEVEL.get());

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pAmplifier >= MAXIMUM_LEVEL.orElse(3)) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 120, 0));
//            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1200, 2));
//            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1200, 2));
//            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 1200, 2));
            getEffectInstanceFromConfig().forEach(pLivingEntity::addEffect);

            pLivingEntity.removeEffect(ModEffects.TIPSY.get());
        }
    }

    public List<MobEffectInstance> getEffectInstanceFromConfig() {
        List<MobEffect> effects = new ArrayList<>();
        for (String s : EFFECTS.orElse(ModCommonConfigs.WASTED_EFFECTS)) try {
            effects.add(ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(s)));
        } catch (Exception ignored) {
            effects.add(null);
        }

        List<Integer> levels = new ArrayList<>();
        for (String s : LEVELS.orElse(List.of("3", "3", "3"))) try {
            levels.add(Integer.parseInt(s) - 1);
        } catch (Exception ignored) {
            levels.add(0);
        }

        List<MobEffectInstance> toReturn = new ArrayList<>();
        for (int index = 0; index < effects.size(); index++) {
            if (effects.get(index) == null) continue;
            int level = index < levels.size() ? levels.get(index) : 0;
            toReturn.add(new MobEffectInstance(effects.get(index), WASTED_DURATION.orElse(1200), level));
        }
        return toReturn;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
