package io.github.tt432.kitchenkarrot.recipes.object;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class EffectStack {
    public static final Codec<EffectStack> CODEC =
            RecordCodecBuilder.create(
                    builder ->
                            builder.group(
                                            Codec.STRING
                                                    .fieldOf("id")
                                                    .forGetter(EffectStack::getId),
                                            Codec.INT.fieldOf("lvl").forGetter(EffectStack::getLvl),
                                            Codec.INT
                                                    .fieldOf("duration")
                                                    .forGetter(EffectStack::getDuration))
                                    .apply(builder, EffectStack::new));

    public EffectStack(String id, int lvl, int duration) {
        this.id = id;
        this.lvl = lvl;
        this.duration = duration;
    }

    private String id;
    private int lvl;
    private int duration;

    private Supplier<MobEffectInstance> cache;

    public String getId() {
        return id;
    }

    public int getLvl() {
        return lvl;
    }

    public int getDuration() {
        return duration;
    }

    public MobEffectInstance get() {
        if (cache == null) {
            Optional<Holder.Reference<MobEffect>> holder =
                    BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse(id));
            holder.ifPresent(
                    mobEffectReference ->
                            cache =
                                    () ->
                                            new MobEffectInstance(
                                                    mobEffectReference, duration, lvl - 1));
        }

        return cache.get();
    }
}
