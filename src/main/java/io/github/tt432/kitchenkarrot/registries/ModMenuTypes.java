package io.github.tt432.kitchenkarrot.registries;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.menu.AirCompressorMenu;
import io.github.tt432.kitchenkarrot.menu.BrewingBarrelMenu;
import io.github.tt432.kitchenkarrot.menu.ShakerMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author DustW
 **/
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Kitchenkarrot.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<AirCompressorMenu>> AIR_COMPRESSOR =
            MENUS.register("air_compressor", () -> from(AirCompressorMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<BrewingBarrelMenu>> BREWING_BARREL =
            MENUS.register("brewing_barrel", () -> from(BrewingBarrelMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<ShakerMenu>> SHAKER =
            MENUS.register(
                    "shaker", () -> new MenuType<>(ShakerMenu::new, FeatureFlags.VANILLA_SET));

    private interface KKBeMenuCreator<M extends AbstractContainerMenu, T extends BlockEntity> {
        M create(int windowId, Inventory inv, T blockEntity);
    }

    private static <M extends AbstractContainerMenu, T extends BlockEntity> MenuType<M> from(
            KKBeMenuCreator<M, T> creator) {
        return IMenuTypeExtension.create(
                (id, inv, data) ->
                        creator.create(
                                id,
                                inv,
                                (T) inv.player.level().getBlockEntity(data.readBlockPos())));
    }
}
