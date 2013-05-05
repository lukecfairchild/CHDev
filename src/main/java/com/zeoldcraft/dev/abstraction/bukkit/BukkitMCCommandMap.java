package com.zeoldcraft.dev.abstraction.bukkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;

import com.zeoldcraft.dev.abstraction.MCCommand;
import com.zeoldcraft.dev.abstraction.MCCommandMap;

public class BukkitMCCommandMap implements MCCommandMap {

	SimpleCommandMap map;
	public BukkitMCCommandMap() throws Exception {
		Object invokedMap;
		try {
			invokedMap = Bukkit.getServer().getClass().getMethod("getCommandMap").invoke(Bukkit.getServer());
			if (invokedMap instanceof SimpleCommandMap) {
				map = (SimpleCommandMap) invokedMap;
			} else {
				throw new Exception("Could not retrieve the server's command map.");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void clearCommands() {
		map.clearCommands();
	}

	public MCCommand getCommand(String name) {
		return map.getCommand(name) == null ? null : new BukkitMCCommand(map.getCommand(name));
	}

	public List<MCCommand> getCommands() {
		List<MCCommand> ret = new ArrayList<MCCommand>();
		for (Command c : map.getCommands()) {
			ret.add(new BukkitMCCommand(c));
		}
		return ret;
	}

	public boolean register(String fallback, MCCommand cmd) {
		return map.register(fallback, ((BukkitMCCommand) cmd).c);
	}

	public boolean register(String label, String fallback, MCCommand cmd) {
		return map.register(label, fallback, ((BukkitMCCommand) cmd).c);
	}

	public boolean unregister(MCCommand cmd) {
		if (cmd.isRegistered()) {
			return cmd.unregister(this);
		} else {
			return false;
		}
	}
}
