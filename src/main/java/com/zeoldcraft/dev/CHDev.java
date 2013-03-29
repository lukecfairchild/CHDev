package com.zeoldcraft.dev;

import org.bukkit.event.Listener;
import com.laytonsmith.annotations.shutdown;
import com.laytonsmith.annotations.startup;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.functions.AbstractFunction;

public class CHDev implements Listener {
	
	private static CommandHelperPlugin chp;
	private static DevListener dl;
	
	@startup
	public static void setup() {
		chp = CommandHelperPlugin.self;
		Static.getLogger().info("Activating jb_aero's dev extension, make sure you know how it works!");
		dl = new DevListener(chp);
	}
	
	@shutdown
	public static void unload() {
		dl.unregister();
	}
	
	public static abstract class DevFunction extends AbstractFunction {

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public CHVersion since() {
			return CHVersion.V3_3_1;
		}
	}

	public static abstract class DevEvent extends AbstractEvent {

		public CHVersion since() {
			return CHVersion.V3_3_1;
		}

		public Driver driver() {
			return Driver.EXTENSION;
		}
	}
}
