package io.github.tt432.kitchenkarrot.glm;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ReplaceLootModifier extends LootModifier {
    public static final Supplier<Codec<ReplaceLootModifier>> CODEC =
            Suppliers.memoize(
                    () ->
                            RecordCodecBuilder.create(
                                    inst ->
                                            codecStart(inst)
                                                    .and(
                                                            inst.group(
                                                                    ItemStack.CODEC
                                                                            .fieldOf("item")
                                                                            .forGetter(
                                                                                    m ->
                                                                                            m.itemStack),
                                                                    Codec.INT
                                                                            .optionalFieldOf(
                                                                                    "weight", 1)
                                                                            .forGetter(
                                                                                    m -> m.weight),
                                                                    Codec.INT
                                                                            .optionalFieldOf(
                                                                                    "count", 1)
                                                                            .forGetter(
                                                                                    m -> m.count)))
                                                    .apply(inst, ReplaceLootModifier::new)));
    private final ItemStack itemStack;
    private final int weight;
    private final int count;

    public ReplaceLootModifier(
            LootItemCondition[] conditionsIn, ItemStack itemStack, int weight, int count) {
        super(conditionsIn);
        this.itemStack = itemStack;
        this.weight = weight;
        this.count = count;
    }

    @Override
    @NotNull
    protected ObjectArrayList<ItemStack> doApply(
            @NotNull ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextInt(900) < weight) {
            generatedLoot.clear();
            ItemStack copy = itemStack.copy();
            copy.setCount(context.getRandom().nextInt(count) + 1);
            generatedLoot.add(copy);
        }
        return generatedLoot;
    }

    @Override
    @NotNull
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
