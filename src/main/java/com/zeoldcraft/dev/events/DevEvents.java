package com.zeoldcraft.dev.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;
import com.zeoldcraft.dev.CHDev.DevEvent;
import com.zeoldcraft.dev.abstraction.events.MCChatTabCompleteEvent;
import com.zeoldcraft.dev.abstraction.events.MCTabCompleteEvent;

public class DevEvents {
	
	@api
	public static class tab_complete extends DevEvent {

		public String getName() {
			return "tab_complete";
		}

		public String docs() {
			// TODO Auto-generated method stub
			return "{}"
					+ " Fires for any command owned by whatever is firing the CH event"
					+ " {}"
					+ " {}";
		}

		public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
			if (e instanceof MCTabCompleteEvent) {
				return true;
			}
			return false;
		}

		public BindableEvent convert(CArray manualObject) {
			throw new ConfigRuntimeException("Operation not supported", Target.UNKNOWN);
		}

		public Map<String, Construct> evaluate(BindableEvent e) throws EventException {
			if (e instanceof MCTabCompleteEvent) {
				MCTabCompleteEvent event = (MCTabCompleteEvent) e;
				Map<String, Construct> ret = new HashMap<String, Construct>();
				Target t = Target.UNKNOWN;
				ret.put("macrotype", new CString("custom", t));
				ret.put("event_type", new CString(getName(), t));
				CArray completions = new CArray(t);
				for (String option : event.getCompletions()) {
					completions.push(new CString(option, t));
				}
				ret.put("completions", completions);
				ret.put("sender", new CString(event.getCommandSender().getName(), t));
				ret.put("command", new CString(event.getCommand().getName(), t));
				CArray args = new CArray(t);
				for (String a : event.getArguments()) {
					args.push(new CString(a, t));
				}
				ret.put("args", args);
				return ret;
			} else {
				throw new EventException("Could not convert to MCTabCompleteEvent");
			}
		}

		public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			if (event instanceof MCTabCompleteEvent) {
				if(key.equals("completions")) {
					if (value instanceof CArray) {
						List<String> comp = new ArrayList<String>();
						for (String k : ((CArray) value).keySet()) {
							comp.add(((CArray) value).get(k).val());
						}
						((MCTabCompleteEvent) event).setCompletions(comp);
						return true;
					} else {
						throw new ConfigRuntimeException("Modified completions needed to be an array.",
								ExceptionType.FormatException, Target.UNKNOWN);
					}
				}
			}
			return false;
		}
	}
	
	@api
	public static class chat_tab_complete extends DevEvent {

		public String getName() {
			return "chat_tab_complete";
		}

		public String docs() {
			// TODO Auto-generated method stub
			return "";
		}

		public boolean matches(Map<String, Construct> prefilter, BindableEvent e)
				throws PrefilterNonMatchException {
			if (e instanceof MCChatTabCompleteEvent) {
				return true;
			}
			return false;
		}

		public BindableEvent convert(CArray manualObject) {
			throw new ConfigRuntimeException("Unsupported Operation", Target.UNKNOWN);
		}

		public Map<String, Construct> evaluate(BindableEvent e)
				throws EventException {
			if (e instanceof MCChatTabCompleteEvent) {
				MCChatTabCompleteEvent event = (MCChatTabCompleteEvent) e;
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
			if (event instanceof MCChatTabCompleteEvent) {
				
			}
			return false;
		}
	}
}
