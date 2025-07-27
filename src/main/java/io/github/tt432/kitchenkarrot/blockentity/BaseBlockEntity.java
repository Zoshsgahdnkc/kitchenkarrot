package io.github.tt432.kitchenkarrot.blockentity;

import io.github.tt432.kitchenkarrot.blockentity.sync.SyncDataManager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

/**
 * @author DustW
 **/
public abstract class BaseBlockEntity extends BlockEntity {

    SyncDataManager syncDataManager = new SyncDataManager();

    public BaseBlockEntity(
            BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
        syncDataInit(syncDataManager);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        sync(level);
    }

    /**
     * place sync data here
     */
    protected void syncDataInit(SyncDataManager manager) {}

    public void tick() {}

    private static final String SYNC_KEY = "sync";

    protected boolean isSyncTag(CompoundTag tag) {
        return tag.contains(SYNC_KEY);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        syncDataManager.load(registries, tag, isSyncTag(tag));

        if (!isSyncTag(tag)) {
            forceOnce();
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (getLevel() != null) {
            sync(getLevel());
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        syncDataManager.save(registries, tag, false, true);
    }

    boolean forceOnce;

    public void forceOnce() {
        forceOnce = true;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag result = new CompoundTag();
        result.putBoolean(SYNC_KEY, true);
        // sync every tick by:skyinr
        syncDataManager.save(registries, result, true, true);

        return result;
    }

    public abstract List<ItemStack> drops();

    public void sync(Level level) {
        if (!level.isClientSide) {
            ClientboundBlockEntityDataPacket p = ClientboundBlockEntityDataPacket.create(this);
            ((ServerLevel) this.level)
                    .getChunkSource()
                    .chunkMap
                    .getPlayers(new ChunkPos(getBlockPos()), false)
                    .forEach(k -> k.connection.send(p));
        }
    }

    public static <T extends BaseBlockEntity> void tick(
            Level level, BlockPos pos, BlockState state, T t) {
        t.tick();
    }
}
