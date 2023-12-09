package io.github.tt432.kitchenkarrot.recipes.base;

import com.mojang.serialization.Codec;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.RecipeSerializer;

/**
 * @author DustW
 **/
public record BaseSerializer<RECIPE extends BaseRecipe>(Codec<RECIPE> codec)
        implements RecipeSerializer<RECIPE> {

    @Override
    public RECIPE fromNetwork(FriendlyByteBuf buf) {
        return buf.readJsonWithCodec(this.codec());
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, RECIPE pRecipe) {
        pBuffer.writeJsonWithCodec(this.codec(), pRecipe);
    }
}
