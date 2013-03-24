package com.zeoldcraft.dev;

import com.laytonsmith.annotations.startup;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.functions.AbstractFunction;

public class CHDev {

	@startup
	public static void setup() {
		Static.getLogger().info("Activating jb_aero's dev extension, make sure you know how it works!");
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
}
