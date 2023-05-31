package me.indian.pl.Listeners;

import me.indian.pl.Main;
import me.indian.pl.Utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class KillEvent implements Listener {

    private final Main plugin;

    public KillEvent(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void KillEvent(PlayerDeathEvent e) {

        if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player) {
            Player p = (Player) e.getEntity();
            Player k = (Player) e.getEntity().getKiller();

            ConfigUtil players = new ConfigUtil(plugin, "players.yml");

            int minus = plugin.getConfig().getInt("minus-points");
            int plus = plugin.getConfig().getInt("plus-points");


            int pl1 = players.getConfig().getInt(p.getDisplayName() + ".pkt");
            int pl2 = players.getConfig().getInt(k.getDisplayName() + ".pkt");

            if (pl1 > minus) {
                players.getConfig().set(p.getDisplayName() + ".pkt", pl1 -= minus);
                players.getConfig().set(k.getDisplayName() + ".pkt", pl2 += plus);
            } else {
                k.sendMessage(plugin.getConfig().getString("non-points").replace("<kill>", p.getDisplayName()));
            }
            players.save();


        }
    }
}
