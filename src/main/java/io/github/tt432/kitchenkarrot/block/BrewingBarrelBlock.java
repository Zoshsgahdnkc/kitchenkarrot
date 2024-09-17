package io.github.tt432.kitchenkarrot.block;

import com.mojang.serialization.MapCodec;

import io.github.tt432.kitchenkarrot.blockentity.BrewingBarrelBlockEntity;
import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class BrewingBarrelBlock extends FacingGuiEntityBlock<BrewingBarrelBlockEntity> {
    public static final MapCodec<BrewingBarrelBlock> CODEC = simpleCodec(BrewingBarrelBlock::new);
    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    public static final VoxelShape SHAPE_Z = Block.box(1, 1, 0, 15, 15, 16);
    public static final VoxelShape SHAPE_X = Block.box(0, 1, 1, 16, 15, 15);

    public BrewingBarrelBlock(Properties p_49224_) {
        super(p_49224_);
        this.registerDefaultState(defaultBlockState().setValue(OPEN, false));
    }

    @Override
    @NotNull
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return Objects.requireNonNull(super.getStateForPlacement(pContext)).setValue(OPEN, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(OPEN);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(
            @NotNull ItemStack stack,
            @NotNull BlockState state,
            Level level,
            @NotNull BlockPos pos,
            @NotNull Player player,
            @NotNull InteractionHand hand,
            @NotNull BlockHitResult hitResult) {
        AtomicBoolean changed = new AtomicBoolean(false);

        IFluidHandler tank =
                level.getCapability(Capabilities.FluidHandler.BLOCK, pos, hitResult.getDirection());
        if (tank != null) {
            ItemStack item = player.getItemInHand(hand);
            ItemStack remain = ItemStack.EMPTY;

            if (item.getItem() == Items.WATER_BUCKET) {
                FluidStack water = new FluidStack(Fluids.WATER, 1000);

                if (tank.fill(water, IFluidHandler.FluidAction.SIMULATE) == 1000) {
                    changed.set(true);
                    remain = new ItemStack(Items.BUCKET);

                    if (!level.isClientSide) {
                        tank.fill(water, IFluidHandler.FluidAction.EXECUTE);
                    }
                }
                player.playSound(
                        SoundEvents.BUCKET_EMPTY, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
            } else if (item.getItem() == Items.POTION
            //                    && PotionUtils.getAllEffects(item.getTag()).isEmpty()
            ) {
                FluidStack water = new FluidStack(Fluids.WATER, 250);

                if (tank.fill(water, IFluidHandler.FluidAction.SIMULATE) == 250) {
                    changed.set(true);
                    remain = new ItemStack(Items.GLASS_BOTTLE);

                    if (!level.isClientSide) {
                        tank.fill(water, IFluidHandler.FluidAction.EXECUTE);
                    }
                }
                player.playSound(
                        SoundEvents.BOTTLE_EMPTY, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
            } else if (item.getItem() == ModItems.WATER.get()) {
                FluidStack water = new FluidStack(Fluids.WATER, 125);

                if (tank.fill(water, IFluidHandler.FluidAction.SIMULATE) == 125) {
                    changed.set(true);

                    if (!level.isClientSide) {
                        tank.fill(water, IFluidHandler.FluidAction.EXECUTE);
                    }
                }
                player.playSound(
                        SoundEvents.BUCKET_EMPTY, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
            } else {
                if (!level.isClientSide()) {
                    // Fixme: qyl27: work not correctly when more than one player open it.
                    level.setBlock(pos, state.setValue(OPEN, true), Block.UPDATE_ALL);
                    this.getBlockEntity()
                            .getBlockEntity(level, pos)
                            .playSound(SoundEvents.BARREL_OPEN);
                }
            }

            if (changed.get()) {
                if (!player.getAbilities().instabuild) {
                    item.shrink(1);
                    player.getInventory().add(remain);
                }
            }
        }

        if (changed.get()) {
            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public BlockEntityType<BrewingBarrelBlockEntity> getBlockEntity() {
        return ModBlockEntities.BREWING_BARREL.get();
    }

    @Override
    @NotNull
    public VoxelShape getShape(
            BlockState pState,
            @NotNull BlockGetter level,
            @NotNull BlockPos pos,
            @NotNull CollisionContext pContext) {

        return switch (pState.getValue(FACING)) {
            case EAST, WEST -> SHAPE_X;
            default -> SHAPE_Z;
        };
    }
}
