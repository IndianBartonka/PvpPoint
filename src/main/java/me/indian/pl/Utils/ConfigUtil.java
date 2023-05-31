package me.indian.pl.Utils;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigUtil {
    private File file;
    private FileConfiguration config;

    public ConfigUtil(Plugin plugin, String patch) {
        this(plugin.getDataFolder().getAbsolutePath() + "/" + patch);

    }

    public ConfigUtil(String patch) {
        this.file = new File(patch);
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public boolean save() {
        try {
            this.config.save(this.file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }
}
