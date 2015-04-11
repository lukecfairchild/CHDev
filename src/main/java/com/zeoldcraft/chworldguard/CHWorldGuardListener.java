package com.zeoldcraft.chworldguard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.zeoldcraft.chworldguard.abstraction.events.bukkit.BukkitWorldGuardEvents;

public class CHWorldGuardListener implements Listener {

	public CHWorldGuardListener(CommandHelperPlugin chp) {
		chp.registerEvents(this);
	}
	
	public void unregister() {
		PlayerMoveEvent.getHandlerList().unregister(this);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onRgMove(PlayerMoveEvent event) {
		Location from = event.getFrom();
		Location to = event.getTo();
		
		// As WG does, check if they are even in a different block
		if (from.getBlockX() != to.getBlockX()
						|| from.getBlockY() != to.getBlockY()
						|| from.getBlockZ() != to.getBlockZ()) {
			World fromworld = from.getWorld();
			World toWorld = to.getWorld();
			
			ApplicableRegionSet fromSet = WGBukkit.getRegionManager(fromworld).getApplicableRegions(from);
			ApplicableRegionSet toSet = WGBukkit.getRegionManager(toWorld).getApplicableRegions(to);
			
			List<String> fromNames = new ArrayList<String>();
			for (ProtectedRegion reg : fromSet) {
				fromNames.add(reg.getId());
			}
			
			List<String> toNames = new ArrayList<String>();
			for (ProtectedRegion reg : toSet) {
				toNames.add(reg.getId());
			}
			
			boolean fire = false;
			if (fromNames.size() == toNames.size()) {
				for (String id : fromNames) {
					if (!toNames.contains(id)) {
						fire = true;
						break;
					}
				}
			} else {
				// If they are different sizes, obviously the region sets are different
				fire = true;
			}
			
			if (fire) {
				BukkitWorldGuardEvents.BukkitWGRegionChangeEvent rgchange
						= new BukkitWorldGuardEvents.BukkitWGRegionChangeEvent(event.getPlayer(), fromNames, toNames, from, to);
				EventUtils.TriggerListener(Driver.EXTENSION, "region_change", rgchange);
				
				if (rgchange.isCancelled()) {
					// Use WG's method of cancelling the event
					Location newLoc = event.getFrom();
					newLoc.setX(newLoc.getBlockX() + 0.5);
					newLoc.setY(newLoc.getBlockY());
					newLoc.setZ(newLoc.getBlockZ() + 0.5);
					event.setTo(newLoc);
				}
			}
		}
	}
}
