package com.zeoldcraft.dev.abstraction.events.bukkit;

import java.net.InetAddress;
import java.util.Collection;

import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.server.ServerListPingEvent;

import com.zeoldcraft.dev.abstraction.events.MCPingEvent;
import com.zeoldcraft.dev.abstraction.events.MCTabCompleteEvent;

public class BukkitDevEvents {
	
	public static class BukkitMCPingEvent implements MCPingEvent {

		ServerListPingEvent slp;
		public BukkitMCPingEvent(ServerListPingEvent event) {
			slp = event;
		}
		
		public Object _GetObject() {
			return slp;
		}

		public InetAddress getAddress() {
			return slp.getAddress();
		}

		public int getMaxPlayers() {
			return slp.getMaxPlayers();
		}

		public String getMOTD() {
			return slp.getMotd();
		}

		public int getNumPlayers() {
			return slp.getNumPlayers();
		}

		public void setMaxPlayers(int max) {
			slp.setMaxPlayers(max);
		}

		public void setMOTD(String motd) {
			slp.setMotd(motd);
		}
	}
	
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
