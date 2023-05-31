package me.indian.points.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import me.indian.points.PvpPoints;
import me.indian.points.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class PointsUtil {

    private static final PvpPoints plugin = PvpPoints.getInstance();
    private static final Configuration config = plugin.getConfig();
    public static final ConfigUtil pointsConfig = plugin.getPlayersConfig();

    public static void addPoints(final Player player , final int points){
    final UUID uuid = player.getUniqueId();
    final String configSegment = uuid + ".pkt";
    int currentPkt = pointsConfig.getConfig().getInt(configSegment);
        currentPkt += points;
        pointsConfig.getConfig().set(configSegment , currentPkt);
        pointsConfig.save();
    }

    public static void removePoints(final Player player , final int points){
        final UUID uuid = player.getUniqueId();
        final String configSegment = uuid + ".pkt";
        int currentPkt = pointsConfig.getConfig().getInt(configSegment);
        currentPkt -= points;
        pointsConfig.getConfig().set(configSegment , currentPkt);
        pointsConfig.save();
    }


    public static int getPoints(final Player player){
        return pointsConfig.getConfig().getInt(player.getUniqueId() + ".pkt");
    }


    public static ArrayList<String> getTop() {
        ArrayList<String> tops = new ArrayList<String>();
        Map<String, Integer> sort = new HashMap<String, Integer>();

//        tops.add(" ");
        tops.add(config.getString("header"));

        for (OfflinePlayer of : Bukkit.getOfflinePlayers()) {
            UUID uuid = of.getUniqueId();
            sort.put(of.getName(), pointsConfig.getConfig().getInt(uuid + ".pkt"));
        }
        AtomicInteger counter = new AtomicInteger();

        sort.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(config.getInt("lb-size"))
                .forEach(e -> {
                    String num = counter.incrementAndGet() + ". ";
                    tops.add(config.getString("Num-color") + num +
                            config.getString("Player-color") + e.getKey() +
                            config.getString("Points-color") + " " + e.getValue());
                });
        tops.add(config.getString("footer"));
        tops.add(" ");
        return tops;
    }
}
