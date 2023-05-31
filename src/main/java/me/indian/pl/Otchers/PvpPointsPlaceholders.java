package me.indian.pl.Otchers;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.indian.pl.Main;
import me.indian.pl.Utils.ConfigUtil;
import me.indian.pl.Utils.TopUtil;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PvpPointsPlaceholders extends PlaceholderExpansion {

    private final Main plugin;

    public PvpPointsPlaceholders(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "pvppoints";
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
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        if (identifier.equals("points")) {
            int pl1 = players.getConfig().getInt(p.getDisplayName() + ".pkt");

            return pl1 + "";
        }
        if(identifier.equals("test")){
            ArrayList<String> top = TopUtil.getTop(plugin);
                String tes = "";
            for (String ar : top) {
                tes = ar;
            }

            return tes ;
        }
        return null;
    }
}
