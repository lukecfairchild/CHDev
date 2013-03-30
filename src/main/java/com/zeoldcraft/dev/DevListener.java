package com.zeoldcraft.dev;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.server.ServerListPingEvent;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.zeoldcraft.dev.abstraction.events.bukkit.BukkitDevEvents;

public class DevListener implements Listener {

	public DevListener(CommandHelperPlugin chp) {
		chp.registerEvent(this);
	}
	
	public void unregister() {
		ServerListPingEvent.getHandlerList().unregister(this);
		PlayerChatTabCompleteEvent.getHandlerList().unregister(this);
		EntityPortalEnterEvent.getHandlerList().unregister(this);
		PlayerFishEvent.getHandlerList().unregister(this);
	}
	
	@EventHandler
	public void onPing(ServerListPingEvent event) {
		BukkitDevEvents.BukkitMCPingEvent e = new BukkitDevEvents.BukkitMCPingEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "server_ping", e);
	}
	
	@EventHandler
	public void onTab(PlayerChatTabCompleteEvent event) {
		BukkitDevEvents.BukkitMCTabCompleteEvent e = new BukkitDevEvents.BukkitMCTabCompleteEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "tab_complete", e);
	}
	
	@EventHandler
	public void onPortalEnter(EntityPortalEnterEvent event) {
		BukkitDevEvents.BukkitMCPortalEnterEvent e = new BukkitDevEvents.BukkitMCPortalEnterEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "portal_enter", e);
	}
	
	@EventHandler
	public void onFish(PlayerFishEvent event) {
		BukkitDevEvents.BukkitMCPlayerFishEvent e = new BukkitDevEvents.BukkitMCPlayerFishEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "player_fish", e);
	}
}
