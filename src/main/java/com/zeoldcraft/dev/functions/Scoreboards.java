package com.zeoldcraft.dev.functions;

import java.util.Set;

import com.laytonsmith.PureUtilities.StringUtils;
import com.laytonsmith.abstraction.MCObjective;
import com.laytonsmith.abstraction.MCOfflinePlayer;
import com.laytonsmith.abstraction.MCScoreboard;
import com.laytonsmith.abstraction.MCServer;
import com.laytonsmith.abstraction.MCTeam;
import com.laytonsmith.abstraction.enums.MCAutoCriteria;
import com.laytonsmith.abstraction.enums.MCDisplaySlot;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.functions.Exceptions;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;

/**
 * 
 * @author jb_aero
 */
public class Scoreboards {
	public static String docs() {
		return "A class of functions for manipulating the server scoreboard.";
	}
	
	public static abstract class SBFunction extends AbstractFunction {

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
	
	@api
	public static class get_objectives extends SBFunction {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCScoreboard s = Static.getServer().getMainScoreboard();
			Set<MCObjective> os;
			if (args.length == 1) {
				os = s.getObjectivesByCriteria(args[0].val());
			} else {
				os = s.getObjectives();
			}
			CArray ret = new CArray(t);
			for (MCObjective o : os) {
				CArray obj = CArray.GetAssociativeArray(t);
				obj.set("name", new CString(o.getName(), t), t);
				obj.set("displayName", new CString(o.getDisplayName(), t), t);
				Construct slot = new CNull(t);
				if (o.getDisplaySlot() != null) {
					slot = new CString(o.getDisplaySlot().name(), t);
				}
				obj.set("slot", slot, t);
				obj.set("modifiable", new CBoolean(o.isModifiable(), t), t);
				obj.set("criteria", new CString(o.getCriteria(), t), t);
				ret.push(obj);
			}
			return ret;
		}

		public String getName() {
			return "get_objectives";
		}

		public Integer[] numArgs() {
			return new Integer[]{0, 1};
		}

		public String docs() {
			return "array {} Returns an array of arrays about the objectives in the current scoreboard."
					+ " If criteria is given, only objectives with that criteria will be returned."
					+ " The arrays contain the keys name, displayname, slot, modifiable, and criteria.";
		}
	}
	
	@api
	public static class get_teams extends SBFunction {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			Set<MCTeam> ts = Static.getServer().getMainScoreboard().getTeams();
			CArray ret = new CArray(t);
			for (MCTeam team : ts) {
				CArray to = CArray.GetAssociativeArray(t);
				to.set("name", new CString(team.getName(), t), t);
				to.set("displayname", new CString(team.getDisplayName(), t), t);
				to.set("prefix", new CString(team.getPrefix(), t), t);
				to.set("suffix", new CString(team.getSuffix(), t), t);
				to.set("size", new CInt(team.getSize(), t), t);
				CArray pl = new CArray(t);
				for (MCOfflinePlayer ofp : team.getPlayers()) {
					pl.push(new CString(ofp.getName(), t));
				}
				to.set("players", pl, t);
				ret.push(to);
			}
			return ret;
		}

		public String getName() {
			return "get_teams";
		}

		public Integer[] numArgs() {
			return new Integer[]{0};
		}

		public String docs() {
			return "array {} Returns an array of arrays about the teams on the current scoreboard."
					+ " The arrays contain the keys name, displayname, prefix, suffix, size, and players.";
		}
	}
	
	@api
	public static class create_objective extends SBFunction {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.FormatException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCScoreboard s = Static.getServer().getMainScoreboard();
			String name = args[0].val();
			String criteria = args[1].val();
			if (s.getObjective(name) != null) {
				return new CBoolean(false, t);
			}
			s.registerNewObjective(name, criteria);
			return new CBoolean(true, t);
		}

		public String getName() {
			return "create_objective";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "boolean {name, criteria} Adds a new objective to the scoreboard, returning true if successful."
					+ " Returns false if a team already exists with the given name."
					+ " Criteria can be anything but using " 
					+ StringUtils.Join(MCAutoCriteria.values(), ", ", ", or ")
					+ " will cause it to be managed automatically by the server.";
		}
	}
	
	@api
	public static class create_team extends SBFunction {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCScoreboard s = Static.getServer().getMainScoreboard();
			String name = args[0].val();
			if (s.getTeam(name) != null) {
				return new CBoolean(false, t);
			}
			s.registerNewTeam(name);
			return new CBoolean(true, t);
		}

		public String getName() {
			return "create_team";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "boolean {name} Adds a new team to the scoreboard, returning true if successful."
					+ " Returns false if a team already exists with the given name.";
		}
	}
	
	@api
	public static class set_objective_display extends SBFunction {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.FormatException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCScoreboard s = Static.getServer().getMainScoreboard();
			MCObjective o = s.getObjective(args[0].val());
			if (o == null) {
				throw new Exceptions.FormatException("No objective by that name exists.", t);
			}
			MCDisplaySlot slot;
			if (args[1] instanceof CNull) {
				slot = null;
			} else {
				try {
					slot = MCDisplaySlot.valueOf(args[1].val().toUpperCase());
				} catch (IllegalArgumentException iae) {
					throw new Exceptions.FormatException("Unknown DisplaySlot, " + args[1].val(), t);
				}
			}
			o.setDisplaySlot(slot);
			return new CVoid(t);
		}

		public String getName() {
			return "set_objective_display";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "void {objectivename, slot} Sets the location to display an objective."
					+ " Null removes it from all displays. Slot can be one of: "
					+ StringUtils.Join(MCDisplaySlot.values(), ", ", ", or ")
					+ ". An exception is thrown if the objective does not exist.";
		}
	}
	
	@api
	public static class add_team_player extends SBFunction {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.FormatException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCServer s = Static.getServer();
			MCTeam team = s.getMainScoreboard().getTeam(args[0].val());
			if (team == null) {
				throw new Exceptions.FormatException("No team by that name exists.", t);
			}
			team.addPlayer(s.getOfflinePlayer(args[1].val()));
			return new CVoid(t);
		}

		public String getName() {
			return "add_team_player";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "void {teamname, player} Adds a player to a team, given the team exists."
					+ " Offline players can be added, so the name must be exact."
					+ " The player will be removed from any other team on the same scoreboard.";
		}
	}
	
	@api
	public static class remove_objective extends SBFunction {

		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.NullPointerException, ExceptionType.FormatException};
		}

		public Construct exec(Target t, Environment environment,
				Construct... args) throws ConfigRuntimeException {
			MCObjective o = Static.getServer().getMainScoreboard().getObjective(args[0].val());
			try {
				o.unregister();
			} catch (NullPointerException npe) {
				throw new ConfigRuntimeException("The objective does not exist.", ExceptionType.NullPointerException, t);
			} catch (IllegalStateException ise) {
				throw new Exceptions.FormatException("The objective has already been unregistered.", t);
			}
			return new CVoid(t);
		}

		public String getName() {
			return "remove_objective";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "void {objectivename} Unregisters an objective from the scoreboard.";
		}
	}
}
