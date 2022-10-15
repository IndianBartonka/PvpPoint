package me.indian.pl.Otchers;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.indian.pl.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class OnlinePlayersPlaceholders extends PlaceholderExpansion implements Listener {

    private final Main plugin;

    public OnlinePlayersPlaceholders(Main plugin){
        this.plugin = plugin;
    }



    @Override
    public String getIdentifier() {
        return "OnlinePlayers";
    }

    @Override
    public String getAuthor() {
        return "IndianPL";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if (identifier.equals("version")) {
            return plugin.getDescription().getVersion();
        }
        if (identifier.equals("expansion_version")) {
            return this.getVersion();
        }

        if (identifier.equals("online_players_count")){
            String onlinep = "" + Bukkit.getOnlinePlayers().size();
            return onlinep;
        }
        if (identifier.equals("max_players_count")){
            String maxp = "" + Bukkit.getMaxPlayers();
            return maxp;
        }

        if (identifier.equals("admin_perm")){
            String adminperm = plugin.getConfig().getString("OnPl");
            return adminperm;
        }

        if (identifier.equals("config_placeholder")){
            String confp = plugin.getConfig().getString("ConfigPlaceholder");
            return confp;
        }
        return null;
    }
}
