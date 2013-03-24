package com.zeoldcraft.dev.functions;

import java.util.List;

import com.laytonsmith.PureUtilities.StringUtils;
import com.laytonsmith.abstraction.MCMetadataValue;
import com.laytonsmith.abstraction.MCMetadatable;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CDouble;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;
import com.zeoldcraft.dev.CHDev.DevFunction;
import com.zeoldcraft.dev.DataType;

/**
 * 
 * @author jb_aero
 */
public class MetaData {
	
	public static String docs() {
		return "Functions for using metadata on entities and blocks.";
	}
	
	public static class has_metadata extends DevFunction {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.CastException, ExceptionType.BadEntityException,
					ExceptionType.PluginInternalException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCMetadatable m;
			if(args[0] instanceof CArray) {
				throw new ConfigRuntimeException("Blocks not yet supported", ExceptionType.PluginInternalException, t);
			} else {
				m = Static.getEntity(Static.getInt32(args[0], t), t);
			}
			return new CBoolean(m.hasMetadata(args[1].val()), t);
		}

		public String getName() {
			return "has_metadata";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "boolean {entityID, key} Returns whether the entity with the given ID has any data to read.";
		}
		
	}
	
	public static class get_metadata extends DevFunction {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.PluginInternalException, ExceptionType.CastException,
					ExceptionType.BadEntityException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCMetadatable m;
			List<MCMetadataValue> vals;
			CArray ret = new CArray(t);
			String key = args[1].val();
			if(args[0] instanceof CArray) {
				throw new ConfigRuntimeException("Blocks not yet supported", ExceptionType.PluginInternalException, t);
			} else {
				m = Static.getEntity(Static.getInt32(args[0], t), t);
			}
			vals = m.getMetadata(key);
			for (MCMetadataValue v : vals) {
				CArray u = new CArray(t);
				for (DataType d : DataType.values()) {
					Construct c = new CVoid(t);
					switch (d) {
						case BOOLEAN:
							c = new CBoolean(v.asBoolean(), t);
							break;
						case DOUBLE:
							c = new CDouble(v.asDouble(), t);
							break;
						case INT:
							c = new CInt(v.asInt(), t);
							break;
						case STRING:
							c = new CString(v.asString(), t);
							break;
						case PLUGIN:
							c = new CString(v.getOwningPlugin().getName(), t);
							break;
						case SELF:
							if (v.value() instanceof Construct) {
								c = (Construct) v.value();
							}
					}
					u.set(d.name(), c, t);
				}
				ret.push(u);
			}
			return ret;
		}

		public String getName() {
			return "get_metadata";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "array {entityID, key} Returns an array of metadata arrays. Each metadata array"
					+ " contains the keys " + StringUtils.Join(DataType.values(), ", ", " and ", ", and ")
					+ ". each of the first 4 keys will contain that version of the data. PLUGIN key will"
					+ " be the name of the plugin that assigned the data. SELF key will contain the"
					+ " original value, but only if it was set by CommandHelper.";
		}
		
	}
}
