package io.github.tt432.kitchenkarrot.event;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.config.ModClientConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Kitchenkarrot.MOD_ID, value = Dist.CLIENT)
public class LoggedInEvent {
    @SubscribeEvent
    public static void sendMessages(PlayerEvent.PlayerLoggedInEvent event){
        if (ModClientConfigs.SHOW_WELCOME_MESSAGE.get()) {
            Player player = event.getEntity();
            String link = Minecraft.getInstance().getLanguageManager().getSelected().equals("zh_cn") ? "http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=e1yPG4aXegt7-1q2wkGist98cl10zcUv&authKey=ucD%2F0WVyzpQosswaKAl%2FPwYz5sY%2FTNfvmcOtfw%2BEUzul1ucdMJUAIlz6MB6MDqbV&noverify=0&group_code=694711755" : "https://discord.gg/AM9mKBFg";
                    player.sendSystemMessage(Component.translatable("info.kitchenkarrot.warning").withStyle(ChatFormatting.RED));
            player.sendSystemMessage(Component.translatable("info.kitchenkarrot.report_link").withStyle(style -> style.withColor(ChatFormatting.LIGHT_PURPLE).withUnderlined(true).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link))));
        }
    }
}
