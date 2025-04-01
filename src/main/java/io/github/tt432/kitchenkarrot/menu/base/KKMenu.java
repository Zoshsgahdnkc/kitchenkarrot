package io.github.tt432.kitchenkarrot.menu.base;

import io.github.tt432.kitchenkarrot.menu.slot.KKResultSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author DustW
 **/
public abstract class KKMenu extends AbstractContainerMenu {
    protected Inventory inventory;
    protected IItemHandler invHandler;

    public KKMenu(MenuType<?> type, int pContainerId, Inventory inventory) {
        super(type, pContainerId);
        this.inventory = inventory;
        invHandler = new InvWrapper(inventory);

        layoutPlayerInventorySlots(7 + 1, 83 + 1);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return inventory.stillValid(pPlayer);
    }

    protected void addDataSlot(Supplier<Supplier<Integer>> getter, Supplier<Consumer<Integer>> setter) {
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getter.get().get() & 0xffff;
            }
            @Override
            public void set(int value) {
                int energyStored = getter.get().get() & 0xffff0000;
                setter.get().accept(energyStored + (value & 0xffff));
            }
        });

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getter.get().get() >> 16) & 0xffff;
            }
            @Override
            public void set(int value) {
                int energyStored = getter.get().get() & 0x0000ffff;
                setter.get().accept(energyStored | (value << 16));
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var slot = slots.get(index);
        var slotItem = slot.getItem();
        var playerSlotSize = 36;

        if (index < playerSlotSize) {
            for (int i = playerSlotSize; i < slots.size(); i++) {
                var temp = slots.get(i);

                if (temp.safeInsert(slotItem).isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }

        }
        else {
            for (int i = 0; i < playerSlotSize; i++) {
                var temp = slots.get(i);

                if (temp.safeInsert(slotItem).isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }

        }

        return ItemStack.EMPTY;
    }

    protected Slot addSlot(IItemHandler handler, int index, int x, int y) {
        return addSlot(new SlotItemHandler(handler, index, x, y));
    }

    protected Slot addResultSlot(IItemHandler handler, int index, int x, int y) {
        return addSlot(new KKResultSlot(handler, index, x, y));
    }

    protected int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    protected int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    protected void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(invHandler, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(invHandler, 0, leftCol, topRow, 9, 18);
    }
}
