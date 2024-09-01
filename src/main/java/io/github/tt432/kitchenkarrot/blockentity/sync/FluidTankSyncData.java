package io.github.tt432.kitchenkarrot.blockentity.sync;

import io.github.tt432.kitchenkarrot.capability.KKFluidTank;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * @author DustW
 **/
public class FluidTankSyncData extends SyncData<FluidTankSyncData.SyncDataFluidTank> {
    public FluidTankSyncData(
            String name, int capacity, Predicate<FluidStack> validator, boolean needSave) {
        super(name, new SyncDataFluidTank(capacity, validator), needSave);
        get().setSyncData(this);
    }

    public FluidTankSyncData(String name, int capacity, boolean needSave) {
        this(name, capacity, (f) -> true, needSave);
    }

    @Override
    protected CompoundTag toTag(HolderLookup.Provider provider) {
        return get().writeToNBT(provider, new CompoundTag());
    }

    @Override
    protected SyncDataFluidTank fromTag(HolderLookup.Provider provider, CompoundTag tag) {
        return get().readFromNBT(provider, tag);
    }

    @Override
    public void set(SyncDataFluidTank value) {}

    public static class SyncDataFluidTank extends KKFluidTank {
        FluidTankSyncData syncData;

        public SyncDataFluidTank(int capacity, Predicate<FluidStack> validator) {
            super(capacity, validator);
        }

        protected void setSyncData(FluidTankSyncData syncData) {
            this.syncData = syncData;
        }

        @Override
        protected void onContentsChanged() {
            if (syncData != null) {
                syncData.onChanged();
            }
        }

        @Override
        @NotNull
        public SyncDataFluidTank readFromNBT(
                @NotNull HolderLookup.Provider lookupProvider, @NotNull CompoundTag nbt) {
            super.readFromNBT(lookupProvider, nbt);
            return this;
        }
    }
}
