package io.github.tt432.kitchenkarrot.effect;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

// TODO IMPLEMENT THIS
public class TidalAffinityEffect extends MobEffect {

    public static final Holder<Attribute> ATTRIBUTE = Attributes.ATTACK_SPEED;

    public TidalAffinityEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        AttributeInstance attributeinstance = pLivingEntity.getAttribute(ATTRIBUTE);
        AttributeModifier attributemodifier =
                new AttributeModifier(
                        ResourceLocation.withDefaultNamespace("effect.tidal"),
                        2D,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        if(attributeinstance != null) {
            attributeinstance.removeModifier(attributemodifier);
            if (pLivingEntity.isInWaterOrRain()) {
                attributeinstance.addTransientModifier(attributemodifier);
            }
        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
