package com.zeoldcraft.dev.abstraction.bukkit;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;

import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.abstraction.bukkit.BukkitMCCommandSender;
import com.zeoldcraft.dev.abstraction.MCCommand;
import com.zeoldcraft.dev.abstraction.MCCommandMap;
import com.zeoldcraft.dev.abstraction.MCPluginCommand;

public class BukkitMCCommand implements MCCommand {

	Command c;
	public BukkitMCCommand(Command cmd) {
		this.c = cmd;
	}
	
	public List<String> getAliases() {
		return c.getAliases();
	}

	public String getDescription() {
		return c.getDescription();
	}

	public String getLabel() {
		return c.getLabel() == null ? null : c.getLabel();
	}

	public String getName() {
		return c.getName();
	}

	public String getPermission() {
		return c.getPermission() == null ? null : c.getPermission();
	}

	public String getPermissionMessage() {
		return c.getPermissionMessage();
	}

	public String getUsage() {
		return c.getUsage();
	}

	public MCCommand setAliases(List<String> aliases) {
		c.setAliases(aliases);
		return this;
	}

	public MCCommand setDescription(String desc) {
		c.setDescription(desc);
		return this;
	}

	public MCCommand setLabel(String name) {
		c.setLabel(name);
		return this;
	}

	public MCCommand setPermission(String perm) {
		c.setPermission(perm);
		return this;
	}

	public MCCommand setPermissionMessage(String permmsg) {
		c.setPermissionMessage(permmsg);
		return this;
	}

	public MCCommand setUsage(String example) {
		c.setUsage(example);
		return this;
	}

	public boolean testPermission(MCCommandSender target) {
		return c.testPermission(((BukkitMCCommandSender) target)._CommandSender());
	}

	public boolean testPermissionSilent(MCCommandSender target) {
		return c.testPermissionSilent(((BukkitMCCommandSender) target)._CommandSender());
	}

	public boolean register(MCCommandMap map) {
		return c.register(((BukkitMCCommandMap) map).map);
	}

	public boolean isRegistered() {
		return c.isRegistered();
	}

	public boolean unregister(MCCommandMap map) {
		return c.unregister(((BukkitMCCommandMap) map).map);
	}

	public boolean isPluginCommand() {
		return c instanceof PluginCommand;
	}

	public MCPluginCommand asPluginCommand() {
		if (c instanceof PluginCommand) {
			return new BukkitMCPluginCommand((PluginCommand) c);
		} else {
			return null;
		}
	}
}
