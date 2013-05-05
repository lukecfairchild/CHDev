package com.zeoldcraft.dev.abstraction.events.bukkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.abstraction.bukkit.BukkitMCConsoleCommandSender;
import com.laytonsmith.abstraction.bukkit.BukkitMCPlayer;
import com.zeoldcraft.dev.abstraction.MCCommand;
import com.zeoldcraft.dev.abstraction.bukkit.BukkitMCCommand;
import com.zeoldcraft.dev.abstraction.bukkit.BukkitMCPluginCommand;
import com.zeoldcraft.dev.abstraction.events.MCChatTabCompleteEvent;
import com.zeoldcraft.dev.abstraction.events.MCTabCompleteEvent;

public class BukkitDevEvents {
	
	public static class BukkitMCTabCompleteEvent implements MCTabCompleteEvent {

		List<String> comp;
		CommandSender sender;
		Command cmd;
		String alias;
		String[] args;
		public BukkitMCTabCompleteEvent(CommandSender sender, Command cmd, String alias, String[] args) {
			this.comp = new ArrayList<String>();
			this.sender = sender;
			this.cmd = cmd;
			this.alias = alias;
			this.args = args;
		}
		
		public Object _GetObject() {
			return comp;
		}

		public MCCommandSender getCommandSender() {
			if (sender instanceof Player) {
				return new BukkitMCPlayer((Player) sender);
			} else if (sender instanceof ConsoleCommandSender) {
				return new BukkitMCConsoleCommandSender((ConsoleCommandSender) sender);
			} else {
				return null;
			}
		}

		public MCCommand getCommand() {
			if (cmd instanceof PluginCommand) {
				return new BukkitMCPluginCommand((PluginCommand) cmd);
			}
			return new BukkitMCCommand(cmd);
		}

		public String getAlias() {
			return alias;
		}

		public String[] getArguments() {
			return args;
		}

		public List<String> getCompletions() {
			return comp;
		}

		public void setCompletions(List<String> completions) {
			comp = completions;
		}
	}
	
	public static class BukkitMCChatTabCompleteEvent implements MCChatTabCompleteEvent {

		PlayerChatTabCompleteEvent tc;
		
		public BukkitMCChatTabCompleteEvent(PlayerChatTabCompleteEvent event) {
			this.tc = event;
		}

		public Object _GetObject() {
			return tc;
		}

		public String getChatMessage() {
			return tc.getChatMessage();
		}

		public String getLastToken() {
			return tc.getLastToken();
		}

		public Collection<String> getTabCompletions() {
			return tc.getTabCompletions();
		}
	}
}
