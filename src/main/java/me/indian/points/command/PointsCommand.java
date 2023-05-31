package me.indian.points.command;

import me.indian.points.PvpPoints;
import me.indian.points.api.PointsUtil;
import me.indian.points.util.ColorUtil;
import me.indian.points.util.ConfigUtil;
import me.indian.points.util.PermissionUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PointsCommand implements CommandExecutor {

    private final PvpPoints plugin;

    public PointsCommand(PvpPoints plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final Configuration config = plugin.getConfig();
        final ConfigUtil players = plugin.getPlayersConfig();

        if (args.length == 0) {
            sender.sendMessage("Bad");
            return false;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission(PermissionUtil.ADMIN)) {
                plugin.reloadConfig();
                sender.sendMessage(ColorUtil.replaceColorCode("&aReloaded"));
            }
        }

        if (args[0].equalsIgnoreCase("points")) {
            if (args.length == 1) {
                if (sender instanceof Player) {
                    final Player player = (Player) sender;
                    final int points = PointsUtil.getPoints(player);
                    player.sendMessage(ColorUtil.replaceColorCode(config.getString("pkt-check").replace("<pkt>", String.valueOf(points))));
                } else {
                    sender.sendMessage(ColorUtil.replaceColorCode("&cYou must bee player!!"));
                }
            }
            if (args.length == 2) {
                final Player cel = Bukkit.getPlayer(args[2]);
                if (cel != null) {
                    final int points = PointsUtil.getPoints(cel);
                    cel.sendMessage(ColorUtil.replaceColorCode(config.getString("pkt-check").replace("<pkt>", String.valueOf(points))));
                } else {
                    sender.sendMessage(ColorUtil.replaceColorCode(config.getString("invaild-player").replace("<player>", args[2])));
                }
            }
        }

        if (args[0].equalsIgnoreCase("top")) {
            for(String s : PointsUtil.getTop()){
                sender.sendMessage(ColorUtil.replaceColorCode(s));
            }
        } else if (args[0].equalsIgnoreCase("top") && args[1].equalsIgnoreCase("gui")) {
            if(sender instanceof Player){
                final Player player = (Player) sender;
                Inventory inv = Bukkit.createInventory(null, 27, config.getString("gui-name"));
                ItemStack miecz = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta meta = miecz.getItemMeta();
                meta.setDisplayName(config.getString("sowrd-name"));
                meta.setLore(PointsUtil.getTop());
                miecz.setItemMeta(meta);

                inv.setItem(13, miecz);
                player.openInventory(inv);

            } else {
                sender.sendMessage(ColorUtil.replaceColorCode("&cYou must bee player!!"));
            }
        }
        return false;
    }
}
