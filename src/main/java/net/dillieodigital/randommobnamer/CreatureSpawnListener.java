package net.dillieodigital.randommobnamer;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Hello world!
 *
 */
public class CreatureSpawnListener implements Listener
{
    final RandomMobNamerPlugin plugin;

    public CreatureSpawnListener(RandomMobNamerPlugin plugin) {
        this.plugin = plugin;
	}

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
     public void onCreatureSpawnEvent(CreatureSpawnEvent event)
     {
        final LivingEntity creature = event.getEntity();
        String creatureType = creature.getType().toString();
        String creatureChancePath = "MobNames." + creatureType + ".Chance";
        String creatureNamePath = "MobNames." + creatureType + ".Names";

        int odds = plugin.getConfiguration().getInt(creatureChancePath);

        Random rand = new Random(System.currentTimeMillis());
        int check =  rand.nextInt(100) + 1;

        plugin.getLogger().info("Mob: " + creatureType + " / Chance: " + odds);


        // Our odds value represents a percentage (1 to 100) that the mob
        // gets a custom name. The random generator generates a number from
        // 1 to 100. If the generator gives us a number less than or equal
        // to our odds range, then we "hit" and will git the mob a custom
        // name. We also verify that odds are greater than 0 to begin with
        // for those mobs that don't have a config or are forcibly set to 0.
        if ((odds > 0) && (odds <= check))
        {
            plugin.getLogger().info("Mob hit: " + creatureType);

            List<String> nameList = plugin.getConfiguration().getStringList(creatureNamePath);
            plugin.getLogger().info("Name List Count: " + nameList.size());

            // Check for available names before assining. Helpful if the mob
            // in question doesn't have names available. When generating the
            // random number, we must use +1 in order to include the max value
            // as a candidate.
            if(nameList.size() > 0)
            {
                // Random number generator needs +1 in order to give us a
                // proper range based on size of name list. We give it
                // another +1 afterwards to account for the 0 based index
                // pull for the actual name. Otherwise we go out of bounds
                // when grabbing the name. The joys of two different range
                // systems that need to place nice together. 8^D
                int nameIndex =  rand.nextInt(nameList.size() + 1) + 1;
                plugin.getLogger().info("Index Check: " + nameIndex);
                creature.setCustomName(nameList.get(nameIndex - 1));
            }
        }
     }
}