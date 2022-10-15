package me.indian.pl.Otchers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.indian.pl.Main;

public class Refresh {


    public static void refresh(Main plugin , Player p) {

        int refresh = plugin.getConfig().getInt("refresh-time");

        Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), refresh * 20);
        Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.dispatchCommand(p, "online"), refresh * 20 + 1);

    }
    public static void closeInv(Main plugin , Player p){
        Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
    }
}
