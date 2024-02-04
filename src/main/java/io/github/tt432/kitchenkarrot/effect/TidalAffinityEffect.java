package io.github.tt432.kitchenkarrot.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;

import java.util.Map;
import java.util.UUID;

public class TidalAffinityEffect extends MobEffect {

    public static final Attribute ATTRIBUTE = Attributes.ATTACK_SPEED;

    public TidalAffinityEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        AttributeInstance attributeinstance = pLivingEntity.getAttribute(ATTRIBUTE);
        AttributeModifier attributemodifier = new AttributeModifier("e3ef60e8-2fe5-4e92-9ed7-5cbf9ce65488",
                2D, AttributeModifier.Operation.MULTIPLY_TOTAL);
        if (pLivingEntity.isInWaterOrRain()) {
            attributeinstance.removeModifier(attributemodifier);
            attributeinstance.addTransientModifier(attributemodifier);
//            attributeinstance.addPermanentModifier(new AttributeModifier(attributemodifier.getId(),
//                    this.getDescriptionId() + " " + pAmplifier, this.getAttributeModifierValue(pAmplifier, attributemodifier), attributemodifier.getOperation()));
        } else {
            attributeinstance.removeModifier(attributemodifier);
        };
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
