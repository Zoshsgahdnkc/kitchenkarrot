package io.github.tt432.kitchenkarrot.block;

import com.mojang.serialization.MapCodec;

import io.github.tt432.kitchenkarrot.blockentity.AirCompressorBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.MenuBlockEntity;
import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jetbrains.annotations.NotNull;

public class AirCompressorBlock extends FacingGuiEntityBlock<AirCompressorBlockEntity> {
    public static final MapCodec<AirCompressorBlock> CODEC =
            simpleCodec((p) -> new AirCompressorBlock());
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 16 - 2, 15, 16 - 2);

    public AirCompressorBlock() {
        super(BlockBehaviour.Properties.of().strength(2.0f, 2.0f).noOcclusion());
    }

    @Override
    @NotNull
    public VoxelShape getShape(
            @NotNull BlockState pState,
            @NotNull BlockGetter level,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockEntityType<AirCompressorBlockEntity> getBlockEntity() {
        return ModBlockEntities.AIR_COMPRESSOR.get();
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
        if (level.isClientSide) {
            if (player.getItemInHand(hand).is(Items.WATER_BUCKET)) {
                level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1, 1);
                level.playSound(
                        player, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1, 1);
            }
            return ItemInteractionResult.SUCCESS;
        } else {
            // If player has a water bucket in hand, pop 8 water item
            if (player.getItemInHand(hand).is(Items.WATER_BUCKET)) {
                RandomSource random = level.random;
                if (!player.getAbilities().instabuild) {
                    if (player.getItemInHand(hand).getCount() == 1) {
                        player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                    } else {
                        // Eject the bucket if it stacks more than 1
                        player.getItemInHand(hand).shrink(1);
                        level.addFreshEntity(
                                new ItemEntity(
                                        level,
                                        pos.getX() + 0.5,
                                        pos.getY() + 1,
                                        pos.getZ() + 0.5,
                                        new ItemStack(Items.BUCKET),
                                        random.nextFloat() * 2 - 1,
                                        random.nextFloat(),
                                        random.nextFloat() * 2 - 1));
                    }
                }
                level.addFreshEntity(
                        new ItemEntity(
                                level,
                                pos.getX() + 0.5,
                                pos.getY() + 1,
                                pos.getZ() + 0.5,
                                new ItemStack(ModItems.WATER.get(), 8),
                                random.nextFloat() * 0.4 - 0.2,
                                random.nextFloat() * 0.5,
                                random.nextFloat() * 0.4 - 0.2));
                return ItemInteractionResult.SUCCESS;
            }
            var be = level.getBlockEntity(pos);

            if (be instanceof MenuBlockEntity kk) {
                player.openMenu(kk, pos);
                kk.forceOnce();
            }
            return ItemInteractionResult.CONSUME;
        }
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
