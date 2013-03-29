package com.zeoldcraft.dev.abstraction.events;

import java.util.Collection;

import com.laytonsmith.core.events.BindableEvent;

public interface MCTabCompleteEvent extends BindableEvent {
	public String getChatMessage();
	public String getLastToken();
	public Collection<String> getTabCompletions();
}
