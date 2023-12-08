package io.github.tt432.kitchenkarrot.recipes.object;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class EffectStack {
    public static final Codec<EffectStack> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.STRING.fieldOf("id").forGetter(effectStack -> effectStack.id),
            Codec.INT.fieldOf("lvl").forGetter(effectStack -> effectStack.lvl),
            Codec.INT.fieldOf("duration").forGetter(effectStack -> effectStack.duration)
    ).apply(builder, EffectStack::new));

    public EffectStack(String id, int lvl, int duration) {
        this.id = id;
        this.lvl = lvl;
        this.duration = duration;
    }

    String id;
    int lvl;
    int duration;

    private Supplier<MobEffectInstance> cache;

    public MobEffectInstance get() {
        if (cache == null) {
            var effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(id));

            if (effect != null) {
                cache = () -> new MobEffectInstance(effect, duration, lvl - 1);
            }
        }

        return cache.get();
    }
}
