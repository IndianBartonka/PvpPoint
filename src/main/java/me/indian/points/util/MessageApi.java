package me.indian.points.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageApi {

    public static void actionBar(Player p, String text) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text.replace("<player>", p.getName())));
    }

    public static void chat(Player p, String text) {
        p.spigot().sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(text.replace("<player>", p.getName())));
    }

    public static void console(Player p, String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("<player>", p.getName()));
    }

    public static void console(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public static void playerCommand(Player p, String command) {
        Bukkit.dispatchCommand(p, command.replace("<player>", p.getName()));
    }

    public static void hoverMessage(Player p, String text, String hovertext) {
        TextComponent message = new TextComponent(text);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
        p.spigot().sendMessage(message);
    }

    public static void hoverMessage(Player p, String text, String command, String hovertext, Boolean run) {
        TextComponent message = new TextComponent(text);
        if (run) {
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        } else {
            message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        }
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
        p.spigot().sendMessage(message);
    }

    public static void hoverMessageCopy(Player p, String text1, String text2, String hovertext) {
        TextComponent message = new TextComponent(text1);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text2));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
        p.spigot().sendMessage(message);
    }
}