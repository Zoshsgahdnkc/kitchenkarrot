package io.github.tt432.kitchenkarrot.mixin;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraft.server.packs.resources.ReloadableResourceManager;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Shadow @Final private ReloadableResourceManager resourceManager;

    @Inject(
            method = "<init>",
            at =
                    @At(
                            value = "NEW",
                            target =
                                    "(Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/color/block/BlockColors;I)Lnet/minecraft/client/resources/model/ModelManager;",
                            shift = At.Shift.BEFORE))
    public void KK$init(GameConfig gameConfig, CallbackInfo ci) {
        resourceManager.registerReloadListener(Kitchenkarrot.getCocktailManager());
    }
}
