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

        plugin.reloadConfig();
        sender.sendMessage("Reloaded");

        return false;
    }
}
