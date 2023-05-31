package me.indian.points.listener;

import me.indian.points.PvpPoints;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    private final PvpPoints plugin;

    public InventoryListener(PvpPoints plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void InventoryClick(InventoryClickEvent event) {
        final Configuration config = plugin.getConfig();
        if (event.getView().getTitle().equals(config.getString("gui-name"))) {
            event.setCancelled(true);
        }
    }
}
