package net.dillieodigital.randommobnamer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Hello world!
 *
 */
public class RandomMobNamerPlugin extends JavaPlugin
{
    CreatureSpawnListener spawnListener = new CreatureSpawnListener(this);

    private static RandomMobNamerPlugin instance;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("Bienvenue, Random Mob Namer!");
        getServer().getPluginManager().registerEvents(spawnListener, this);

        // if the config file doesn't exist, create it
        saveDefaultConfig();

        // use bukkit's config loader
        config = getConfig();

        String healthCheck = config.getString("HealthCheck");
        getLogger().info("Health Check: " + healthCheck);

    }
    @Override
    public void onDisable() {
        getLogger().info("Peace out, Random Mob Namer!");
    }

    public static RandomMobNamerPlugin getInstance() {
		return instance;
    }

    public FileConfiguration getConfiguration() {
		return config;
	}
}
