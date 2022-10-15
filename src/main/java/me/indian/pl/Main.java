package me.indian.pl;


import me.indian.pl.Commands.CloseInvCommand;
import me.indian.pl.Commands.OnlinePlayers;
import me.indian.pl.Commands.Reload;
import me.indian.pl.Commands.TestCommand;
import me.indian.pl.Otchers.MessageApi;
import me.indian.pl.Otchers.Metrics;
import me.indian.pl.Otchers.OnlinePlayersPlaceholders;
import me.indian.pl.Otchers.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    public static String prefix = "§b[§aOnline§ePlayers§b] ";
    @Override
    public void onEnable() {


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {

            new OnlinePlayersPlaceholders(this).register();
        } else {
            Bukkit.getConsoleSender().sendMessage("You not using PlaceholderAPI , this plugin is required!");
                 Bukkit.getPluginManager().disablePlugin(this);
        }




            new UpdateChecker(this, 105534).getVersion(version -> {
                if (this.getDescription().getVersion().equals(version)) {
                    getLogger().info(prefix + "There is not a new update available.");

                } else {
                    getLogger().info (prefix + "There is a new update available.");
                    getLogger().info(prefix + "Your version " + this.getDescription().getVersion() + " new version " + version);

                }
            });


        int pluginId = 16535;
        Metrics metrics = new Metrics(this, pluginId);


        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));


        String version = getDescription().getVersion();
        Bukkit.getConsoleSender().sendMessage("------------------------------");
        Bukkit.getConsoleSender().sendMessage("\t OnlinePlayers " + version);
        Bukkit.getConsoleSender().sendMessage("------------------------------");

        saveDefaultConfig();

        PluginManager pm = getServer().getPluginManager();
        getCommand("online").setExecutor(new OnlinePlayers(this));
        getCommand("opr").setExecutor(new Reload(this));
        getCommand("t").setExecutor(new TestCommand(this));
        getCommand("closeinv").setExecutor(new CloseInvCommand(this));
        pm.registerEvents(this, this);
        pm.registerEvents(new OnlinePlayers(this),this);
        pm.registerEvents(new TestCommand(this),this);

    }


    @EventHandler
    public void OpJoin(PlayerJoinEvent e){
        Player p = (Player) e.getPlayer();


        if (p.isOp()){
            new UpdateChecker(this, 105534).getVersion(version -> {
                if (!(this.getDescription().getVersion().equals(version))) {
                    MessageApi.Chat(p, prefix + "§bThere is a new update available.");
                    MessageApi.HoverMessageCopy(p,
                            prefix + "§bYour version " + this.getDescription().getVersion() + " new version " + version,
                            "https://www.spigotmc.org/resources/onlineplayers.105534/",
                            "Copy download link");
                }
            });
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
