package io.github.tt432.kitchenkarrot.gui;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.blockentity.BrewingBarrelBlockEntity;
import io.github.tt432.kitchenkarrot.gui.base.KKGui;
import io.github.tt432.kitchenkarrot.gui.widget.ProgressWidget;
import io.github.tt432.kitchenkarrot.menu.BrewingBarrelMenu;
import io.github.tt432.kitchenkarrot.networking.packet.C2SUpdateBarrelPacket;
import io.github.tt432.kitchenkarrot.recipes.recipe.BrewingBarrelRecipe;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.IFluidTank;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Optional;

/**
 * @author DustW
 **/
public class BrewingBarrelGui extends KKGui<BrewingBarrelMenu> {

    public static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    Kitchenkarrot.MOD_ID, "textures/gui/brewing_barrel.png");

    public BrewingBarrelGui(BrewingBarrelMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, TEXTURE);
    }

    @Override
    protected void init() {
        super.init();
        BrewingBarrelBlockEntity be = this.menu.blockEntity;
        if (be.getLevel() != null) {
            IFluidHandler capability =
                    be.getLevel()
                            .getCapability(Capabilities.FluidHandler.BLOCK, be.getBlockPos(), null);
            if (capability instanceof IFluidTank tank) {
                addRenderableWidget(
                        new ProgressWidget(
                                this,
                                TEXTURE,
                                leftPos + 21,
                                topPos + 23,
                                182,
                                0,
                                9,
                                42,
                                true,
                                () ->
                                        Component.translatable(
                                                tank.getFluidAmount()
                                                        + "mB / "
                                                        + tank.getCapacity()
                                                        + "mB"),
                                true,
                                tank::getCapacity,
                                tank::getFluidAmount));

                addRenderableWidget(
                        new ProgressWidget(
                                this,
                                TEXTURE,
                                leftPos + 152,
                                topPos + 23,
                                178,
                                0,
                                4,
                                42,
                                true,
                                () -> {
                                    if (be.isStarted()) {
                                        return Component.literal(
                                                be.getProgress() * 100 / be.getMaxProgress() + "%");
                                    } else {
                                        Optional<RecipeHolder<BrewingBarrelRecipe>> recipe =
                                                be.findRecipe();
                                        if (recipe.isPresent()
                                                && be.hasEnoughWater(recipe.get().value())) {
                                            return Component.translatable(
                                                    "info.kitchenkarrot.brewing_barrel.not_enough_liquid");
                                        } else if (!be.isRecipeSame()) {
                                            return Component.translatable(
                                                    "info.kitchenkarrot.brewing_barrel.error_recipe");
                                        } else {
                                            return Component.empty();
                                        }
                                    }
                                },
                                true,
                                be::getMaxProgress,
                                be::getProgress));
            }
        }
    }

    @Override
    public void onClose() {
        super.onClose();
        BrewingBarrelBlockEntity blockEntity = this.getMenu().blockEntity;
        PacketDistributor.sendToServer(new C2SUpdateBarrelPacket(blockEntity.getBlockPos(), false));
    }
}
