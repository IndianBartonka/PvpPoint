package me.indian.pl.Utils;

import me.indian.pl.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TopUtil {


    public static ArrayList<String> getTop(Main plugin){
        ConfigUtil players = new ConfigUtil(plugin, "players.yml");
        Configuration conf = plugin.getConfig() ;
        ArrayList<String> tops = new ArrayList<String>();
        Map<String, Integer> sort = new HashMap<String, Integer>();


//        tops.add(" ");
        tops.add(conf.getString("header"));


        for(OfflinePlayer of : Bukkit.getOfflinePlayers()){
            UUID uuid = of.getUniqueId();
            sort.put(of.getName() , players.getConfig().getInt(of.getName() + ".pkt" ));
        }
        AtomicInteger counter = new AtomicInteger();

        sort.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(conf.getInt("lb-size"))
                .forEach(e -> {
                    String num = counter.incrementAndGet() + ". ";
                    tops.add(conf.getString("Num-color") + num + conf.getString("Player-color") + e.getKey() + conf.getString("Points-color") + " " + e.getValue() );



                });
        tops.add(conf.getString("footer"));
        tops.add(" ");

        return tops;
    }
}
