package me.indian.points.listener;

import me.indian.points.PvpPoints;
import me.indian.points.api.PointsUtil;
import me.indian.points.util.ConfigUtil;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDeathListener implements Listener {

    private final PvpPoints plugin;

    public PlayerDeathListener(PvpPoints plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void KillEvent(org.bukkit.event.entity.PlayerDeathEvent e) {

        if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player) {
            final  Player p =  e.getEntity();
            final   Player k = e.getEntity().getKiller();
            final Configuration config = plugin.getConfig();
            final ConfigUtil players = plugin.getPlayersConfig();

            int grab = config.getInt("grab-points");


            int pl1 = PointsUtil.getPoints(p);
            int pl2 = PointsUtil.getPoints(k);

            if (pl1 >= 0) {
                PointsUtil.removePoints(p , grab);
                PointsUtil.addPoints(k, grab);
            } else {
                k.sendMessage(config.getString("non-points").replace("<kill>", p.getDisplayName()));
            }
            players.save();

        }
    }
}
