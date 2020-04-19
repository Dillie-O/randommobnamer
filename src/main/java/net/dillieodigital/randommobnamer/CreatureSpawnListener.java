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
    final Random chance;
    final RandomMobNamerPlugin plugin;

    public CreatureSpawnListener(RandomMobNamerPlugin plugin) {
        this.plugin = plugin;
        this.chance = new Random();
	}

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
     public void onCreatureSpawnEvent(CreatureSpawnEvent event)
     {
        final LivingEntity creature = event.getEntity();
        
        // Don't rename named mobs
		if (creature.getCustomName() != null) return;

		// Calculate the chance to name this mob
        String creatureType = creature.getType().toString();
        String creatureChancePath = "MobNames." + creatureType + ".Chance";
        String creatureNamePath = "MobNames." + creatureType + ".Names";

        int odds = plugin.getConfiguration().getInt(creatureChancePath);

        // No need to take a chance if the odds are set to 0.
        if (odds == 0) return;

        // Our odds value represents a percentage (1 to 100) that the mob
        // gets a custom name. We convert this to a double (maths and stuff)
        // and check it against our random chance. If the odds are greater
        // than the chance, then we "hit" and we grab a random name from the
        // available list.
        Double oddsPrecision = odds / (double) 100;

        if (oddsPrecision >= chance.nextDouble())
        {
            List<String> nameList = plugin.getConfiguration().getStringList(creatureNamePath);

            // Check for available names before assigning.
            if(nameList.size() > 0)
            {
                // Since Random.nextInt() gives us a value that includes 0 and
                // excludes the upper bound (our count), it plays perfectly into
                // our 0 based index arrays. Maybe Java planned it that way 8^D.
                int nameIndex = chance.nextInt(nameList.size());
                creature.setCustomName(nameList.get(nameIndex));
            }
        }
     }
}
