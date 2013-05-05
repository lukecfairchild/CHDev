package com.zeoldcraft.dev.abstraction;

import com.laytonsmith.abstraction.MCPlugin;

public interface MCPluginCommand extends MCCommand {
	
	public MCPlugin getPlugin();
	
	public MCPlugin getExecutor();
	
	public MCPlugin getTabCompleter();
	
	public void setExecutor(MCPlugin plugin);
	
	public void setTabCompleter(MCPlugin plugin);
}
