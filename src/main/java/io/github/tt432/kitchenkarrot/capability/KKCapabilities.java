package io.github.tt432.kitchenkarrot.capability;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.item.ShakerItem;
import io.github.tt432.kitchenkarrot.registries.ModBlockEntities;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;

import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.ItemStackHandler;

import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Kitchenkarrot.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class KKCapabilities {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        // BlockEntity
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.AIR_COMPRESSOR.get(),
                (blockEntity, direction) ->
                        switch (direction) {
                            case DOWN -> blockEntity.getOutput();
                            case UP -> blockEntity.getInput1();
                            case NORTH, SOUTH, WEST, EAST -> blockEntity.getInput2();
                            case null -> null;
                        });

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.BREWING_BARREL.get(),
                (blockEntity, direction) ->
                        switch (direction) {
                            case DOWN -> blockEntity.getResult();
                            case UP, NORTH, SOUTH, WEST, EAST -> blockEntity.getInput();
                            case null -> null;
                        });

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntities.BREWING_BARREL.get(),
                (blockEntity, direction) -> blockEntity.getTank());

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.COASTER.get(),
                (blockEntity, direction) -> blockEntity.getItem());

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.PLATE.get(),
                (blockEntity, direction) -> blockEntity.getItem());

        // Item
        // spotless:off
        event.registerItem(
                Capabilities.ItemHandler.ITEM,
                (itemStack, unused) ->
                        new ItemStackHandler(12) {
                            @Override
                            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                                return !(stack.getItem() instanceof ShakerItem)
                                        && (slot < 5 || slot > 10 || (slot < 9 ? stack.is(ModItemTags.BASE)
                                        : slot == 9 ? stack.is(ModItems.ICE_CUBES.get())
                                        : stack.is(
                                        ModItems.CARROT_SPICES
                                                .get())));
                            }
                        },
                ModItems.SHAKER);
        // spotless:on
    }
}
