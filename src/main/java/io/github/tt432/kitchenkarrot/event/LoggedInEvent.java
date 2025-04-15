package io.github.tt432.kitchenkarrot.event;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.config.ModClientConfigs;
import io.github.tt432.kitchenkarrot.config.ModCommonConfigs;
import io.github.tt432.kitchenkarrot.item.ModBlockItems;
import io.github.tt432.kitchenkarrot.registries.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = Kitchenkarrot.MOD_ID)
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
