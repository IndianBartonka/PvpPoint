package me.indian.pl.Commands;

import me.indian.pl.Main;
import me.indian.pl.Utils.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PointsCommand implements CommandExecutor {

    private final Main plugin;
    public PointsCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            ConfigUtil players = new ConfigUtil(plugin, "players.yml");
            if(args.length == 0){
                int pl1 = players.getConfig().getInt(p.getDisplayName() + ".pkt");
                String pkt = pl1 + "";
                p.sendMessage(plugin.getConfig().getString("pkt-check").replace("<pkt>" , pkt));



            } else if (args.length == 1){
               Player cel = Bukkit.getPlayer(args[0]);
                int pl1 = players.getConfig().getInt(cel.getDisplayName() + ".pkt");
                String pkt = pl1 + "";
                p.sendMessage(plugin.getConfig().getString("pkt-check-player").replace("<pkt>" , pkt).replace("<player>" , cel.getDisplayName()));

            }

        } else {
            sender.sendMessage("Consol not a player");
        }
        return false;
    }
}
