package com.zeoldcraft.dev;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import com.laytonsmith.abstraction.bukkit.events.BukkitPlayerEvents;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;

public class DevListener implements Listener {

	public DevListener(CommandHelperPlugin chp) {
		chp.registerEvent(this);
	}
	
	public void unregister() {
		PlayerChatTabCompleteEvent.getHandlerList().unregister(this);
	}
	
	@EventHandler(priority= EventPriority.LOWEST)
	public void onChatTab(PlayerChatTabCompleteEvent event) {
		BukkitPlayerEvents.BukkitMCChatTabCompleteEvent e = new BukkitPlayerEvents.BukkitMCChatTabCompleteEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "tab_complete_chat", e);
	}
}
