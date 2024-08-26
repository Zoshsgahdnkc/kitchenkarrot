package io.github.tt432.kitchenkarrot.item;

import static io.github.tt432.kitchenkarrot.registries.ModItems.cocktailProperties;

import io.github.tt432.kitchenkarrot.menu.ShakerMenu;
import io.github.tt432.kitchenkarrot.registries.ModDataComponents;
import io.github.tt432.kitchenkarrot.registries.ModSoundEvents;
import io.github.tt432.kitchenkarrot.util.SoundUtil;

import net.minecraft.client.Minecraft;
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
    public InteractionResultHolder<ItemStack> use(
            Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        var stack = pPlayer.getItemInHand(pUsedHand);

        if (pUsedHand == InteractionHand.MAIN_HAND) {
            if (pPlayer.isShiftKeyDown()) {
                if (!pLevel.isClientSide) {
                    pPlayer.openMenu(
                            new SimpleMenuProvider(
                                    (id, inv, player) -> new ShakerMenu(id, inv),
                                    stack.getDisplayName()));
                } else {
                    pPlayer.playSound(
                            ModSoundEvents.SHAKER_OPEN.get(),
                            0.5F,
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
    public void inventoryTick(
            ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pLevel.isClientSide && pEntity instanceof Player player) {
            if (player.getUseItem() != pStack) {
                Minecraft.getInstance()
                        .getSoundManager()
                        .stop(ModSoundEvents.SHAKER.get().getLocation(), player.getSoundSource());
            }
        }

        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player) {
            setFinish(pStack, true);

            if (pLevel.isClientSide) {
                Minecraft.getInstance()
                        .getSoundManager()
                        .stop(ModSoundEvents.SHAKER.get().getLocation(), player.getSoundSource());
                pLivingEntity.playSound(
                        ModSoundEvents.COCKTAIL_COMPLETE.get(),
                        0.5F,
                        pLevel.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        return pStack;
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
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }
}
