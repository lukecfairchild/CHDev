package com.zeoldcraft.dev;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.zeoldcraft.dev.abstraction.events.bukkit.BukkitDevEvents;

public class DevListener implements Listener {

	public DevListener(CommandHelperPlugin chp) {
		chp.registerEvent(this);
	}
	
	public void unregister() {
		PlayerChatTabCompleteEvent.getHandlerList().unregister(this);
	}
	
	@EventHandler(priority= EventPriority.LOWEST)
	public void onTab(PlayerChatTabCompleteEvent event) {
		BukkitDevEvents.BukkitMCTabCompleteEvent e = new BukkitDevEvents.BukkitMCTabCompleteEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "tab_complete", e);
	}
}
