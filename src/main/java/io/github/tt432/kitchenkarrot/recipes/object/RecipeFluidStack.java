package io.github.tt432.kitchenkarrot.recipes.object;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.neoforged.neoforge.fluids.FluidStack;

/**
 * @author DustW
 **/
@Deprecated
public class RecipeFluidStack {
    public static final Codec<RecipeFluidStack> CODEC =
            RecordCodecBuilder.create(
                    builder ->
                            builder.group(
                                            Codec.STRING
                                                    .fieldOf("fluid_name")
                                                    .forGetter(recipe -> recipe.fluidName),
                                            Codec.INT
                                                    .fieldOf("amount")
                                                    .forGetter(recipe -> recipe.amount))
                                    .apply(builder, RecipeFluidStack::new));

    public String fluidName;
    public int amount;

    public RecipeFluidStack(String fluidName, int amount) {
        this.fluidName = fluidName;
        this.amount = amount;
    }

    public RecipeFluidStack(FluidStack fluidStack) {
        this(fluidStack.getFluid().getFluidType().getDescriptionId(), fluidStack.getAmount());
        //        this(fluidStack.getFluid().getRegistryName().toString(), fluidStack.getAmount());
    }
}
