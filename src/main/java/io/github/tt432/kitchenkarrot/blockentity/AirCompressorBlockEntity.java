package io.github.tt432.kitchenkarrot.blockentity;

import io.github.tt432.kitchenkarrot.blockentity.sync.BoolSyncData;
import io.github.tt432.kitchenkarrot.blockentity.sync.IntSyncData;
import io.github.tt432.kitchenkarrot.blockentity.sync.StringSyncData;
import io.github.tt432.kitchenkarrot.blockentity.sync.SyncDataManager;
import io.github.tt432.kitchenkarrot.capability.KKItemStackHandler;
import io.github.tt432.kitchenkarrot.menu.AirCompressorMenu;
import io.github.tt432.kitchenkarrot.recipes.RecipeManager;
import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;
import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;
import io.github.tt432.kitchenkarrot.util.ItemHandlerUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * @author DustW
 **/
public class AirCompressorBlockEntity extends MenuBlockEntity {
    static final int MAX_ENERGY = 120;

    /**
     * 原料 / 容器输入
     */
    ItemStackHandler input1 =
            new KKItemStackHandler(this, 5) {
                @Override
                public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
                    if (!isItemValid(slot, stack)) {
                        return;
                    }
                    super.setStackInSlot(slot, stack);
                }

                @Override
                public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                    return slot != 4 || stack.is(ModItemTags.CONTAINER_ITEM);
                }
            };

    /**
     * 燃料输入
     */
    ItemStackHandler input2 =
            new KKItemStackHandler(this, 1) {
                @Override
                public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
                    if (!isItemValid(slot, stack)) {
                        return;
                    }
                    super.setStackInSlot(slot, stack);
                }

                @Override
                public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                    return stack.is(Items.REDSTONE);
                }
            };

    /**
     * 成品输出
     */
    ItemStackHandler output = new KKItemStackHandler(this, 1);

    public AirCompressorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.AIR_COMPRESSOR.get(), pWorldPosition, pBlockState);
    }

    private IntSyncData progress;
    private IntSyncData maxProgress;
    private IntSyncData energy;
    private StringSyncData recipeId;
    private RecipeHolder<AirCompressorRecipe> recipe;
    private BoolSyncData started;

    @Override
    protected void syncDataInit(SyncDataManager manager) {
        manager.add(recipeId = new StringSyncData("recipe", "", true));
        manager.add(progress = new IntSyncData("progress", 0, true));
        manager.add(maxProgress = new IntSyncData("max_progress", 1, true));
        manager.add(energy = new IntSyncData("energy", 0, true));
        manager.add(started = new BoolSyncData("started", false, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide) {
            // Part of adding energy
            if (canCharge()) {
                charge();
            }
            // Part of handling recipe
            if (isStarted()) {
                if (isRecipeSame()) {
                    if (progress.plus(1, getMaxProgress()) == getMaxProgress()) {
                        finish();
                    }
                } else {
                    stop();
                }
            } else {
                RecipeHolder<AirCompressorRecipe> recipePredicate = getRecipeFromItems();
                if (hasEnergy()
                        && recipePredicate != null
                        && isSlotAvailable(recipePredicate.value())) {
                    start(recipePredicate);
                }
            }
        }
    }

    public RecipeHolder<AirCompressorRecipe> getRecipe() {
        return recipe == null && !this.recipeId.isEmpty()
                ? (recipe =
                        (RecipeHolder<AirCompressorRecipe>)
                                level.getRecipeManager()
                                        .byKey(ResourceLocation.parse(recipeId.get()))
                                        .get())
                : recipe;
    }

    /* Predicate the recipe from given items before the recipe is set.*/
    private RecipeHolder<AirCompressorRecipe> getRecipeFromItems() {
        List<ItemStack> items = ItemHandlerUtils.toList(input1);
        ItemStack container = items.remove(4);
        return RecipeManager.getAirCompressorRecipe(level).stream()
                .filter(r -> r.value().matches(items) && r.value().testContainer(container))
                .findFirst()
                .orElse(null);
    }

    private boolean isSlotAvailable(AirCompressorRecipe recipe) {
        ItemStack resultStack = output.getStackInSlot(0);
        return resultStack.isEmpty()
                || (resultStack.is(recipe.getResultItem(RegistryAccess.EMPTY).getItem())
                        && resultStack.getCount() < resultStack.getMaxStackSize());
        //        return resultStack.isEmpty() || (resultStack.is(recipe.getResultItem().getItem())
        //                && resultStack.getCount() < resultStack.getMaxStackSize());
    }

    private void finish() {
        for (int i = 0; i < input1.getSlots(); i++) {
            if (input1.getStackInSlot(i).is(ModItems.EMPTY_CAN.get())) continue;
            input1.extractItem(i, 1, false);
        }
        energy.reduce(10, 0);
        output.insertItem(0, getRecipe().value().getResultItem(RegistryAccess.EMPTY), false);
        //        output.insertItem(0, getRecipe().getResultItem(), false);
        stop();
    }

    private boolean isRecipeSame() {
        var recipe = getRecipe();
        List<ItemStack> items = ItemHandlerUtils.toList(input1);
        ItemStack container = items.remove(4);
        return recipe != null
                && recipe.value().matches(items)
                && recipe.value().testContainer(container);
    }

    private boolean isStarted() {
        return started.get();
    }

    protected void start(RecipeHolder<AirCompressorRecipe> pRecipe) {
        setRecipe(pRecipe);
        maxProgress.set(recipe.value().getCraftingTime());
        started.set(true);
    }

    protected void setRecipe(RecipeHolder<AirCompressorRecipe> recipe) {
        this.recipe = recipe;
        if (recipe != null) {
            this.recipeId.set(recipe.value().getId());
        } else {
            this.recipeId.set("");
        }
    }

    protected void charge() {
        input2.extractItem(0, 1, false);
        energy.set(MAX_ENERGY);
        setChanged();
    }

    protected boolean hasEnergy() {
        return energy.get() >= 10;
    }

    protected boolean canCharge() {
        return input2.getStackInSlot(0).is(Items.REDSTONE) && getEnergy() < 10;
    }

    public int getEnergy() {
        return energy.get();
    }

    public int getAtomicEnergy() {
        return getEnergy() / 10;
    }

    protected void stop() {
        recipe = null;
        recipeId.set("");
        progress.set(0);
        maxProgress.set(1);
        started.set(false);
    }

    @Override
    public List<ItemStack> drops() {
        return ItemHandlerUtils.toList(input1, input2, output);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(
            int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AirCompressorMenu(pContainerId, pInventory, this);
    }

    public int getProgress() {
        return progress.get();
    }

    public int getMaxProgress() {
        return maxProgress.get();
    }

    public ItemStackHandler getInput1() {
        return input1;
    }

    public ItemStackHandler getInput2() {
        return input2;
    }

    public ItemStackHandler getOutput() {
        return output;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        var input1 = getInput1().serializeNBT(registries);
        var input2 = getInput2().serializeNBT(registries);
        var output = getOutput().serializeNBT(registries);

        tag.put("input1", input1);
        tag.put("input2", input2);
        tag.put("output", output);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (!isSyncTag(tag)) {
            input1.deserializeNBT(registries, tag.getCompound("input1"));
            input2.deserializeNBT(registries, tag.getCompound("input2"));
            output.deserializeNBT(registries, tag.getCompound("output"));
        }
    }
}
