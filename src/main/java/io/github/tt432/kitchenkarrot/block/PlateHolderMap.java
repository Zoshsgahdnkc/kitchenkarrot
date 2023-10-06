package io.github.tt432.kitchenkarrot.block;

import com.google.common.collect.ImmutableMap;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;

public class PlateHolderMap {
    public static Map<Item, Integer> plateHolder = new ImmutableMap.Builder<Item, Integer>()
            .put(ModItems.SIRLOIN_STEAK.get(), 1)
            .put(ModItems.BEEF_GRAINS.get(), 6)
            .put(ModItems.CHORUS_MOUSSE.get(), 1)
            .put(ModItems.SMALL_CHORUS_MOUSSE.get(), 4)
            .put(ModItems.SLIME_MOUSSE.get(), 1)
            .put(ModItems.SMALL_SLIME_MOUSSE.get(), 4)
            .put(ModItems.FEAST_PIZZA.get(), 1)
            .put(ModItems.FEAST_PIZZA_SLICE.get(), 4)
            .put(ModItems.SHINY_PIZZA.get(), 1)
            .put(ModItems.SHINY_PIZZA_SLICE.get(), 4)
            .put(ModItems.DUNGEON_PIZZA.get(), 1)
            .put(ModItems.DUNGEON_PIZZA_SLICE.get(), 4)
            .put(ModItems.MONSTER_LASAGNA.get(), 1)
            .put(ModItems.SMALL_MONSTER_LASAGNA.get(), 4)
            .put(ModItems.SWEET_LOAF.get(), 1)
            .put(ModItems.SWEET_LOAF_SLICE.get(), 3)
            .put(ModItems.FRIED_PUMPKIN_CAKE.get(), 5)
            .put(ModItems.SEED_PIE.get(), 5)
            .put(ModItems.RICE_CAKE.get(), 10)
            .put(ModItems.VERDANT_NAMA_CHOCO.get(), 10)
            .put(ModItems.FRIED_CHICKEN_COMBO.get(), 1)
            .put(ModItems.BEEF_IN_DRIPLEAF.get(), 1)
            .put(ModItems.SMALL_BEEF_IN_DRIPLEAF.get(), 4)
            .put(Items.MELON, 1)
            .put(Items.MELON_SLICE, 8)
            .build();

    public static boolean canPutOnPlate(Item item) {
        return plateHolder.containsKey(item);
    }
}
