package com.zeoldcraft.dev.events;

import java.util.Map;

import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.zeoldcraft.dev.CHDev.DevEvent;
import com.zeoldcraft.dev.abstraction.events.MCTabCompleteEvent;

public class DevEvents {
	
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
				Map<String, Construct> ret = evaluate_helper(event);
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
}
