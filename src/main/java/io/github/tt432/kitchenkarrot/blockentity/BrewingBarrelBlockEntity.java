package io.github.tt432.kitchenkarrot.blockentity;

import io.github.tt432.kitchenkarrot.block.BrewingBarrelBlock;
import io.github.tt432.kitchenkarrot.blockentity.sync.*;
import io.github.tt432.kitchenkarrot.capability.KKFluidTank;
import io.github.tt432.kitchenkarrot.capability.KKItemStackHandler;
import io.github.tt432.kitchenkarrot.menu.BrewingBarrelMenu;
import io.github.tt432.kitchenkarrot.recipes.recipe.BrewingBarrelRecipe;
import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;
import io.github.tt432.kitchenkarrot.util.ItemHandlerUtils;

import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * @author DustW
 **/
public class BrewingBarrelBlockEntity extends MenuBlockEntity {
    public static final int FLUID_CONSUMPTION = 500;
    public static final int FLUID_CAPACITY = 4000;
    private FluidTankSyncData tank;
    private final KKItemStackHandler input = new KKItemStackHandler(this, 6);
    private final KKItemStackHandler result = new KKItemStackHandler(this, 1);
    private IntSyncData progress;
    private IntSyncData maxProgress;
    private StringSyncData recipe;
    private RecipeHolder<BrewingBarrelRecipe> currentRecipe;
    private BoolSyncData started;
    private final ContainerOpenersCounter openersCounter =
            new ContainerOpenersCounter() {
                @Override
                protected void onOpen(Level level, BlockPos pos, BlockState state) {
                    BrewingBarrelBlockEntity.this.playSound(SoundEvents.BARREL_OPEN);
                    level.setBlockAndUpdate(pos, state.setValue(BrewingBarrelBlock.OPEN, true));
                }

                @Override
                protected void onClose(Level level, BlockPos pos, BlockState state) {
                    BrewingBarrelBlockEntity.this.playSound(SoundEvents.BARREL_CLOSE);
                    level.setBlockAndUpdate(pos, state.setValue(BrewingBarrelBlock.OPEN, false));
                }

                @Override
                protected void openerCountChanged(
                        Level level, BlockPos pos, BlockState state, int count, int openCount) {
                    level.blockEvent(pos, state.getBlock(), 1, openCount);
                }

                @Override
                protected boolean isOwnContainer(Player player) {
                    if (player.containerMenu instanceof BrewingBarrelMenu menu) {
                        return menu.blockEntity == BrewingBarrelBlockEntity.this;
                    } else {
                        return false;
                    }
                }
            };

    public BrewingBarrelBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.BREWING_BARREL.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void syncDataInit(SyncDataManager manager) {
        manager.add(
                tank =
                        new FluidTankSyncData(
                                "fluid", FLUID_CAPACITY, f -> f.getFluid() == Fluids.WATER, true));
        manager.add(progress = new IntSyncData("progress", 0, true));
        manager.add(maxProgress = new IntSyncData("maxProgress", 0, true));
        manager.add(recipe = new StringSyncData("recipe", "", true));
        manager.add(started = new BoolSyncData("started", false, true));
    }

    public RecipeHolder<BrewingBarrelRecipe> getRecipe() {
        return currentRecipe == null && !this.recipe.isEmpty()
                ? currentRecipe =
                        (RecipeHolder<BrewingBarrelRecipe>)
                                level.getRecipeManager()
                                        .byKey(ResourceLocation.parse(recipe.get()))
                                        .get()
                : currentRecipe;
    }

    public KKItemStackHandler getInput() {
        return input;
    }

    public KKItemStackHandler getResult() {
        return result;
    }

    public KKFluidTank getTank() {
        return tank.get();
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isClientSide) {
            Optional<RecipeHolder<BrewingBarrelRecipe>> recipe = findRecipe();
            if (recipe.isPresent() && hasEnoughWater(recipe.get().value())) {
                if (isStarted()) {
                    if (isRecipeSame() && progress.plus(1, getMaxProgress()) == getMaxProgress()) {
                        finishBrewing();
                    }
                } else {
                    start();
                }
            } else if (isStarted()) {
                endProgress();
            }
        }
    }

    public Optional<RecipeHolder<BrewingBarrelRecipe>> findRecipe() {
        var inputList = ItemHandlerUtils.toList(input);
        return level.getRecipeManager().getAllRecipesFor(RecipeTypes.BREWING_BARREL.get()).stream()
                .filter(r -> r.value().matches(inputList))
                .findFirst();
    }

    private void finishBrewing() {
        ItemStack resultStack = result.getStackInSlot(0);
        if (resultStack.isEmpty()
                || (resultStack.is(
                                getRecipe().value().getResultItem(RegistryAccess.EMPTY).getItem())
                        && resultStack.getCount() < resultStack.getMaxStackSize())) {
            //                (resultStack.is(getRecipe().getResultItem().getItem()) &&
            // resultStack.getCount() < resultStack.getMaxStackSize())) {
            result.insertItem(0, getRecipe().value().getResultItem(RegistryAccess.EMPTY), false);
            //            result.insertItem(0, getRecipe().getResultItem(), false);
            for (int i = 0; i < input.getSlots(); i++) {
                Item item = input.getStackInSlot(i).getItem();
                if (item.hasCraftingRemainingItem()) {
                    Direction direction =
                            getBlockState().getValue(BrewingBarrelBlock.FACING).getOpposite();
                    level.addFreshEntity(
                            (new ItemEntity(
                                    level,
                                    getBlockPos().getX() + 0.5 + direction.getStepX() * 0.5,
                                    getBlockPos().getY() + 0.5,
                                    getBlockPos().getZ() + 0.5 + direction.getStepZ() * 0.5,
                                    new ItemStack(item.getCraftingRemainingItem()),
                                    direction.getStepX() * 0.2,
                                    0,
                                    direction.getStepZ() * 0.2)));
                }
                input.extractItem(i, 1, false);
            }
            tank.get()
                    .drain(
                            getRecipe().value().getWaterConsumption(),
                            IFluidHandler.FluidAction.EXECUTE);
            endProgress();
        }
    }

    public boolean resultEmpty() {
        return result.getStackInSlot(0).isEmpty();
    }

    public boolean hasEnoughWater(BrewingBarrelRecipe recipe) {
        return tank.get().getFluidAmount() >= recipe.getWaterConsumption();
    }

    public boolean isRecipeSame() {
        return this.getRecipe() != null
                && this.getRecipe().value().matches(ItemHandlerUtils.toList(input));
    }

    public boolean isStarted() {
        return started.get();
    }

    void endProgress() {
        started.set(false);
        setRecipe(null);
        resetProgress();
    }

    void setRecipe(RecipeHolder<BrewingBarrelRecipe> recipe) {
        this.currentRecipe = recipe;

        if (recipe != null) {
            this.recipe.set(recipe.id().toString());
        } else {
            this.recipe.set("");
        }
    }

    void resetProgress() {
        if (isStarted()) {
            this.progress.set(0);
            this.maxProgress.set(currentRecipe.value().getCraftingTime());
        } else {
            this.progress.set(0);
            this.maxProgress.set(0);
        }
    }

    public void start() {
        var inputList = ItemHandlerUtils.toList(input);
        level.getRecipeManager().getAllRecipesFor(RecipeTypes.BREWING_BARREL.get()).stream()
                .filter(r -> r.value().matches(inputList))
                .forEach(
                        r -> {
                            setRecipe(r);
                        });
        started.set(true);
        resetProgress();
    }

    @Override
    public List<ItemStack> drops() {
        return ItemHandlerUtils.toList(input, result);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(
            int pContainerId, Inventory pInventory, Player pPlayer) {
        return new BrewingBarrelMenu(pContainerId, pInventory, this);
    }

    public Integer getMaxProgress() {
        return maxProgress.get();
    }

    public Integer getProgress() {
        return progress.get();
    }

    public IItemHandler result() {
        return result;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("input", input.serializeNBT(registries));
        tag.put("result", result.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (!isSyncTag(tag)) {
            input.deserializeNBT(registries, tag.getCompound("input"));
            result.deserializeNBT(registries, tag.getCompound("result"));
        }
    }

    public void playSound(SoundEvent soundEvent) {
        Vec3i vec3i = this.getBlockState().getValue(BrewingBarrelBlock.FACING).getNormal();
        double d0 = (double) this.getBlockPos().getX() + 0.5D + (double) vec3i.getX() / 2.0D;
        double d1 = (double) this.getBlockPos().getY() + 0.5D + (double) vec3i.getY() / 2.0D;
        double d2 = (double) this.getBlockPos().getZ() + 0.5D + (double) vec3i.getZ() / 2.0D;
        level.playSound(
                (Player) null,
                d0,
                d1,
                d2,
                soundEvent,
                SoundSource.BLOCKS,
                0.5F,
                level.random.nextFloat() * 0.1F + 0.9F);
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(
                    player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(
                    player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(
                    this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }
}
