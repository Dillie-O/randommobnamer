package net.dillieodigital.randommobnamer;

import java.util.List;
import java.util.Random;

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
    final Random rand;
    final RandomMobNamerPlugin plugin;

    public CreatureSpawnListener(RandomMobNamerPlugin plugin) {
        this.plugin = plugin;
        this.rand = new Random();
	}

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
     public void onCreatureSpawnEvent(CreatureSpawnEvent event)
     {
        final LivingEntity creature = event.getEntity();

        // Don't rename named mobs
		if(creature.getCustomName() != null) return;

		// Calculate the chance to name this mob
        String creatureType = creature.getType().toString();
        String creatureChancePath = "MobNames." + creatureType + ".Chance";
        String creatureNamePath = "MobNames." + creatureType + ".Names";

        int odds = plugin.getConfiguration().getInt(creatureChancePath);

        // Our odds value represents a percentage (1 to 100) that the mob
        // gets a custom name. The random generator generates a number from
        // 0 to 99. If the generator gives us a number less than or equal
        // to our odds range, then we "hit" and will git the mob a custom
        // name. We also verify that odds are greater than 0 to begin with
        // for those mobs that don't have a config or are forcibly set to 0.
        if ((odds > 0) && (odds > rand.nextInt(100)))
        {
            List<String> nameList = plugin.getConfiguration().getStringList(creatureNamePath);

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
                int nameIndex = rand.nextInt(nameList.size()) + 1;
                creature.setCustomName(nameList.get(nameIndex - 1));
            }
        }
     }
}
