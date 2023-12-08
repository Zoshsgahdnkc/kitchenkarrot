package io.github.tt432.kitchenkarrot.util;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.locating.IModFile;

import java.nio.file.Path;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModResourcePack {
    @SubscribeEvent
    public static void registerResourcePack(AddPackFindersEvent event) {
        IModFile file = ModList.get().getModFileById(Kitchenkarrot.MOD_ID).getFile();
        Path path = file.findResource("resourcepack/legacy_art");
        event.addRepositorySource(consumer -> {
                    Pack pack = Pack.readMetaAndCreate(
                            new ResourceLocation(Kitchenkarrot.MOD_ID, "legacy_art").toString(),
                            Component.translatable("resource.kitchenkarrot.legacy_art"),
                            false,
                            new PathPackResources.PathResourcesSupplier(path, true),
//                        name -> new PathPackResources(name, true, path),
                            PackType.CLIENT_RESOURCES,
                            Pack.Position.TOP,
                            PackSource.BUILT_IN
                    );
                    if (pack != null) consumer.accept(pack);
                }
        );
    }
}
