package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.cocktail.CocktailProperty;
import io.github.tt432.kitchenkarrot.config.ModCommonConfigs;
import io.github.tt432.kitchenkarrot.recipes.object.EffectStack;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;
import io.github.tt432.kitchenkarrot.registries.ModDataComponents;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author DustW
 **/
@ParametersAreNonnullByDefault
public class CocktailItem extends Item {
    public static final ResourceLocation UNKNOWN_COCKTAIL =
            ResourceLocation.fromNamespaceAndPath(Kitchenkarrot.MOD_ID, "unknown");
    public static final CocktailProperty UNKNOWN_COCKTAIL_PROPERTY =
            new CocktailProperty(UNKNOWN_COCKTAIL, "", List.of());

    public CocktailItem() {
        super(
                ModItems.defaultProperties()
                        .food(new FoodProperties.Builder().alwaysEdible().build()));
    }

    public static ItemStack unknownCocktail() {
        var stack = new ItemStack(ModItems.COCKTAIL.get());
        setCocktail(stack, UNKNOWN_COCKTAIL_PROPERTY);
        return stack;
    }

    @Override
    @NotNull
    public ItemStack finishUsingItem(ItemStack stack, Level pLevel, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer player) {
            CocktailProperty cocktailProperty = getCocktail(stack);
            ResourceLocation cocktail = cocktailProperty.id();
            if (cocktail != null) {
                CocktailRecipe recipe = get(pLevel, cocktailProperty);
                if (recipe != null) {
                    player.getFoodData().eat(recipe.content.hunger(), recipe.content.saturation());
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                    for (EffectStack effectStack : getCocktail(stack).effectStack()) {
                        player.addEffect(effectStack.get());
                    }
                } else if (cocktail.equals(UNKNOWN_COCKTAIL)) {
                    player.addEffect(
                            new MobEffectInstance(getUnknownCocktailEffect(pLevel), 100, 0));
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                }
            }
        }
        return stack;
    }

    private Holder<MobEffect> getUnknownCocktailEffect(Level level) {
        List<String> list = ModCommonConfigs.UNKNOWN_COCKTAIL_EFFECTS_BLACKLIST.get();
        List<MobEffect> effects = new ArrayList<>();
        for (String s : list)
            try {
                effects.add(BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.parse(s)));
            } catch (Exception ignored) {
            }
        if (!ModCommonConfigs.WHITELIST_MODE.get()) {
            List<Holder<MobEffect>> values =
                    BuiltInRegistries.MOB_EFFECT.stream()
                            .filter(mobEffect -> !effects.contains(mobEffect))
                            .map(BuiltInRegistries.MOB_EFFECT::wrapAsHolder)
                            .toList();
            return values.get(level.getRandom().nextInt(values.size()));
        }
        return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(
                effects.get(level.getRandom().nextInt(effects.size())));
    }

    @Override
    @NotNull
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    @NotNull
    public String getDescriptionId(ItemStack stack) {
        CocktailProperty name = getCocktail(stack);

        return name.toString().replace(":", ".");
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        ResourceLocation name = Objects.requireNonNull(getCocktail(stack)).id();
        if (name != null) {
            tooltipComponents.add(
                    Component.translatable(name.toString().replace(":", ".") + ".tooltip"));
            if (Minecraft.getInstance().level != null) {

                tooltipComponents.add(
                        Component.translatable(
                                "item.cocktail.author", getCocktail(stack).author()));

                List<MobEffectInstance> list =
                        getCocktail(stack).effectStack().stream().map(EffectStack::get).toList();

                PotionContents.addPotionTooltip(
                        list, tooltipComponents::add, 1.0F, context.tickRate());
            }
        }
    }

    @Nonnull
    public static CocktailProperty getCocktail(ItemStack itemStack) {
        if (itemStack.getComponents().has(ModDataComponents.COCKTAIL.get())) {
            return Objects.requireNonNull(itemStack.get(ModDataComponents.COCKTAIL));
        }
        return CocktailItem.UNKNOWN_COCKTAIL_PROPERTY;
    }

    public static void setCocktail(ItemStack itemStack, CocktailProperty cocktailProperty) {
        itemStack.set(ModDataComponents.COCKTAIL, cocktailProperty);
    }

    @Nullable
    public static CocktailRecipe get(Level level, CocktailProperty cocktailProperty) {
        Optional<RecipeHolder<?>> result = level.getRecipeManager().byKey(cocktailProperty.id());

        if (result.isPresent() && result.get().value().getType() == RecipeTypes.COCKTAIL.get()) {
            return (CocktailRecipe) result.get().value();
        }

        return null;
    }
}
