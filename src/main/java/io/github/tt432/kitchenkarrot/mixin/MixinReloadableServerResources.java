package io.github.tt432.kitchenkarrot.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;

import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.stream.Stream;

@Mixin(ReloadableServerResources.class)
public class MixinReloadableServerResources {
    @ModifyReturnValue(method = "listeners", at = @At("RETURN"))
    public List<PreparableReloadListener> KK$listeners(List<PreparableReloadListener> original) {
        return Stream.concat(Stream.of(Kitchenkarrot.getCocktailManager()), original.stream())
                .toList();
    }
}
