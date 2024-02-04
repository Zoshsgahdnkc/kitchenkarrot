package io.github.tt432.kitchenkarrot.mixin;

import io.github.tt432.kitchenkarrot.registries.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author DustW
 **/
@Mixin(MobEffectInstance.class)
public class MixinMobEffectInstance {
    @Shadow @Final private MobEffect effect;

    @Shadow private int amplifier;

    @Shadow private int duration;

    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void mixinUpdate(MobEffectInstance pOther, CallbackInfoReturnable<Boolean> cir) {
        if (this.effect == ModEffects.TIPSY.get()) {
            amplifier += 1;
            duration = pOther.getDuration();
            cir.setReturnValue(true);
        }
    }
}
