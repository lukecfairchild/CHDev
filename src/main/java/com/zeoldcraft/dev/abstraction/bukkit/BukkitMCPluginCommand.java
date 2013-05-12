package com.zeoldcraft.dev.abstraction.bukkit;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import com.laytonsmith.PureUtilities.ReflectionUtils;
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
	
	public static MCPluginCommand newCommand(String name) {
		return new BukkitMCPluginCommand(ReflectionUtils.newInstance(PluginCommand.class,
				new Class[]{String.class, Plugin.class}, new Object[]{name, CommandHelperPlugin.self}));
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
