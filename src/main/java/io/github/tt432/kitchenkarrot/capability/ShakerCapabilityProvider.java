package io.github.tt432.kitchenkarrot.capability;

import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.item.ShakerItem;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 **/
public class ShakerCapabilityProvider extends CapabilityProvider<ShakerCapabilityProvider> implements INBTSerializable<CompoundTag> {
    private final LazyOptional<ItemStackHandler> handler = LazyOptional.of(() -> new ItemStackHandler(12) {
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (!stack.getItem().canFitInsideContainerItems()) return false;
            return !(stack.getItem() instanceof ShakerItem) && (slot < 5 || slot > 10 || (slot < 9 ? stack.is(ModItemTags.BASE) :
                    slot == 9 ? stack.is(ModItems.ICE_CUBES.get()) : stack.is(ModItems.CARROT_SPICES.get())));
        }
    });

    public ShakerCapabilityProvider() {
        super(ShakerCapabilityProvider.class);
    }

    @Override
    public CompoundTag serializeNBT() {
        return handler.resolve().get().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        handler.ifPresent(h -> h.deserializeNBT(nbt));
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, handler.cast());
    }
}
