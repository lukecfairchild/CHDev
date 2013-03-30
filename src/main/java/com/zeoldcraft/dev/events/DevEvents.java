package com.zeoldcraft.dev.events;

import java.util.HashMap;
import java.util.Map;

import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.zeoldcraft.dev.CHDev.DevEvent;
import com.zeoldcraft.dev.abstraction.events.MCPingEvent;
import com.zeoldcraft.dev.abstraction.events.MCPortalEnterEvent;
import com.zeoldcraft.dev.abstraction.events.MCTabCompleteEvent;

public class DevEvents {
	
	@api
	public static class server_ping extends DevEvent {

		public String getName() {
			return "server_ping";
		}

		public String docs() {
			// TODO Auto-generated method stub
			return "";
		}

		public boolean matches(Map<String, Construct> prefilter, BindableEvent e)
				throws PrefilterNonMatchException {
			if (e instanceof MCPingEvent) {
				return true;
			}
			return false;
		}

		public BindableEvent convert(CArray manualObject) {
			throw new ConfigRuntimeException("Unsupported Operation", Target.UNKNOWN);
		}

		public Map<String, Construct> evaluate(BindableEvent e)
				throws EventException {
			if (e instanceof MCPingEvent) {
				MCPingEvent event = (MCPingEvent) e;
				Target t = Target.UNKNOWN;
				Map<String, Construct> ret = new HashMap<String, Construct>();
				ret.put("address", new CString(event.getAddress().getHostAddress(), t));
				ret.put("motd", new CString(event.getMOTD(), t));
				ret.put("players", new CInt(event.getNumPlayers(), t));
				ret.put("maxplayers", new CInt(event.getMaxPlayers(), t));
				return ret;
			} else {
				throw new EventException("Could not convert to MCPingEvent");
			}
		}

		public boolean modifyEvent(String key, Construct value,
				BindableEvent event) {
			if (event instanceof MCPingEvent) {
				MCPingEvent e = (MCPingEvent) event;
				if (key.equals("motd")) {
					e.setMOTD(value.val());
					return true;
				}
				if (key.equals("maxplayers")) {
					e.setMaxPlayers(Static.getInt32(value, Target.UNKNOWN));
					return true;
				}
			}
			return false;
		}
	}
	
	@api
	public static class tab_complete extends DevEvent {

		public String getName() {
			return "tab_complete";
		}

		public String docs() {
			// TODO Auto-generated method stub
			return "";
		}

		public boolean matches(Map<String, Construct> prefilter, BindableEvent e)
				throws PrefilterNonMatchException {
			if (e instanceof MCTabCompleteEvent) {
				return true;
			}
			return false;
		}

		public BindableEvent convert(CArray manualObject) {
			throw new ConfigRuntimeException("Unsupported Operation", Target.UNKNOWN);
		}

		public Map<String, Construct> evaluate(BindableEvent e)
				throws EventException {
			if (e instanceof MCTabCompleteEvent) {
				MCTabCompleteEvent event = (MCTabCompleteEvent) e;
				Target t = Target.UNKNOWN;
				Map<String, Construct> ret = new HashMap<String, Construct>();
				ret.put("message", new CString(event.getChatMessage(), t));
				ret.put("last", new CString(event.getLastToken(), t));
				CArray completions = new CArray(t);
				for (String c : event.getTabCompletions()) {
					completions.push(new CString(c, t));
				}
				ret.put("completions", completions);
				return ret;
			} else {
				throw new EventException("Could not convert to MCTabCompleteEvent.");
			}
		}

		public boolean modifyEvent(String key, Construct value,
				BindableEvent event) {
			if (event instanceof MCTabCompleteEvent) {
				
			}
			return false;
		}
	}
	
	@api
	public static class portal_enter extends DevEvent {

		public String getName() {
			return "portal_enter";
		}

		public String docs() {
			// TODO Auto-generated method stub
			return "";
		}

		public boolean matches(Map<String, Construct> prefilter, BindableEvent e)
				throws PrefilterNonMatchException {
			if (e instanceof MCPortalEnterEvent) {
				return true;
			}
			return false;
		}

		public BindableEvent convert(CArray manualObject) {
			throw new ConfigRuntimeException("Unsupported Operation", Target.UNKNOWN);
		}

		public Map<String, Construct> evaluate(BindableEvent e)
				throws EventException {
			if (e instanceof MCPortalEnterEvent) {
				MCPortalEnterEvent event = (MCPortalEnterEvent) e;
				Target t = Target.UNKNOWN;
				Map<String, Construct> ret = evaluate_helper(event);
				ret.put("id", new CInt(event.getEntity().getEntityId(), t));
				ret.put("type", new CString(event.getEntity().getType().name(), t));
				ret.put("location", ObjectGenerator.GetGenerator().location(event.getLocation()));
				return ret;
			} else {
				throw new EventException("Could not convert to MCPortalEnterEvent");
			}
		}

		public boolean modifyEvent(String key, Construct value,
				BindableEvent event) {
			return false;
		}
	}
}
