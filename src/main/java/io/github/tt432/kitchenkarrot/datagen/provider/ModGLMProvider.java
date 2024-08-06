package io.github.tt432.kitchenkarrot.datagen.provider;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.glm.AddItemModifier;
import io.github.tt432.kitchenkarrot.glm.ReplaceLootModifier;
import io.github.tt432.kitchenkarrot.registries.ModItems;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGLMProvider extends GlobalLootModifierProvider {
    public ModGLMProvider(PackOutput output) {
        super(output, Kitchenkarrot.MOD_ID);
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
                        ModItems.GEM_CARROT.get().getDefaultInstance()));

        add(
                "ice_cubes_loot_modifier",
                new AddItemModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            new ResourceLocation("blocks/ice"))
                                    .build(),
                            MatchTool.toolMatches(
                                            ItemPredicate.Builder.item().of(ItemTags.PICKAXES))
                                    .build()
                        },
                        ModItems.ICE_CUBES.get().getDefaultInstance()));

        add(
                "piglin_barter_loot_modifier",
                new ReplaceLootModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            new ResourceLocation(
                                                    "gameplay/piglin_bartering"))
                                    .build()
                        },
                        ModItems.CANNED_HOGLIN_CONFIT.get().getDefaultInstance(),
                        45,
                        6));

        add(
                "pillager_pie_from_mansion_loot_modifier",
                new AddItemModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            new ResourceLocation(
                                                    "chests/pillager_outpost"))
                                    .build(),
                            LootItemRandomChanceCondition.randomChance(0.3F).build()
                        },
                        ModItems.PILLAGER_PIE.get().getDefaultInstance()));

        add(
                "pillager_pie_from_outpost_loot_modifier",
                new AddItemModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            new ResourceLocation(
                                                    "chests/pillager_outpost"))
                                    .build()
                        },
                        ModItems.PILLAGER_PIE.get().getDefaultInstance()));

        add(
                "pillager_pie_from_pillager_loot_modifier",
                new AddItemModifier(
                        new LootItemCondition[] {
                            LootTableIdCondition.builder(
                                            new ResourceLocation(
                                                    "entities/pillager"))
                                    .build(),
                            LootItemRandomChanceCondition.randomChance(0.2F).build()
                        },
                        ModItems.PILLAGER_PIE.get().getDefaultInstance()));
    }
}
