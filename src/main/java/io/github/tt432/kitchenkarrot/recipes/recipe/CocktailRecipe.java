package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.item.CocktailItem;
import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.recipes.object.EffectStack;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import io.github.tt432.kitchenkarrot.registries.RecipeSerializers;
import io.github.tt432.kitchenkarrot.registries.RecipeTypes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.RecipeMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class CocktailRecipe extends BaseRecipe {
    public static final Codec<CocktailRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            ResourceLocation.CODEC.fieldOf("cocktail_id")
                    .xmap(resourceLocation -> new ResourceLocation(Kitchenkarrot.MOD_ID, "cocktails" + "/" + resourceLocation.getPath()),
                            resourceLocation -> resourceLocation)
                    .orElse(new ResourceLocation(""))
                    .forGetter(recipe -> new ResourceLocation(recipe.cocktailID.getPath())),
            Codec.STRING.fieldOf("author").forGetter(recipe -> recipe.author),
            Content.CODEC.fieldOf("content").forGetter(recipe -> recipe.content)
    ).apply(builder, CocktailRecipe::new));


    public CocktailRecipe(ResourceLocation cocktailID, String author, Content content) {
        this.cocktailID = cocktailID;
        this.author = author;
        this.content = content;
    }

    @Override
    public boolean matches(List<ItemStack> inputs) {
        return RecipeMatcher.findMatches(inputs, content.recipe) != null;
    }

    @Override
    public String getId() {
        return cocktailID.toString();
    }

    private final ResourceLocation cocktailID;
    ItemStack result;

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        if (result == null) {
            result = new ItemStack(ModItems.COCKTAIL.get());
            CocktailItem.setCocktail(result, new ResourceLocation(getId()));
        }
        return result.copy();
    }

//    @Override
//    public ItemStack getResultItem() {
//        if (result == null) {
//            result = new ItemStack(ModItems.COCKTAIL.get());
//            CocktailItem.setCocktail(result, getId());
//        }
//        return result.copy();
//    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.COCKTAIL.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypes.COCKTAIL.get();
    }

    public String author;
    public Content content;

    public record Content(List<Ingredient> recipe, int craftingTime, int hunger, int saturation,
                          List<EffectStack> effect) {
        public static final Codec<Content> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("recipe").forGetter(Content::recipe),
                Codec.INT.fieldOf("craftingtime").forGetter(Content::craftingTime),
                Codec.INT.fieldOf("hunger").forGetter(Content::hunger),
                Codec.INT.fieldOf("saturation").forGetter(Content::saturation),
                EffectStack.CODEC.listOf().fieldOf("effect").orElse(new ArrayList<>()).forGetter(Content::effect)
        ).apply(builder, Content::new));
    }

    public Content getContent() {
        return content;
    }
}
