package com.zeoldcraft.chworldguard.abstraction.events.bukkit;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.zeoldcraft.chworldguard.abstraction.events.WGRegionChangeEvent;

public class BukkitWorldGuardEvents {
	
	public static class BukkitWGRegionChangeEvent extends Event
			implements WGRegionChangeEvent, Cancellable {

		Player player;
		Location to;
		Location from;
		List<String> toRegions;
		List<String> fromRegions;
		boolean cancelled = false;
		private static final HandlerList handlers = new HandlerList();
		
		public BukkitWGRegionChangeEvent(Player pl, List<String> fromNames, List<String> toNames, Location f, Location t) {
			player = pl;
			toRegions = toNames;
			fromRegions = fromNames;
			from = f;
			to = t;
		}
		
		@Override
		public Object _GetObject() {
			return this;
		}

		@Override
		public MCLocation getFrom() {
			return new BukkitMCLocation(from);
		}

		@Override
		public MCLocation getTo() {
			return new BukkitMCLocation(to);
		}

		@Override
		public List<String> getFromRegions() {
			return fromRegions;
		}

		@Override
		public List<String> getToRegions() {
			return toRegions;
		}

		@Override
		public HandlerList getHandlers() {
			return handlers;
		}
		
		public static HandlerList getHandlerList() {
	        return handlers;
	    }

		@Override
		public boolean isCancelled() {
			return cancelled;
		}

		@Override
		public void setCancelled(boolean arg0) {
			cancelled = arg0;
		}

		@Override
		public MCPlayer getPlayer() {
			return new BukkitMCPlayer(player);
		}
	}
}
