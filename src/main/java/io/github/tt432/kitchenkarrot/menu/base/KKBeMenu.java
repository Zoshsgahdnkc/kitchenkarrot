package io.github.tt432.kitchenkarrot.menu.base;

import io.github.tt432.kitchenkarrot.blockentity.BaseBlockEntity;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

/**
 * @author DustW
 **/
public class KKBeMenu<T extends BaseBlockEntity> extends KKMenu {
    public final T blockEntity;

    public KKBeMenu(MenuType<?> type, int windowId, Inventory inv, T blockEntity) {
        super(type, windowId, inv);
        this.blockEntity = blockEntity;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        blockEntity.sync(blockEntity.getLevel());
        return super.stillValid(pPlayer);
    }
}
