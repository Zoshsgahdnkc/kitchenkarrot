package io.github.tt432.kitchenkarrot.blockentity.sync;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.fluids.FluidStack;

/**
 * @author DustW
 **/
public class FluidSyncData extends SyncData<FluidStack> {
    public FluidSyncData(String name, FluidStack defaultValue, boolean needSave) {
        super(name, defaultValue, needSave);
    }

    @Override
    protected CompoundTag toTag(HolderLookup.Provider provider) {
        return (CompoundTag) get().save(provider);
    }

    @Override
    protected FluidStack fromTag(HolderLookup.Provider provider, CompoundTag tag) {
        return FluidStack.parseOptional(provider, tag);
    }
}
