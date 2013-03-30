package com.zeoldcraft.dev.abstraction.events.bukkit;

import java.net.InetAddress;
import java.util.Collection;

import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.server.ServerListPingEvent;

import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.bukkit.BukkitMCEntity;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.zeoldcraft.dev.abstraction.MCFishHook;
import com.zeoldcraft.dev.abstraction.bukkit.BukkitMCFishHook;
import com.zeoldcraft.dev.abstraction.enums.MCFishingState;
import com.zeoldcraft.dev.abstraction.enums.bukkit.BukkitMCFishingState;
import com.zeoldcraft.dev.abstraction.events.MCPlayerFishEvent;
import com.zeoldcraft.dev.abstraction.events.MCPortalEnterEvent;
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
	
	public static class BukkitMCPortalEnterEvent implements MCPortalEnterEvent {

		EntityPortalEnterEvent epe;
		public BukkitMCPortalEnterEvent(EntityPortalEnterEvent event) {
			epe = event;
		}
		
		public Object _GetObject() {
			return epe;
		}

		public MCEntity getEntity() {
			return new BukkitMCEntity(epe.getEntity());
		}

		public MCLocation getLocation() {
			return new BukkitMCLocation(epe.getLocation());
		}
	}
	
	public static class BukkitMCPlayerFishEvent implements MCPlayerFishEvent {

		PlayerFishEvent e;
		public BukkitMCPlayerFishEvent(PlayerFishEvent event) {
			e = event;
		}
		
		public Object _GetObject() {
			return e;
		}

		public MCEntity getCaught() {
			if (e.getCaught() == null) {
				return null;
			}
			return new BukkitMCEntity(e.getCaught());
		}

		public int getExpToDrop() {
			return e.getExpToDrop();
		}

		public MCFishHook getHook() {
			return new BukkitMCFishHook(e.getHook());
		}

		public MCFishingState getState() {
			return BukkitMCFishingState.getConvertor().getAbstractedEnum(e.getState());
		}

		public void setExpToDrop(int exp) {
			e.setExpToDrop(exp);
		}
	}
}
