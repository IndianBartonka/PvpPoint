package me.indian.points.others;

import java.util.ArrayList;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.indian.points.PvpPoints;
import me.indian.points.api.PointsUtil;
import org.bukkit.entity.Player;

public class PvpPointsPlaceholders extends PlaceholderExpansion {

    private final PvpPoints plugin;

    public PvpPointsPlaceholders(PvpPoints plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "pvppoints";
    }

    @Override
    public String getAuthor() {
        return String.valueOf(plugin.getDescription().getAuthors()).replace("[", "").replace("]", "");
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
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equals("points")) {
            int points = PointsUtil.getPoints(player);
            return String.valueOf(points);
        }
        if (identifier.equals("test")) {
            ArrayList<String> top = PointsUtil.getTop();
            String tes = "";
            for (String ar : top) {
                tes = ar;
            }

            return tes;
        }
        return null;
    }
}
