package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.capability.ShakerCapabilityProvider;
import io.github.tt432.kitchenkarrot.menu.ShakerMenu;
import io.github.tt432.kitchenkarrot.registries.ModSoundEvents;
import io.github.tt432.kitchenkarrot.util.SoundUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static io.github.tt432.kitchenkarrot.registries.ModItems.cocktailProperties;

/**
 * @author DustW
 **/
public class ShakerItem extends Item {
    public ShakerItem() {
        super(cocktailProperties().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("info.kitchenkarrot.shaker1"));
        tooltip.add(Component.translatable("info.kitchenkarrot.shaker2"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        var stack = pPlayer.getItemInHand(pUsedHand);

        if (pUsedHand == InteractionHand.MAIN_HAND) {
            if (pPlayer.isShiftKeyDown()) {
                if (!pLevel.isClientSide) {
                    NetworkHooks.openScreen((ServerPlayer) pPlayer, new SimpleMenuProvider(
                            (id, inv, player) -> new ShakerMenu(id, inv), stack.getDisplayName()));
                } else {
                    pPlayer.playSound(ModSoundEvents.SHAKER_OPEN.get(), 0.5F,
                            pLevel.random.nextFloat() * 0.1F + 0.9F);
                }

                return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide);
            } else if (!getFinish(stack)) {
                pPlayer.startUsingItem(pUsedHand);
                if (pLevel.isClientSide) {
                    SoundUtil.shakerSound(pPlayer, pLevel);
                }
                return InteractionResultHolder.success(stack);
            }
        }

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pLevel.isClientSide && pEntity instanceof Player player) {
            if (player.getUseItem() != pStack) {
                Minecraft.getInstance().getSoundManager().stop(ModSoundEvents.SHAKER.get().getLocation(),
                        player.getSoundSource());
            }
        }

        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player) {
            setFinish(pStack, true);

            if (pLevel.isClientSide) {
                Minecraft.getInstance().getSoundManager().stop(ModSoundEvents.SHAKER.get().getLocation(),
                        player.getSoundSource());
                pLivingEntity.playSound(ModSoundEvents.COCKTAIL_COMPLETE.get(), 0.5F,
                        pLevel.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        return pStack;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return getRecipeTime(pStack);
    }

    public static void setFinish(ItemStack stack, boolean finish) {
        stack.getOrCreateTag().putBoolean("finish", finish);
    }

    public static boolean getFinish(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("finish");
    }

    public static void setRecipeTime(ItemStack stack, int time) {
        stack.getOrCreateTag().putInt("time", time);
    }

    public static int getRecipeTime(ItemStack stack) {
        var tag = stack.getOrCreateTag();
        return tag.contains("time") ? tag.getInt("time") : 0;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ShakerCapabilityProvider();
    }

    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        var result = Objects.requireNonNullElse(super.getShareTag(stack), new CompoundTag());
        stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                .ifPresent(h -> result.put("items", ((ItemStackHandler) h).serializeNBT()));
        result.putBoolean("finish", getFinish(stack));
        result.putInt("time", getRecipeTime(stack));
        return result;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
        super.readShareTag(stack, nbt);

        if (nbt != null) {
            stack.getCapability(ForgeCapabilities.ITEM_HANDLER)
                    .ifPresent(h -> ((ItemStackHandler) h).deserializeNBT(nbt.getCompound("items")));
            setFinish(stack, nbt.getBoolean("finish"));
            setRecipeTime(stack, nbt.getInt("time"));
        }
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }
}
