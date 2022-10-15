package me.indian.pl.Commands;

import me.indian.pl.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CloseInvCommand implements CommandExecutor {

    private final Main plugin;

    public CloseInvCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.closeInventory();

        } else {
            sender.sendMessage(plugin.getConfig().getString("player-not-be"));
        }
        return false;
    }
}
