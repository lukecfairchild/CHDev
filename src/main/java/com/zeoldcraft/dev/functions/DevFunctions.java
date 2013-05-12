package com.zeoldcraft.dev.functions;

import java.util.ArrayList;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlockFace;
import com.laytonsmith.abstraction.blocks.MCBlockState;
import com.laytonsmith.abstraction.blocks.MCFallingBlock;
import com.laytonsmith.abstraction.bukkit.BukkitMCPlugin;
import com.laytonsmith.abstraction.bukkit.BukkitMCServer;
import com.laytonsmith.abstraction.entities.MCEnderman;
import com.laytonsmith.abstraction.entities.MCOcelot;
import com.laytonsmith.abstraction.entities.MCSheep;
import com.laytonsmith.abstraction.entities.MCWolf;
import com.laytonsmith.abstraction.enums.MCDyeColor;
import com.laytonsmith.abstraction.enums.MCOcelotType;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.Exceptions;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;
import com.zeoldcraft.dev.CHDev;
import com.zeoldcraft.dev.CHDev.DFun;
import com.zeoldcraft.dev.abstraction.MCCommand;
import com.zeoldcraft.dev.abstraction.MCCommandMap;
import com.zeoldcraft.dev.abstraction.MCPluginCommand;
import com.zeoldcraft.dev.abstraction.blocks.MCSkull;
import com.zeoldcraft.dev.abstraction.bukkit.BukkitMCCommandMap;
import com.zeoldcraft.dev.abstraction.bukkit.BukkitMCPluginCommand;

public class DevFunctions {
	
	public String docs() {
		return "Function testing for EntityManagement class.";
	}
	
	@api
	public static class get_entity_spec extends DFun {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.CastException, ExceptionType.BadEntityException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCEntity ent = Static.getEntity(Static.getInt32(args[0], t), t);
			CArray ret = new CArray(t);
			if (ent instanceof MCItem) {
				ret.set("itemstack", ObjectGenerator.GetGenerator().item(((MCItem) ent).getItemStack(), t), t);
				ret.set("pickupdelay", new CInt(((MCItem) ent).getPickupDelay(), t), t);
			}
			if (ent instanceof MCFallingBlock) {
				ret.set("dropitem", new CBoolean(((MCFallingBlock) ent).getDropItem(), t), t);
				ret.set("type", new CInt(((MCFallingBlock) ent).getBlockId(), t), t);
				ret.set("data", new CInt(((MCFallingBlock) ent).getBlockData(), t), t);
			}
			if (ent instanceof MCExperienceOrb) {
				ret.set("exp", new CInt(((MCExperienceOrb) ent).getExperience(), t), t);
			}
			if (ent instanceof MCLightningStrike) {
				ret.set("iseffect", new CBoolean(((MCLightningStrike) ent).isEffect(), t), t);
			}
			if (ent instanceof MCTNT) {
				
			}
			if (ent instanceof MCFireball) {
				ret.set("direction", ObjectGenerator.GetGenerator().velocity(((MCFireball) ent).getDirection(), t), t);
			}
			if (ent instanceof MCProjectile) {
				ret.set("shooter", new CInt(((MCProjectile) ent).getShooter().getEntityId(), t), t);
				ret.set("bounce", new CBoolean(((MCProjectile) ent).doesBounce(), t), t);
			}
			if (ent instanceof MCHanging) {
				ret.set("facing", new CString(((MCHanging) ent).getFacing().name(), t), t);
			}
			if (ent instanceof MCWolf) {
				ret.set("color", new CString(((MCWolf) ent).getCollarColor().name(), t), t);
				ret.set("angry", new CBoolean(((MCWolf) ent).isAngry(), t), t);
				ret.set("sitting", new CBoolean(((MCWolf) ent).isSitting(), t), t);
			}
			if (ent instanceof MCOcelot) {
				ret.set("cat", new CString(((MCOcelot) ent).getCatType().name(), t), t);
				ret.set("sitting", new CBoolean(((MCOcelot) ent).isSitting(), t), t);
			}
			if (ent instanceof MCEnderman) {
				ret.set("type", new CInt(((MCEnderman) ent).getCarriedType(), t), t);
				ret.set("data", new CInt(((MCEnderman) ent).getCarriedData(), t), t);
			}
			if (ent instanceof MCSheep) {
				ret.set("color", new CString(((MCSheep) ent).getColor().name(), t), t);
			}
			return ret;
		}

		public String getName() {
			return "get_entity_spec";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "specsArray {entityID} Returns an array of information specific to this entitytype.";
		}
	}
	
	@api
	public static class set_entity_spec extends DFun {

		public ExceptionType[] thrown() {
			// TODO Auto-generated method stub
			return new ExceptionType[]{ExceptionType.CastException, ExceptionType.BadEntityException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCEntity ent = Static.getEntity(Static.getInt32(args[0], t), t);
			if (!(args[1] instanceof CArray)) {
				throw new Exceptions.FormatException("Arg 2 was expected to be an array", t);
			}
			CArray ca = (CArray) args[1];
			if (ent instanceof MCItem) {
				((MCItem) ent).setItemStack(ObjectGenerator.GetGenerator().item(ca.get("itemstack", t), t));
				((MCItem) ent).setPickupDelay(Static.getInt32(ca.get("pickupdelay", t), t));
			}
			if (ent instanceof MCFallingBlock) {
				((MCFallingBlock) ent).setDropItem(Static.getBoolean(ca.get("dropitem", t)));
			}
			if (ent instanceof MCExperienceOrb) {
				((MCExperienceOrb) ent).setExperience(Static.getInt32(ca.get("exp"), t));
			}
			if (ent instanceof MCLightningStrike) {
				
			}
			if (ent instanceof MCTNT) {
				
			}
			if (ent instanceof MCFireball) {
				((MCFireball) ent).setDirection(ObjectGenerator.GetGenerator().velocity(ca.get("direction", t), t));
			}
			if (ent instanceof MCProjectile) {
				((MCProjectile) ent).setShooter(Static.getLivingEntity(Static.getInt32(ca.get("shooter", t), t), t));
				((MCProjectile) ent).setBounce(Static.getBoolean(ca.get("bounce", t)));
			}
			if (ent instanceof MCHanging) {
				try {
					((MCHanging) ent).setFacingDirection(MCBlockFace.valueOf(ca.get("facing", t).val()), true);
				} catch (IllegalArgumentException iae) {
					throw new Exceptions.FormatException("Not a valid blockface.", t);
				}
			}
			if (ent instanceof MCWolf) {
				try {
					((MCWolf) ent).setCollarColor(MCDyeColor.valueOf(ca.get("color", t).val()));
				} catch (IllegalArgumentException iae) {
					throw new Exceptions.FormatException("Not a valid color.", t);
				}
				((MCWolf) ent).setAngry(Static.getBoolean(ca.get("angry", t)));
				((MCWolf) ent).setSitting(Static.getBoolean(ca.get("sitting", t)));
			}
			if (ent instanceof MCOcelot) {
				try {
					((MCOcelot) ent).setCatType(MCOcelotType.valueOf(ca.get("cat", t).val()));
				} catch (IllegalArgumentException iae) {
					throw new Exceptions.FormatException("Not a valid cat type.", t);
				}
				((MCOcelot) ent).setSitting(Static.getBoolean(ca.get("sitting", t)));
			}
			if (ent instanceof MCEnderman) {
				((MCEnderman) ent).setCarriedMaterial(Static.getInt32(ca.get("type", t), t), 
						Static.getInt8(ca.get("data", t), t));
			}
			if (ent instanceof MCSheep) {
				try {
					((MCSheep) ent).setColor(MCDyeColor.valueOf(ca.get("color", t).val()));
				} catch (IllegalArgumentException iae) {
					throw new Exceptions.FormatException("Not a valid color.", t);
				}
			}
			return new CVoid(t);
		}

		public String getName() {
			return "set_entity_spec";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "void {entityID, specsArray} Sets properties on an entity. Nothing is optional atm.";
		}
	}
	
	@api
	public static class get_head_at extends DFun {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.FormatException, ExceptionType.CastException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCLocation loc = ObjectGenerator.GetGenerator().location(args[0], null, t);
			MCBlockState bs = loc.getBlock().getState();
			if (bs instanceof MCSkull) {
				MCSkull sk = (MCSkull) bs;
				return new CString(sk.getSkullType() + "|" + sk.getOwner() + "|" + sk.getRotation().name(), t);
			}
			return new CNull(t);
		}

		public String getName() {
			return "get_head_at";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "string {locationArray} Returns skull data in the form 'skulltype|name|rotation'";
		}
	}
	
	@api
	public static class get_commands extends DFun {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.PluginInternalException};
		}

		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
			Server s = ((BukkitMCServer) Static.getServer()).__Server();
			SimpleCommandMap scm;
			Object invokedMap;
			try {
				invokedMap = s.getClass().getMethod("getCommandMap").invoke(s);
			} catch (Exception e) {
				throw new ConfigRuntimeException(e.getMessage(), ExceptionType.PluginInternalException, t);
			}
			if (invokedMap instanceof SimpleCommandMap) {
				scm = (SimpleCommandMap) invokedMap;
				CArray ret = new CArray(t);
				for (Command cmd : scm.getCommands()) {
					CArray com = new CArray(t);
					com.set("name", new CString(cmd.getName(), t), t);
					Construct label;
					if (cmd.getLabel() == null) {
						label = new CNull(t);
					} else {
						label = new CString(cmd.getLabel(), t);
					}
					com.set("label", label, t);
					com.set("description", new CString(cmd.getDescription(), t), t);
					Construct permission;
					if (cmd.getPermission() == null) {
						permission = new CNull(t);
					} else {
						permission = new CString(cmd.getPermission(), t);
					}
					com.set("permission", permission, t);
					com.set("nopermmsg", new CString(cmd.getPermissionMessage(), t), t);
					com.set("usage", new CString(cmd.getUsage(), t), t);
					CArray aliases = new CArray(t);
					for (String a : cmd.getAliases()) {
						aliases.push(new CString(a, t));
					}
					com.set("aliases", aliases, t);
					ret.set(cmd.getLabel(), com, t);
				}
				return ret;
			} else {
				throw new ConfigRuntimeException("CommandMap not found", ExceptionType.PluginInternalException, t);
			}
		}

		public String getName() {
			return "get_commands";
		}

		public Integer[] numArgs() {
			return new Integer[]{0};
		}

		public String docs() {
			return "array {} Array of command keys with associative array values";
		}
	}
	
	@api
	public static class register_command extends DFun {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.PluginInternalException};
		}

		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
			MCPluginCommand cmd;
			try {
				cmd = BukkitMCPluginCommand.newCommand(args[0].val());
				cmd.setTabCompleter(new BukkitMCPlugin(CHDev.myself));
			} catch (Exception e) {
				throw new ConfigRuntimeException(e.getMessage(), ExceptionType.PluginInternalException, t);
			}
			if (args.length >= 2) {
				cmd.setDescription(args[1].val());
			}
			if (args.length >= 3) {
				cmd.setUsage(args[2].val());
			}
			if (args.length >= 4) {
				cmd.setPermission(args[3].val());
			}
			if (args.length >= 5) {
				cmd.setPermissionMessage(args[4].val());
			}
			if (args.length == 6) {
				if (args[5] instanceof CArray) {
					CArray aa = (CArray) args[5];
					ArrayList<String> aliases = new ArrayList<String>();
					for (String key : aa.keySet()) {
						aliases.add(aa.get(key).val());
					}
					cmd.setAliases(aliases);
				}
			}
			try {
				return new CBoolean(new BukkitMCCommandMap().register("CommandHelper", cmd), t);
			} catch (Exception e) {
				throw new ConfigRuntimeException(e.getMessage(), ExceptionType.PluginInternalException, t);
			}
		}

		public String getName() {
			return "register_command";
		}

		public Integer[] numArgs() {
			return new Integer[]{1, 2, 3, 4, 5, 6};
		}

		public String docs() {
			return "boolean {name, [description], [usage], [permission], [nopermmsg], [aliases]}"
					+ " Attempts to register a command, and returns true if succesful."
					+ " If false, the prefix 'CommandHelper:' was added to the front of the command.";
		}
	}
	
	@api
	public static class unregister_command extends DFun {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.PluginInternalException};
		}

		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
			try {
				MCCommandMap map = new BukkitMCCommandMap();
				MCCommand cmd = map.getCommand(args[0].val());
				//This didn't do what I thought it would, will change later
				return new CBoolean(cmd.unregister(map), t);
			} catch (Exception e) {
				throw new ConfigRuntimeException(e.getMessage(), ExceptionType.PluginInternalException, t);
			}
		}

		public String getName() {
			return "unregister_command";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "boolean {command} Attempts to unregister a command, returning true if successful.";
		}
	}
	
	@api
	public static class claim_tabcompleter extends DFun {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.PluginInternalException, ExceptionType.NullPointerException,
					ExceptionType.CastException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			try {
				MCCommandMap map = new BukkitMCCommandMap();
				MCCommand cmd = map.getCommand(args[0].val());
				if (cmd == null) {
					throw new ConfigRuntimeException("Command not found", ExceptionType.NullPointerException, t);
				}
				if (cmd.isPluginCommand()) {
					cmd.asPluginCommand().setTabCompleter(new BukkitMCPlugin(CHDev.myself));
				} else {
					throw new ConfigRuntimeException("The given command was not plugin-assignable",
							ExceptionType.CastException, t);
				}
			} catch (Exception e) {
				throw new ConfigRuntimeException(e.getMessage(), ExceptionType.PluginInternalException, t);
			}
			return new CVoid(t);
		}

		public String getName() {
			return "claim_tabcompleter";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "void {command} Makes CommandHelper the TabCompleter for this command";
		}
	}
}
