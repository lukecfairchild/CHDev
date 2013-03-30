package com.zeoldcraft.dev;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.server.ServerListPingEvent;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.EventUtils;
import com.zeoldcraft.dev.abstraction.events.bukkit.BukkitDevEvents.*;

public class DevListener implements Listener {

	public DevListener(CommandHelperPlugin chp) {
		chp.registerEvent(this);
	}
	
	public void unregister() {
		ServerListPingEvent.getHandlerList().unregister(this);
		PlayerChatTabCompleteEvent.getHandlerList().unregister(this);
	}
	
	@EventHandler
	public void onPing(ServerListPingEvent event) {
		BukkitMCPingEvent e = new BukkitMCPingEvent(event);
		EventUtils.TriggerExternal(e);
	}
	
	@EventHandler
	public void onTab(PlayerChatTabCompleteEvent event) {
		BukkitMCTabCompleteEvent e = new BukkitMCTabCompleteEvent(event);
		EventUtils.TriggerExternal(e);
	}
	
	@EventHandler
	public void onPortalEnter(EntityPortalEnterEvent event) {
		BukkitMCPortalEnterEvent e = new BukkitMCPortalEnterEvent(event);
		EventUtils.TriggerExternal(e);
	}
}
