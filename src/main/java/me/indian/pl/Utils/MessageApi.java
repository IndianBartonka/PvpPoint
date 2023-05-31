package me.indian.pl.Utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageApi {

    public static void ActionBar(Player p , String tekst){
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(tekst.replace("<player>" , p.getName())));
    }
    public static void Chat(Player p , String tekst){
        p.spigot().sendMessage(ChatMessageType.CHAT , TextComponent.fromLegacyText(tekst.replace("<player>" , p.getName())));
    }
    public static void Console(Player p ,String komenda){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender() , komenda.replace("<player>" , p.getName()));
    }
    public static void Console(String komenda){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender() , komenda);
    }
    public static void PlayerCommand(Player p, String komenda) {
        Bukkit.dispatchCommand(p , komenda.replace("<player>" , p.getName()));
    }
    public static void HoverMessage(Player p , String tekst , String hovertekst) {
        TextComponent message = new TextComponent(tekst);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertekst).create()));
        p.spigot().sendMessage(message);
    }
    public static void HoverMessage(Player p , String tekst ,String komenda , String hovertekst , Boolean run) {
        TextComponent message = new TextComponent(tekst);
        if (run == true) {
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, komenda));
        } else {
            message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, komenda));
        }
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertekst).create()));
        p.spigot().sendMessage(message);
    }
    public static void HoverMessageCopy(Player p , String tekst ,String tekstc , String hovertekst ) {
        TextComponent message = new TextComponent(tekst);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, tekstc));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertekst).create()));
        p.spigot().sendMessage(message);
    }
}
