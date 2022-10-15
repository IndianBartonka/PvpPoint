package me.indian.pl.Commands;

import me.indian.pl.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

    private final Main plugin;
    public Reload(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender.hasPermission(plugin.getConfig().getString("OnPl"))) {
            plugin.reloadConfig();
            sender.sendMessage("Reloaded config");
        } else {
            sender.sendMessage(plugin.getConfig().getString("admin-perms"));
        }
        return false;
    }
}
