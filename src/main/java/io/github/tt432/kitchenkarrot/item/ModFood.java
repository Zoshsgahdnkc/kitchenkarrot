package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

public class ModFood extends ModItem {
    protected UseAnim foodType = UseAnim.EAT;
    protected Duration duration = Duration.Normal;
    protected EffectEntry[] effectEntries;
    protected Item returnItem;

    public ModFood(Properties pProperties) {
        super(pProperties);
    }

    public ModFood(int nutrition, float saturation, EffectEntry... effectEntries) {
        this(nutrition, saturation, false, effectEntries);
        this.effectEntries = effectEntries;
    }

    public ModFood(
            int nutrition, float saturation, boolean alwaysEat, EffectEntry... effectEntries) {
        super(
                FoodUtil.effectFood(
                        ModItems.defaultProperties(),
                        nutrition,
                        saturation,
                        alwaysEat,
                        effectEntries));
        this.effectEntries = effectEntries;
    }

    public ModFood(int nutrition, float saturation, int stackSize, EffectEntry... effectEntries) {
        this(nutrition, saturation, stackSize, false, effectEntries);
        this.effectEntries = effectEntries;
    }

    public ModFood(
            int nutrition,
            float saturation,
            int stackSize,
            boolean alwaysEat,
            EffectEntry... effectEntries) {
        super(
                FoodUtil.effectFood(
                                ModItems.defaultProperties(),
                                nutrition,
                                saturation,
                                alwaysEat,
                                effectEntries)
                        .stacksTo(stackSize));
        this.effectEntries = effectEntries;
    }

    public static ModFood drinkItem(int nutrition, float saturation, EffectEntry... effectEntries) {
        ModFood item = new ModFood(nutrition, saturation, true, effectEntries).setDrinkAnim();
        item.effectEntries = effectEntries;
        return item;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (this.effectEntries != null && this.effectEntries.length > 0) {
            PotionContents.addPotionTooltip(
                    Arrays.stream(effectEntries).map(e -> e.effect.get()).toList(),
                    tooltipComponents::add,
                    1.0F,
                    context.tickRate());
        }
    }

    @Override
    @NotNull
    public UseAnim getUseAnimation(ItemStack stack) {
        return foodType;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return duration.time;
    }

    @Override
    public ItemStack finishUsingItem(
            @NotNull ItemStack stack, @NotNull Level level, LivingEntity livingEntity) {
        stack = livingEntity.eat(level, stack);
        if (returnItem == null) return stack;
        if (livingEntity instanceof Player player) {
            player.getInventory().add(new ItemStack(returnItem));
        }
        return stack;
    }

    public ModFood setFoodType(@NotNull UseAnim foodType) {
        this.foodType = foodType;
        return this;
    }

    public ModFood setDrinkAnim() {
        setFoodType(UseAnim.DRINK);
        return this;
    }

    public ModFood setDuration(@NotNull Duration duration) {
        this.duration = duration;
        return this;
    }

    public ModFood setBowlFood() {
        setReturnItem(Items.BOWL);
        return this;
    }

    public ModFood setBottleFood() {
        setReturnItem(Items.GLASS_BOTTLE);
        return this;
    }

    public ModFood setReturnItem(@NotNull Item item) {
        this.returnItem = item;
        return this;
    }

    public ModFood setEffectEntries(EffectEntry[] effectEntries) {
        this.effectEntries = effectEntries;
        return this;
    }

    public enum Duration {
        SunflowerKelp(12),
        VeryFast(16),
        Fast(24),
        Normal(32),
        Slow(48),
        VerySlow(64),
        CurryUdon(96);
        private final int time;

        Duration(int duration) {
            this.time = duration;
        }
    }
}
