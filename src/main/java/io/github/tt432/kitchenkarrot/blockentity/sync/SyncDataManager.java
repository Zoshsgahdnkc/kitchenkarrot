package io.github.tt432.kitchenkarrot.blockentity.sync;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
// TODO rewrite this
public class SyncDataManager {
    private final List<SyncData<?>> syncDataList = new ArrayList<>();

    public <T, S extends SyncData<T>> S add(S syncData) {
        syncDataList.add(syncData);
        return syncData;
    }

    public void remove(SyncData<?> syncData) {
        syncDataList.remove(syncData);
    }

    public void load(HolderLookup.Provider provider, CompoundTag pTag, boolean sync) {
        if (sync) {
            syncDataList.forEach(data -> data.load(provider, pTag));
        } else {
            syncDataList.forEach(
                    data -> {
                        if (data.needSave) {
                            data.load(provider, pTag);
                        }
                    });
        }
    }

    public void save(
            HolderLookup.Provider provider, CompoundTag pTag, boolean sync, boolean force) {
        if (sync) {
            syncDataList.forEach(data -> data.save(provider, pTag, force));
        } else {
            syncDataList.forEach(
                    data -> {
                        if (data.needSave) {
                            data.save(provider, pTag, force);
                        }
                    });
        }
    }
}
