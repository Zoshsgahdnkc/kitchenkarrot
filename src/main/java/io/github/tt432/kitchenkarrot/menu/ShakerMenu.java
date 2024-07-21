package io.github.tt432.kitchenkarrot.menu;

import io.github.tt432.kitchenkarrot.item.CocktailItem;
import io.github.tt432.kitchenkarrot.item.ShakerItem;
import io.github.tt432.kitchenkarrot.menu.base.KKMenu;
import io.github.tt432.kitchenkarrot.menu.slot.KKResultSlot;
import io.github.tt432.kitchenkarrot.recipes.RecipeManager;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;
import io.github.tt432.kitchenkarrot.registries.ModMenuTypes;
import io.github.tt432.kitchenkarrot.registries.ModSoundEvents;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author DustW
 **/
public class ShakerMenu extends KKMenu {
    ItemStack itemStack;

    public ShakerMenu(int pContainerId, Inventory inventory) {
        super(ModMenuTypes.SHAKER.get(), pContainerId, inventory);

        itemStack = inventory.getSelected();

        if (!(itemStack.getItem() instanceof ShakerItem)) {
            removed(inventory.player);
            return;
        }

        addSlots();
        finishRecipe(inventory.player);
    }

    /**
     * temporary, to sync two sides
     */
    private void sync() {
        IItemHandler capability = Capabilities.ItemHandler.ITEM.getCapability(itemStack, null);
        if (capability != null) {
            slotChanged(capability);
        }
    }

    /**
     * trigger when finish
     *
     * @param player
     */
    private void finishRecipe(Player player) {
        if (ShakerItem.getFinish(itemStack)) {
            IItemHandler handler = Capabilities.ItemHandler.ENTITY_AUTOMATION.getCapability(player, null);

            if (handler != null) {
                if (player.level().isClientSide) {
                    return;
                }

                List<ItemStack> inputs = getInputs(handler);
                Optional<RecipeHolder<CocktailRecipe>> recipeHolder = RecipeManager.getCocktailRecipes(inventory.player.level()).stream().filter(holder -> holder.value().matches(inputs)).findFirst();

                ItemStack recipeResult = CocktailItem.unknownCocktail();

                if (recipeHolder.isPresent()) {
                    recipeResult = recipeHolder.get().value().getResultItem(RegistryAccess.EMPTY);
                }

                if (inputs.stream().anyMatch(ItemStack::isEmpty)) {
                    return;
                }

                ItemStack result = handler.insertItem(11, recipeResult, false);

                if (!result.isEmpty()) {
                    inventory.player.drop(result, true);
                }

                for (int i = 0; i < 5; i++) {
                    handler.extractItem(i, 1, false);
                }

                slotChanged(handler);
            }

            ShakerItem.setFinish(itemStack, false);
        }
    }

    List<ItemStack> getInputs(IItemHandler handler) {
        var list = new ArrayList<ItemStack>();

        for (int i = 0; i < 5; i++) {
            list.add(handler.getStackInSlot(i));
        }

        return list;
    }

    void slotChanged(IItemHandler handler) {
        if (!handler.getStackInSlot(11).isEmpty()) {
            ShakerItem.setRecipeTime(itemStack, 0);
            return;
        }

        var list = getInputs(handler);

        var recipe = RecipeManager.getCocktailRecipes(inventory.player.level())
                .stream().filter(r -> r.value().matches(list)).findFirst();
        if (recipe.isPresent()) {
            ShakerItem.setRecipeTime(itemStack, recipe.get().value().getContent().craftingTime());
        } else {
            if (list.stream().anyMatch(ItemStack::isEmpty)) {
                ShakerItem.setRecipeTime(itemStack, 0);
            } else {
                ShakerItem.setRecipeTime(itemStack, 60);
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (index == slots.size() - 1) {
            sound();
        }
        return super.quickMoveStack(player, index);
    }

    // init
    @Override
    protected Slot addSlot(IItemHandler handler, int index, int x, int y) {
        return addSlot(new SlotItemHandler(handler, index, x, y) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(handler);
            }
        });
    }

    protected void sound() {
        var player = inventory.player;

        if (player.level().isClientSide) {
            player.playSound(ModSoundEvents.SHAKER_COCKTAIL.get(), 0.5F,
                    player.getRandom().nextFloat() * 0.1F + 0.9F);
        }
    }

    // init
    @Override
    protected Slot addResultSlot(IItemHandler handler, int index, int x, int y) {
        return addSlot(new KKResultSlot(handler, index, x, y) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotChanged(handler);
            }
        });
    }

    void addSlots() {
        IItemHandler handler = Capabilities.ItemHandler.ITEM.getCapability(itemStack, null);
        if (handler != null) {
            addSlot(handler, 0, 62, 22);
            addSlot(handler, 1, 80, 22);
            addSlot(handler, 2, 98, 22);
            addSlot(handler, 3, 71, 40);
            addSlot(handler, 4, 89, 40);

            addSlot(handler, 5, 8, 15);
            addSlot(handler, 6, 26, 15);
            addSlot(handler, 7, 8, 33);
            addSlot(handler, 8, 26, 33);

            addSlot(handler, 9, 8, 51);
            addSlot(handler, 10, 26, 51);

            addResultSlot(handler, 11, 144, 22);
        }
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        sync();

        if (pPlayer.level().isClientSide) {
            pPlayer.playSound(ModSoundEvents.SHAKER_CLOSE.get(), 0.5F,
                    pPlayer.getRandom().nextFloat() * 0.1F + 0.9F);
        }
    }

    @Override
    protected void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(invHandler, 9, 8, 80, 9, 18, 3, 18);

        // Hotbar
        addSlotRange(invHandler, 0, 8, 138, 9, 18);
    }

    @Override
    public void clicked(int slot, int button, @NotNull ClickType type, @NotNull Player player) {
        try {
            Slot slotInstance = slots.get(slot);
            if (slotInstance.getItem().getItem() instanceof ShakerItem) {
                return;
            }
        } catch (Exception ignored) {

        }

        super.clicked(slot, button, type, player);
    }
}
