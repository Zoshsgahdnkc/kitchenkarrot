package io.github.tt432.kitchenkarrot.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/*
   Currently used ONLY as a makeshift of item tooltip mixin before really fixing the render&interact problem.
*/
public class ModItem extends Item {
    public ModItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        // use Mixin version
        //        if (PlateHolderMap.canPutOnPlate(stack.getItem())) {
        //
        // tooltipComponents.add(Component.translatable("info.kitchenkarrot.can_be_dished"));
        //        }
    }
}
