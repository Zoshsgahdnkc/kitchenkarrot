package io.github.tt432.kitchenkarrot.mixin;

import io.github.tt432.kitchenkarrot.registries.ModEffects;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

/**
 * @author DustW
 **/
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow protected abstract void onEffectUpdated(MobEffectInstance p_147192_, boolean p_147193_, @Nullable Entity p_147194_);

    @Shadow @Final private Map<MobEffect, MobEffectInstance> activeEffects;

    @Inject(method = "triggerItemUseEffects", at = @At("HEAD"), cancellable = true)
    private void kkTriggerItemUseEffects(ItemStack pStack, int pCount, CallbackInfo ci) {
        if (pStack.getItem() == ModItems.SHAKER.get()) {
            ci.cancel();
        }
    }
}
