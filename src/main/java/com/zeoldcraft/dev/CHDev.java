package com.zeoldcraft.dev;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import com.avaje.ebean.EbeanServer;
import com.laytonsmith.annotations.shutdown;
import com.laytonsmith.annotations.startup;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.laytonsmith.core.functions.AbstractFunction;
import com.zeoldcraft.dev.abstraction.events.bukkit.BukkitDevEvents.BukkitMCTabCompleteEvent;

public class CHDev implements Plugin {
	
	private static CommandHelperPlugin chp;
	private static DevListener dl;
	public static CHDev myself = new CHDev();
	
	@startup
	public static void setup() {
		chp = CommandHelperPlugin.self;
		Static.getLogger().info("Activating jb_aero's dev extension, make sure you know how it works!");
		dl = new DevListener(chp);
	}
	
	@shutdown
	public static void unload() {
		dl.unregister();
	}
	
	public static abstract class DFun extends AbstractFunction {

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public CHVersion since() {
			return CHVersion.V3_3_1;
		}

		public String getName() {
			String cname = this.getClass().getName();
			return cname.substring(cname.indexOf("$") + 1, cname.length());
		}
	}

	public static abstract class DevEvent extends AbstractEvent {

		public CHVersion since() {
			return CHVersion.V3_3_1;
		}

		public Driver driver() {
			return Driver.EXTENSION;
		}
	}

	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		BukkitMCTabCompleteEvent event = new BukkitMCTabCompleteEvent(sender, command, alias, args);
		EventUtils.TriggerListener(Driver.EXTENSION, "tab_complete", event);
		return event.getCompletions();
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("Oops, you should not be seeing this."
				+ " \nAny case, your name is: " + sender.getName()
				+ " \nThe command being run is: " + command.getName()
				+ " \nUnder label: " + label
				+ " \nWith arguments: " + args.toString());
		return true;
	}

	public File getDataFolder() {
		return null;
	}

	public PluginDescriptionFile getDescription() {
		return null;
	}

	public FileConfiguration getConfig() {
		return null;
	}

	public InputStream getResource(String filename) {
		return null;
	}

	public void saveConfig() {
		
	}

	public void saveDefaultConfig() {
		
	}

	public void saveResource(String resourcePath, boolean replace) {
		
	}

	public void reloadConfig() {
		
	}

	public PluginLoader getPluginLoader() {
		return null;
	}

	public Server getServer() {
		return Bukkit.getServer();
	}

	public boolean isEnabled() {
		return false;
	}

	public void onDisable() {
		
	}

	public void onLoad() {
		
	}

	public void onEnable() {
		
	}

	public boolean isNaggable() {
		return false;
	}

	public void setNaggable(boolean canNag) {
		
	}

	public EbeanServer getDatabase() {
		return null;
	}

	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return null;
	}

	public Logger getLogger() {
		return CommandHelperPlugin.self.getLogger();
	}

	public String getName() {
		return "CHDev";
	}
	
	/*
    public static List<Location> circle (Player player, Location loc, Integer r, Integer h, Boolean hollow, Boolean sphere, int plus_y) {
        List<Location> circleblocks = new ArrayList<Location>();
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        for (int x = cx - r; x <= cx +r; x++)
            for (int z = cz - r; z <= cz +r; z++)
                for (int y = (sphere ? cy - r : cy); y < (sphere ? cy + r : cy + h); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (dist < r*r && !(hollow && dist < (r-1)*(r-1))) {
                        Location l = new Location(loc.getWorld(), x, y + plus_y, z);
                        circleblocks.add(l);
                        }
                    }
     
        return circleblocks;
    }
    */
}
