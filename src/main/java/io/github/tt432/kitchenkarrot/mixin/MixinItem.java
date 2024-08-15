package io.github.tt432.kitchenkarrot.mixin;

import io.github.tt432.kitchenkarrot.block.PlateHolderMap;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * @author DustW
 **/
@Mixin(Item.class)
public class MixinItem {
    @SuppressWarnings("all")
    @Inject(method = "appendHoverText", at = @At("RETURN"), cancellable = true)
    public void KK$appendHoverText(
            ItemStack stack,
            Item.TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag,
            CallbackInfo ci) {
        if (PlateHolderMap.canPutOnPlate(stack.getItem())) {
            if (tooltipComponents.contains(
                    Component.translatable("info.kitchenkarrot.can_be_dished"))) return;
            tooltipComponents.add(Component.translatable("info.kitchenkarrot.can_be_dished"));
        }
    }
}
