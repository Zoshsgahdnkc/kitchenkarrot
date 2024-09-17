package io.github.tt432.kitchenkarrot.item;

import static io.github.tt432.kitchenkarrot.registries.ModItems.cocktailProperties;

import io.github.tt432.kitchenkarrot.menu.ShakerMenu;
import io.github.tt432.kitchenkarrot.registries.ModDataComponents;
import io.github.tt432.kitchenkarrot.registries.ModSoundEvents;
import io.github.tt432.kitchenkarrot.util.SoundUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

/**
 * @author DustW
 **/
public class ShakerItem extends Item {
    public ShakerItem() {
        super(cocktailProperties().stacksTo(1));
    }

    @Override
    @NotNull
    public InteractionResultHolder<ItemStack> use(
            @NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (usedHand == InteractionHand.MAIN_HAND) {
            if (player.isShiftKeyDown()) {
                if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.openMenu(
                            new SimpleMenuProvider(
                                    (id, inv, player1) -> new ShakerMenu(id, inv),
                                    stack.getDisplayName()));
                } else {
                    player.playSound(
                            ModSoundEvents.SHAKER_OPEN.get(),
                            0.5F,
                            level.random.nextFloat() * 0.1F + 0.9F);
                }

                return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
            } else if (!getFinish(stack)) {
                player.startUsingItem(usedHand);
                if (level.isClientSide) {
                    SoundUtil.shakerSound(player, level);
                }
                return InteractionResultHolder.success(stack);
            }
        }

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void inventoryTick(
            @NotNull ItemStack itemStack,
            Level pLevel,
            @NotNull Entity entity,
            int slotId,
            boolean isSelected) {
        if (pLevel.isClientSide && entity instanceof Player player) {
            if (player.getUseItem() != itemStack) {
                Minecraft.getInstance()
                        .getSoundManager()
                        .stop(ModSoundEvents.SHAKER.get().getLocation(), player.getSoundSource());
            }
        }

        super.inventoryTick(itemStack, pLevel, entity, slotId, isSelected);
    }

    @Override
    @NotNull
    public ItemStack finishUsingItem(
            @NotNull ItemStack itemStack,
            @NotNull Level level,
            @NotNull LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            setFinish(itemStack, true);

            if (level.isClientSide) {
                Minecraft.getInstance()
                        .getSoundManager()
                        .stop(ModSoundEvents.SHAKER.get().getLocation(), player.getSoundSource());
                livingEntity.playSound(
                        ModSoundEvents.COCKTAIL_COMPLETE.get(),
                        0.5F,
                        level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        return itemStack;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
        return getRecipeTime(stack);
    }

    public static void setFinish(ItemStack stack, boolean finish) {
        stack.set(ModDataComponents.FINISH, finish);
    }

    public static boolean getFinish(ItemStack stack) {
        return stack.getComponents().getOrDefault(ModDataComponents.FINISH.get(), false);
    }

    public static void setRecipeTime(ItemStack stack, int time) {
        stack.set(ModDataComponents.RECIPE_TIME, time);
    }

    public static int getRecipeTime(ItemStack stack) {
        return stack.getComponents().getOrDefault(ModDataComponents.RECIPE_TIME.get(), 0);
    }

    @Override
    @NotNull
    public UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.DRINK;
    }
}
