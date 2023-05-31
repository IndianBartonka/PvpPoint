package me.indian.points;

import me.indian.points.api.Api;
import me.indian.points.command.PointsCommand;
import me.indian.points.listener.InventoryListener;
import me.indian.points.listener.PlayerDeathListener;
import me.indian.points.listener.PlayerJoinListener;
import me.indian.points.others.Metrics;
import me.indian.points.others.PvpPointsPlaceholders;
import me.indian.points.others.UpdateChecker;
import me.indian.points.util.ConfigUtil;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PvpPoints extends JavaPlugin {

    public String prefix = "§b[§aPlayer§ePoints§b] ";
    private static PvpPoints instance;
    private ConfigUtil players;
    private Api api;

    public static PvpPoints getInstance() {
        return instance;
    }

    public ConfigUtil getPlayersConfig(){
        return this.players;
    }

    public Api getApi(){
        return this.api;
    }

    @Override
    public void onLoad() {
        instance = this;
        this.saveDefaultConfig();
        this.players = new ConfigUtil(this, "players.yml");
        this.api = new Api();
        players.save();
    }

    @Override
    public void onEnable() {
        final PluginManager pm = getServer().getPluginManager();

        new Metrics(this, 16670);

        if (pm.getPlugin("PlaceholderAPI") != null) {
            new PvpPointsPlaceholders(this).register();
        } else {
            getLogger().info("You don't have PlaceholderAPI, this plugin is not required");
        }

        getLogger().info("-----------------------------");
        getLogger().info("\t " + getDescription().getName() + " " + this.getDescription().getVersion());
        getLogger().info("-----------------------------");

        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerDeathListener(this), this);
        pm.registerEvents(new InventoryListener(this), this);



        getCommand("points").setExecutor(new PointsCommand(this));


        new UpdateChecker(this).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info(prefix + "There is not a new update available.");

            } else {
                getLogger().info(prefix + " There is a new update available.");
                getLogger().info(prefix + " Your version " + this.getDescription().getVersion() + " new version " + version);
            }
        });
    }
}