package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.registries.ModBlocks;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class EmptyPlateItem extends ModFood {

    public EmptyPlateItem(Properties properties) {
        super(properties);
    }

    @NotNull
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult interactionResult = this.place(new BlockPlaceContext(context));
        if (!interactionResult.consumesAction()
                && this.isComplex()
                && context.getPlayer() != null) {
            InteractionResult interactionResult1 =
                    this.use(context.getLevel(), context.getPlayer(), context.getHand())
                            .getResult();
            return interactionResult1 == InteractionResult.CONSUME
                    ? InteractionResult.CONSUME_PARTIAL
                    : interactionResult1;
        } else {
            return interactionResult;
        }
    }

    public InteractionResult place(BlockPlaceContext context) {
        if (!context.canPlace()) {
            return InteractionResult.FAIL;
        } else {
            BlockPlaceContext blockPlaceContext = this.updatePlacementContext(context);
            if (blockPlaceContext == null) {
                return InteractionResult.FAIL;
            } else {
                BlockState blockState = this.getPlacementState(blockPlaceContext);
                if (blockState == null) {
                    return InteractionResult.FAIL;
                } else if (!this.placeBlock(blockPlaceContext, blockState)) {
                    return InteractionResult.FAIL;
                } else {
                    BlockPos blockPos = blockPlaceContext.getClickedPos();
                    Level level = blockPlaceContext.getLevel();
                    Player player = blockPlaceContext.getPlayer();
                    ItemStack itemStack = blockPlaceContext.getItemInHand();
                    BlockState blockState1 = level.getBlockState(blockPos);
                    if (blockState1.is(blockState.getBlock())) {
                        blockState1 =
                                this.updateBlockStateFromTag(
                                        blockPos, level, itemStack, blockState1);
                        this.updateCustomBlockEntityTag(blockPos, level, player, itemStack);
                        blockState1
                                .getBlock()
                                .setPlacedBy(level, blockPos, blockState1, player, itemStack);
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger(
                                    (ServerPlayer) player, blockPos, itemStack);
                        }
                    }

                    level.gameEvent(player, GameEvent.BLOCK_PLACE, blockPos);
                    SoundType soundType =
                            blockState1.getSoundType(level, blockPos, context.getPlayer());
                    float volume = (soundType.getVolume() + 1.0F) / 2.0F;
                    float pitch = soundType.getPitch() * 0.8F;
                    level.playSound(
                            player,
                            blockPos,
                            SoundEvents.STONE_PLACE,
                            SoundSource.BLOCKS,
                            volume,
                            pitch);
                    if (player == null || !player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }

                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }
    }

    @Nullable
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
        return context;
    }

    protected void updateCustomBlockEntityTag(
            BlockPos pos, Level level, @Nullable Player player, ItemStack stack) {
        updateCustomBlockEntityTag(level, player, pos, stack);
    }

    @Nullable
    protected BlockState getPlacementState(BlockPlaceContext context) {
        BlockState blockstate = this.getBlock().getStateForPlacement(context);
        return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
    }

    private BlockState updateBlockStateFromTag(
            BlockPos pos, Level level, ItemStack stack, BlockState state) {
        BlockState blockState = state;

        if (stack.has(DataComponents.BLOCK_STATE)) {
            Map<String, String> properties =
                    Objects.requireNonNull(stack.getComponents().get(DataComponents.BLOCK_STATE))
                            .properties();
            StateDefinition<Block, BlockState> stateDefinition =
                    state.getBlock().getStateDefinition();
            for (String s : properties.keySet()) {
                Property<?> property = stateDefinition.getProperty(s);
                if (property != null) {
                    String s1 = Objects.requireNonNull(properties.get(s));
                    blockState = updateState(blockState, property, s1);
                }
            }
        }
        if (blockState != state) {
            level.setBlock(pos, blockState, 2);
        }

        return blockState;
    }

    private static <T extends Comparable<T>> BlockState updateState(
            BlockState state, Property<T> property, String valueIdentifier) {
        return property.getValue(valueIdentifier)
                .map((t) -> state.setValue(property, t))
                .orElse(state);
    }

    protected boolean canPlace(BlockPlaceContext context, BlockState state) {
        Player player = context.getPlayer();
        CollisionContext collisioncontext =
                player == null ? CollisionContext.empty() : CollisionContext.of(player);
        return state.canSurvive(context.getLevel(), context.getClickedPos())
                && context.getLevel()
                        .isUnobstructed(state, context.getClickedPos(), collisioncontext);
    }

    protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
        return context.getLevel().setBlock(context.getClickedPos(), state, 11);
    }

    public static void updateCustomBlockEntityTag(
            Level level, @Nullable Player player, BlockPos pos, ItemStack stack) {
        MinecraftServer server = level.getServer();
        if (server != null && stack.has(DataComponents.BLOCK_ENTITY_DATA)) {
            CompoundTag compoundTag = Objects.requireNonNull(getBlockEntityData(stack)).copyTag();
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity != null) {
                if (!level.isClientSide
                        && blockentity.onlyOpCanSetNbt()
                        && (player == null || !player.canUseGameMasterBlocks())) {
                    return;
                }

                CompoundTag compoundTag1 = blockentity.saveWithoutMetadata(level.registryAccess());
                CompoundTag compoundTag2 = compoundTag1.copy();
                compoundTag1.merge(compoundTag);
                if (!compoundTag1.equals(compoundTag2)) {
                    blockentity.loadWithComponents(compoundTag1, level.registryAccess());
                    blockentity.setChanged();
                }
            }
        }
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("info.kitchenkarrot.text1"));
        tooltipComponents.add(Component.translatable("info.kitchenkarrot.text2"));
        tooltipComponents.add(Component.translatable("info.kitchenkarrot.text3"));
    }

    public Block getBlock() {
        return ModBlocks.PLATE.get();
    }

    @Nullable
    public static CustomData getBlockEntityData(ItemStack itemStack) {
        return itemStack.get(DataComponents.BLOCK_ENTITY_DATA);
    }
}
