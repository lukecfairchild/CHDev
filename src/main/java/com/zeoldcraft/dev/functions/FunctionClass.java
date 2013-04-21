package com.zeoldcraft.dev.functions;

import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;
import com.zeoldcraft.dev.abstraction.MCTNT;

public class FunctionClass {

	// In order for the plugin to recognize a class as a function,
	// you need to include this annotation
	@api
	public static class get_fuse_ticks extends AbstractFunction {
	// By extending AbstractFunction, you save yourself a lot of work,
	// and all these methods will be created, you just have to fill them out

		// This alerts CH ahead of time what this function expects can go wrong
		public ExceptionType[] thrown() {
			return new ExceptionType[]{
					// Thrown by Static.getEntity and if(!(ent instanceof MCTNT))
					ExceptionType.BadEntityException,
					
					// Thrown by Static.getInt32
					ExceptionType.CastException};
		}

		// Should a player with no permissions be blocked from using this function?
		// This spares you permission checking
		public boolean isRestricted() {
			return true;
		}

		// If you don't know how this works, you should put false unless LC advises otherwise
		public Boolean runAsync() {
			return false;
		}

		// Here is where we actually code what the function does
		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			
			// We want to get a TNT by it's entity ID, so first we need the id
			// This line gets a 32-bit integer (an int) from the first argument passed in the function
			int id = Static.getInt32(args[0], t);
			
			// Next, we want to use that ID to get a specific entity
			// This method will get a wrapper extending BukkitMCEntity
			// if we are using a bukkit-based server, and the entity exists
			MCEntity ent = Static.getEntity(id, t);
			
			// Then we will check if the entity is using the TNT interface
			// if not, the function needs to throw an exception
			if (!(ent instanceof MCTNT)) {
				throw new ConfigRuntimeException("The entity was not a Primed TNT",
						ExceptionType.BadEntityException, t);
			}
			
			// Now that we know it's TNT, we can go ahead and get the fuse ticks
			int fuse = ((MCTNT) ent).getFuseTicks();
			
			// and finally, we return it as a CH-readable object
			return new CInt(fuse, t);
		}

		// The name of the function, used for docs
		public String getName() {
			return "get_fuse_ticks";
		}

		// How many arguments should be inside the parenthesis?
		// This saves you from checking if they have too many or too few args
		// It is an array because you could have multiple amounts
		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		// How-to-use method, use by the wiki
		// Format: 'return {arg1, [optionalarg2]} Description'
		public String docs() {
			return "int {entityID} Returns the number of ticks remaining on the specified TNT's fuse.";
		}

		// Used by the wiki, used to track when features were added
		public CHVersion since() {
			return CHVersion.V3_3_1;
		}
	}
}
