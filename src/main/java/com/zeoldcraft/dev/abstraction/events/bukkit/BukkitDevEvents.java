package com.zeoldcraft.dev.abstraction.events.bukkit;

import java.net.InetAddress;

import org.bukkit.event.server.ServerListPingEvent;

import com.zeoldcraft.dev.abstraction.events.MCPingEvent;

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
}
