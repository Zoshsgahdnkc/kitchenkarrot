package io.github.tt432.kitchenkarrot.util;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class ItemHandlerUtils {
    public static List<ItemStack> toList(IItemHandler... itemHandlers) {
        List<ItemStack> result = new ArrayList<>();

        for (IItemHandler itemHandler : itemHandlers) {
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                result.add(itemHandler.getStackInSlot(i));
            }
        }

        return result;
    }

    public static void insertSingle(
            IItemHandler handler, int slot, Player player, InteractionHand hand) {
        insertSingle(handler, slot, player, player.getItemInHand(hand));
    }

    public static void insertSingle(
            IItemHandler handler, int slot, Player player, ItemStack itemStack) {
        ItemStack stack = player.getAbilities().instabuild ? itemStack.copy() : itemStack.split(1);
        handler.insertItem(slot, stack, false);
    }

    public static void extractSingle(IItemHandler handler, int slot, Player player) {
        ItemStack stack = handler.extractItem(slot, 1, false);
        if (!player.getAbilities().instabuild && !player.addItem(stack)) {
            player.drop(stack, true);
        }
    }
}
