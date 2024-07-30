package io.github.tt432.kitchenkarrot.datagen.provider;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.glm.AddItemModifier;
import io.github.tt432.kitchenkarrot.glm.ReplaceLootModifier;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class MODGLMProvider extends GlobalLootModifierProvider {
    public MODGLMProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Kitchenkarrot.MOD_ID);
    }

    @Override
    protected void start() {
        add(
                "gem_carrot_loot_modifier",
                new AddItemModifier(
                        new LootItemCondition[] {
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(
                                            Blocks.CARROTS)
                                    .setProperties(
                                            StatePropertiesPredicate.Builder.properties()
                                                    .hasProperty(BlockStateProperties.AGE_7, 7))
                                    .build(),
                            LootItemRandomChanceCondition.randomChance(0.04F).build()
                        },
                        ModItems.GEM_CARROT.toStack()));

        add(
                "ice_cubes_loot_modifier",
                new AddItemModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            ResourceLocation.withDefaultNamespace("blocks/ice"))
                                    .build(),
                            MatchTool.toolMatches(
                                            ItemPredicate.Builder.item().of(ItemTags.PICKAXES))
                                    .build()
                        },
                        ModItems.ICE_CUBES.toStack()));

        add(
                "piglin_barter_loot_modifier",
                new ReplaceLootModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            ResourceLocation.withDefaultNamespace(
                                                    "gameplay/piglin_bartering"))
                                    .build()
                        },
                        ModItems.CANNED_HOGLIN_CONFIT.toStack(),
                        45,
                        6));

        add(
                "pillager_pie_from_mansion_loot_modifier",
                new AddItemModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            ResourceLocation.withDefaultNamespace(
                                                    "chests/pillager_outpost"))
                                    .build(),
                            LootItemRandomChanceCondition.randomChance(0.3F).build()
                        },
                        ModItems.PILLAGER_PIE.toStack()));

        add(
                "pillager_pie_from_outpost_loot_modifier",
                new AddItemModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            ResourceLocation.withDefaultNamespace(
                                                    "chests/pillager_outpost"))
                                    .build()
                        },
                        ModItems.PILLAGER_PIE.toStack()));

        add(
                "pillager_pie_from_pillager_loot_modifier",
                new AddItemModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            ResourceLocation.withDefaultNamespace(
                                                    "entities/pillager"))
                                    .build(),
                            LootItemRandomChanceCondition.randomChance(0.2F).build()
                        },
                        ModItems.PILLAGER_PIE.toStack()));
    }
}
