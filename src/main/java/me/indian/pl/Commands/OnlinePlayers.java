package me.indian.pl.Commands;


import me.clip.placeholderapi.PlaceholderAPI;
import me.indian.pl.Main;
import me.indian.pl.Otchers.Refresh;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class OnlinePlayers implements CommandExecutor, Listener {

    private final Main plugin;
    public boolean cmdss = false;
    public boolean tp = false;


    public OnlinePlayers(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;


            Inventory inv = Bukkit.createInventory(null, 54, "Online");


            int refreshslot = plugin.getConfig().getInt("refresh-slot");
            int cmdsslot = plugin.getConfig().getInt("cmdslot");
            int tpswitchslot = plugin.getConfig().getInt("tpswitchslot");
            int barrierslot = plugin.getConfig().getInt("barrierslot");

            int players = plugin.getConfig().getInt("start-counting-players-from-the-slot");
            int slots = plugin.getConfig().getInt("start-counting-players-from-the-slot") + 1;
            for (Player all : Bukkit.getOnlinePlayers()) {

                for (Integer it : plugin.getConfig().getIntegerList("GRAY_STAINED_GLASS_PANE")) {
                    ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemMeta s = empty.getItemMeta();
                    s.setDisplayName(ChatColor.BLACK + " ");
                    empty.setItemMeta(s);

                    inv.setItem(it, empty);
                }
                ItemStack gracz = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta gr = (SkullMeta) gracz.getItemMeta();

                gr.setOwner(all.getPlayer().getName());

                gr.setDisplayName(PlaceholderAPI.setPlaceholders(all, plugin.getConfig().getString("head-name-prefix") + all.getDisplayName() + plugin.getConfig().getString("head-name-sufix")));
                ArrayList<String> lore = new ArrayList<>();
                for (String msg : plugin.getConfig().getStringList("description")) {
                    lore.add(PlaceholderAPI.setPlaceholders(all, msg.replace("<tp>", plugin.getConfig().getString("tp"))));
                }
                gr.setLore(lore);
                gracz.setItemMeta(gr);

                ItemStack wolny = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                ItemMeta wl = wolny.getItemMeta();
                wl.setDisplayName(plugin.getConfig().getString("free-slots"));
                ArrayList<String> sloty = new ArrayList<>();
                sloty.add("§a" + Bukkit.getOnlinePlayers().size() + "§6/§a" + Bukkit.getMaxPlayers() + "");
                if (plugin.getConfig().getBoolean("Playerlist")) {
                    sloty.add(PlaceholderAPI.setPlaceholders(all, "%playerlist_online,normal,yes,list%  "));
                }
                wl.setLore(sloty);
                wolny.setItemMeta(wl);

                ItemStack zajen = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                ItemMeta zn = wolny.getItemMeta();
                zn.setDisplayName(plugin.getConfig().getString("non-slots"));
                zn.setLore(sloty);
                zajen.setItemMeta(zn);
                List<Integer> sl = plugin.getConfig().getIntegerList("GRAY_STAINED_GLASS_PANE");

                if (plugin.getConfig().getBoolean("free&non-slots")) {
                    if (Bukkit.getMaxPlayers() == Bukkit.getOnlinePlayers().size()) {
                        inv.setItem(slots, zajen);
                    } else {
                        inv.setItem(slots, wolny);
                    }
                }

                inv.setItem(players, gracz);


                inv.setItem(players, gracz);
                players++;
                if (plugin.getConfig().getBoolean("free&non-slots")) {
                    slots++;
                    if (sl.contains(slots)) {
                        slots++;
                        slots++;

                    }
                }
                if (sl.contains(players)) {
                    players++;
                    players++;
                }
            }


            ItemStack ods = new ItemStack(Material.IRON_TRAPDOOR);
            ItemMeta odss = ods.getItemMeta();
            odss.setDisplayName(plugin.getConfig().getString("refresh"));
            ods.setItemMeta(odss);

            ItemStack guzik = new ItemStack(Material.STONE_BUTTON);
            ItemMeta guz = guzik.getItemMeta();
            guz.setDisplayName(plugin.getConfig().getString("TPswitch"));
            ArrayList<String> gl = new ArrayList<>();
            gl.add(plugin.getConfig().getString("tp"));
            guz.setLore(gl);
            guzik.setItemMeta(guz);


            ItemStack cmds = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta cd = cmds.getItemMeta();
            cd.setDisplayName(plugin.getConfig().getString("cmd-switch"));
            ArrayList<String> cmdlo = new ArrayList<>();
            cmdlo.add(plugin.getConfig().getString("cmdl"));
            cd.setLore(cmdlo);
            cmds.setItemMeta(cd);


            ItemStack test = new ItemStack(Material.IRON_TRAPDOOR);
            ItemMeta testt = ods.getItemMeta();
            testt.setDisplayName("test");
            test.setItemMeta(testt);

            ItemStack nextpage = new ItemStack(Material.BOOK);
            ItemMeta np = ods.getItemMeta();
            np.setDisplayName(plugin.getConfig().getString("nextpagename"));
            nextpage.setItemMeta(np);

            ItemStack barrier = new ItemStack(Material.BARRIER);
            ItemMeta bar = barrier.getItemMeta();
            bar.setDisplayName(plugin.getConfig().getString("barriername"));
            barrier.setItemMeta(bar);


            inv.setItem(cmdsslot, cmds);
            inv.setItem(refreshslot, ods);
            inv.setItem(tpswitchslot, guzik);
            if (plugin.getConfig().getBoolean("barriera")) {
                inv.setItem(barrierslot, barrier);
            }


            p.openInventory(inv);


        } else {
            sender.sendMessage(plugin.prefix + plugin.getConfig().getString("player-not-be"));
        }
        return false;
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Online")) {
            e.setCancelled(true);
        }


        if (e.getView().getTitle().equals("Online")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("cmd-switch"))) {
                if (p.hasPermission(plugin.getConfig().getString("OnPl"))) {
                    if (cmdss) {
                        cmdss = false;
                        plugin.getConfig().set("cmdl", plugin.getConfig().getString("cmd-tp"));
                        Refresh.refresh(plugin, p);

                    } else {
                        cmdss = true;
                        plugin.getConfig().set("cmdl", plugin.getConfig().getString("tp-cmd"));
                        Refresh.refresh(plugin, p);
                    }

                } else {
                    p.sendMessage(plugin.prefix + plugin.getConfig().getString("admin-perms"));
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                }
            }
        }
        if (e.getView().getTitle().equals("Online")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("refresh"))) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 10);
                Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.dispatchCommand(p, "online"), 20);
            }
            if (e.getView().getTitle().equals("Online")) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("TPswitch"))) {
                    if (p.hasPermission(plugin.getConfig().getString("OnPl"))) {
                        if (tp) {
                            tp = false;
                            plugin.getConfig().set("tp", plugin.getConfig().getString("head-to-player"));

                            Refresh.refresh(plugin, p);
                        } else {
                            tp = true;
                            plugin.getConfig().set("tp", plugin.getConfig().getString("player-to-head"));
                            Refresh.refresh(plugin, p);
                        }
                    } else {
                        p.sendMessage(plugin.prefix + plugin.getConfig().getString("admin-perms"));
                        Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                    }
                }
            }


            for (Player all : Bukkit.getOnlinePlayers()) {
                if (e.getView().getTitle().equals("Online")) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(PlaceholderAPI.setPlaceholders(all, plugin.getConfig().getString("head-name-prefix") + all.getDisplayName() + plugin.getConfig().getString("head-name-sufix")))) {
                        if (p.hasPermission(plugin.getConfig().getString("OnPl"))) {
                            if (e.isLeftClick()) {
                                if (cmdss == false) {
                                    if (tp) {
                                        if (all != p) {
                                            if (all != null) {
                                                p.teleport(all.getLocation());
                                            } else {
                                                all.sendMessage(plugin.prefix + plugin.getConfig().getString("player-null"));
                                                Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                                            }
                                        } else {
                                            p.sendMessage(plugin.prefix + plugin.getConfig().getString("shelf-command"));
                                            Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                                        }
                                    } else {
                                        if (all != p) {
                                            if (p != null) {
                                                all.teleport(p.getLocation());
                                            } else {
                                                all.sendMessage(plugin.prefix + plugin.getConfig().getString("player-null"));
                                                Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                                            }
                                        } else {
                                            p.sendMessage(plugin.prefix + plugin.getConfig().getString("shelf-command"));
                                            Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                                        }
                                    }
                                } else {
                                    if (p.hasPermission(plugin.getConfig().getString("OnPl"))) {

                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plugin.getConfig().getString("command").replace("<player>", all.getDisplayName()));
                                        Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 10);

                                    } else {
                                        p.sendMessage(plugin.prefix + plugin.getConfig().getString("admin-perms"));
                                        Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                                    }
                                }
                            } else if (e.isRightClick()) {
                                Bukkit.dispatchCommand(p, "give <sender> minecraft:player_head{SkullOwner:<player>}".replace("<player>", all.getDisplayName()).replace("<sender>", p.getDisplayName()));
                                Refresh.refresh(plugin, p);
                            }
                        } else {
                            p.sendMessage(plugin.prefix + plugin.getConfig().getString("admin-perms"));
                            Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                        }
                    }
                }
            }
            if (e.getView().getTitle().equals("Online")) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("barriername"))) {
                    Bukkit.dispatchCommand(p, plugin.getConfig().getString("barriercommand"));

                }
            }
        }
    }
}