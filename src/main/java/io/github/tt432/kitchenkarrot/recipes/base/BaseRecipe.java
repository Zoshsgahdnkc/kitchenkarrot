package io.github.tt432.kitchenkarrot.recipes.base;

import com.google.gson.annotations.Expose;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * @author DustW
 **/
public abstract class BaseRecipe<SELF extends BaseRecipe<SELF>> implements Recipe<Container> {
    @Expose(deserialize = false, serialize = false)
    ResourceLocation id;
    @Expose
    public String type;

    public abstract boolean matches(List<ItemStack> inputs);

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_) {
        return getResultItem(p_267165_);
    }

    public ResourceLocation getId() {
        return id;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    public SELF setID(ResourceLocation id) {
        this.id = id;
        return self();
    }

    private SELF self() {
        return (SELF) this;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BaseRecipe recipe && recipe.id.equals(id);
    }
}
