# Random Mob Namer Plugin
Need a little extra randomness in your Minecraft world? Want the extra satisfaction of being able to slay Shelob the spider? This plugin is for you!

## Overview
This plugin listens to the CreatureSpawn event. When the event fires, it checks to see what kind of mob it is and determines what the chance is that the mob will get a random name. A  "virtual dice roll" occurs and if successful, we pull from a predetermined list of random names and assign it to the mob.

The list of names and chance of success is setup through a config file so that the chances and names can be customized without having to recomple the plugin.

## Server Requirements
The plugin has been verified to run on Minecraft Spigot 1.15.2 with an API requirement of 1.13. Other platforms/versions have not been verified.

## Development Requirements
In order to compile / develop the plugin, you will need the following:

* JDK 1.8+
* Maven 3.6.3+

This plugin was built using Visual Studio Code using the Java Extension Pack, but you can just as easily build in Eclipse or from the command line.

## Compiling the Code
To compile, load the workspace into Visual Studio Code. In the "Maven Projects" section, the "RandomMobNamer" project will be displayed. Right click on this package and select "install". This will compile, build the jar file, and place it it the `target` folder.

If you are using the command line, execute the following commands:

```
mvn install -f "[PATH_TO_PROJECT_ROOT]\pom.xml"
```

## Deploying the Plugin
Once the plugin has been compiled/packaged, copy the `RandomMobNamer-[VERSION].jar` file into the `plugins` folder in your Minecraft server. You will need to restart the server for the plugin to get installed. If you are upgrading from a previous version, make sure to remove the older version jar file.

When the plugin has been successfully installed and loaded, you will see the following message in your server logs:

> Bienvenue, Random Mob Namer!

Likewise, you can confirm the plugin has been unloaded when you see the folowing message:

> Peace out, Random Mob Namer!

## Configuring Options
When the plugin is installed for the first time, it will create a `RandomMobNamer/config.yml` file in your plugins folder. The contents of that file include all the mob types, the chance (integer reflecting percentage) of it getting a random name, and the random names available for the mob.

You can make changes to this file and reload the plugin to have them take effect. A couple of notes related to the configuration file:

The mob type must be in all caps and match the entity type as outlined [here](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/EntityType.html). You'll notice that there are a lot of things that are entities. We've taken the liberty of naming some boats for you. You may want to have fun and name llama spit or a splash potion that is flying at you!

There is no special character escaping done on the mob names themselves. Please keep them to simple strings without special characters. If you're familiar enough with YAML and/or how the Minecraft API sets custom names, you can modify these values at your own risk.