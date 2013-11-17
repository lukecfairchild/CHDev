package com.zeoldcraft.dev.events;

public class DevEvents {
	
	/*@api
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
			throw new ConfigRuntimeException("Operation not supported", ExceptionType.BindException, Target.UNKNOWN);
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
	}*/
}
