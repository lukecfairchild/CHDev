package com.zeoldcraft.dev.abstraction.events.bukkit;

import java.util.Collection;

import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import com.zeoldcraft.dev.abstraction.events.MCTabCompleteEvent;

public class BukkitDevEvents {
	
	public static class BukkitMCTabCompleteEvent implements MCTabCompleteEvent {

		PlayerChatTabCompleteEvent tc;
		
		public BukkitMCTabCompleteEvent(PlayerChatTabCompleteEvent event) {
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
