package com.zeoldcraft.dev.abstraction.bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import com.laytonsmith.abstraction.MCPlugin;
import com.laytonsmith.abstraction.bukkit.BukkitMCPlugin;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.zeoldcraft.dev.abstraction.MCPluginCommand;

public class BukkitMCPluginCommand extends BukkitMCCommand implements MCPluginCommand {

	PluginCommand pc;
	public BukkitMCPluginCommand(PluginCommand pcmd) {
		super(pcmd);
		this.pc = pcmd;
	}
	
	public static MCPluginCommand newCommand(String name) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<PluginCommand> pcc = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
		pcc.setAccessible(true);
		PluginCommand npc = pcc.newInstance(name, CommandHelperPlugin.self);
		return new BukkitMCPluginCommand(npc);
	}
	
	public MCPlugin getPlugin() {
		return pc.getPlugin() == null ? null : new BukkitMCPlugin(pc.getPlugin());
	}

	public MCPlugin getExecutor() {
		return new BukkitMCPlugin((Plugin) pc.getExecutor());
	}

	public MCPlugin getTabCompleter() {
		return new BukkitMCPlugin((Plugin) pc.getTabCompleter());
	}

	public void setExecutor(MCPlugin plugin) {
		pc.setExecutor(((BukkitMCPlugin) plugin).getPlugin());
	}

	public void setTabCompleter(MCPlugin plugin) {
		pc.setTabCompleter(((BukkitMCPlugin) plugin).getPlugin());
	}
}
