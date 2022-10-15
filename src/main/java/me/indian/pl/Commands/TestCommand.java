package me.indian.pl.Commands;

import me.indian.pl.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor , Listener {

    private final Main plugin;
    public TestCommand(Main plugin) {
    this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;





        return false;
    }


    @EventHandler
    public void I(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals("test")){
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getItemStack("Testy.t.meta.display-name"))){
                e.setCancelled(true);
            }
        }


    }
}







