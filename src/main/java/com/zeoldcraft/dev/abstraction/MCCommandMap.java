package com.zeoldcraft.dev.abstraction;

import java.util.List;

public interface MCCommandMap {

	public void clearCommands();
	
	public MCCommand getCommand(String name);
	
	public List<MCCommand> getCommands();
	
	public boolean register(String fallback, MCCommand cmd);
	
	public boolean register(String label, String fallback, MCCommand cmd);
	
	public boolean unregister(MCCommand cmd);
}
