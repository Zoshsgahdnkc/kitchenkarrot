package io.github.tt432.kitchenkarrot.glm;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AddItemModifier extends LootModifier {
    public static final Supplier<MapCodec<AddItemModifier>> CODEC =
            Suppliers.memoize(
                    () ->
                            RecordCodecBuilder.mapCodec(
                                    inst ->
                                            codecStart(inst)
                                                    .and(
                                                            ItemStack.CODEC
                                                                    .fieldOf("item")
                                                                    .forGetter(m -> m.itemStack))
                                                    .apply(inst, AddItemModifier::new)));
    private final ItemStack itemStack;

    public AddItemModifier(LootItemCondition[] conditionsIn, ItemStack itemStack) {
        super(conditionsIn);
        this.itemStack = itemStack;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(itemStack);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
