package com.zeoldcraft.dev.abstraction.events;

import java.util.List;

import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.core.events.BindableEvent;
import com.zeoldcraft.dev.abstraction.MCCommand;

public interface MCTabCompleteEvent extends BindableEvent {

	public MCCommandSender getCommandSender();
	
	public MCCommand getCommand();
	
	public String getAlias();
	
	public String[] getArguments();
	
	public List<String> getCompletions();
	
	public void setCompletions(List<String> completions);
}
