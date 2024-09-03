package io.github.tt432.kitchenkarrot.blockentity;

import io.github.tt432.kitchenkarrot.blockentity.sync.ItemStackHandlerSyncData;
import io.github.tt432.kitchenkarrot.blockentity.sync.SyncDataManager;
import io.github.tt432.kitchenkarrot.capability.KKItemStackHandler;
import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * @author DustW
 **/
public class CoasterBlockEntity extends BaseBlockEntity {
    private ItemStackHandlerSyncData item;

    public CoasterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.COASTER.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void syncDataInit(SyncDataManager manager) {
        item = manager.add(new ItemStackHandlerSyncData(this, "item", 1, true));
    }

    @Override
    public List<ItemStack> drops() {
        return Collections.singletonList(item.get().getStackInSlot(0));
    }

    @NotNull
    public KKItemStackHandler getItem() {
        return item.get();
    }
}
