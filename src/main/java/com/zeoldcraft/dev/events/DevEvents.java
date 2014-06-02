package com.zeoldcraft.dev.events;

import java.util.Map;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.zeoldcraft.dev.abstraction.events.WGRegionChangeEvent;

public class DevEvents {
	
	@api
	public static class region_change extends AbstractEvent {

		@Override
		public BindableEvent convert(CArray arg0, Target t ) {
			throw new UnsupportedOperationException("This is not supported at this time.");
		}

		@Override
		public String docs() {
			return "{}"
					+ " Fires when a player moves to a block with a different region set than they are currently in."
					+ " {player | from: locationArray | to: locationArray | fromRegions: array of regions at the block"
					+ " they are coming from | toRegions: array of regions at the block they are moving to}"
					+ " {}"
					+ " {}";
		}

		@Override
		public Driver driver() {
			return Driver.EXTENSION;
		}

		@Override
		public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
			if (event instanceof WGRegionChangeEvent) {
				Target t = Target.UNKNOWN;
				WGRegionChangeEvent e = (WGRegionChangeEvent) event;
				Map<String, Construct> ret = evaluate_helper(e);
				ret.put("player", new CString(e.getPlayer().getName(), t));
				ret.put("from", ObjectGenerator.GetGenerator().location(e.getFrom()));
				ret.put("to", ObjectGenerator.GetGenerator().location(e.getTo()));
				CArray fn = new CArray(t);
				CArray tn = new CArray(t);
				for (String id : e.getFromRegions()) {
					fn.push(new CString(id, t));
				}
				for (String id : e.getToRegions()) {
					tn.push(new CString(id, t));
				}
				ret.put("fromRegions", fn);
				ret.put("toRegions", tn);
				return ret;
			} else {
				throw new EventException("Not a proper region change event.");
			}
		}

		@Override
		public String getName() {
			return "region_change";
		}

		@Override
		public boolean matches(Map<String, Construct> prefilter, BindableEvent event) throws PrefilterNonMatchException {
			if (event instanceof WGRegionChangeEvent) {
				return true;
			}
			return false;
		}

		@Override
		public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			return false;
		}

		@Override
		public Version since() {
			return CHVersion.V3_3_1;
		}
	}
}
