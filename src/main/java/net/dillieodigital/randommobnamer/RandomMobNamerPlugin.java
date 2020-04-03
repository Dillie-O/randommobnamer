package net.dillieodigital.randommobnamer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Hello world!
 *
 */
public class RandomMobNamerPlugin extends JavaPlugin
{
    @Override
    public void onEnable() {
        getLogger().info("Bienvenue, Random Mob Namer!");
    }
    @Override
    public void onDisable() {
        getLogger().info("Peace out, Random Mob Namer!");
    }
}
