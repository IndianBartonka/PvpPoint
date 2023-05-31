package me.indian.points.listener;

import me.indian.points.PvpPoints;
import me.indian.points.api.PointsUtil;
import me.indian.points.others.UpdateChecker;
import me.indian.points.util.ConfigUtil;
import me.indian.points.util.MessageApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final PvpPoints plugin;

    public PlayerJoinListener(PvpPoints plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void playerJoinFirstTime(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            PointsUtil.addPoints(player , plugin.getConfig().getInt("start-points"));
        }
    }

    @EventHandler
    private void opJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            new UpdateChecker(this.plugin).getVersion(version -> {
                if (!(this.plugin.getDescription().getVersion().equals(version))) {
                    MessageApi.chat(player, this.plugin.prefix + "§bThere is a new update available.");
                    MessageApi.hoverMessageCopy(player,
                            this.plugin.prefix + "§bYour version " + this.plugin.getDescription().getVersion() + " new version " + version,
                            "https://www.spigotmc.org/resources/pvppoints.105778/",
                            "Copy download link");
                }
            });
        }
    }
}