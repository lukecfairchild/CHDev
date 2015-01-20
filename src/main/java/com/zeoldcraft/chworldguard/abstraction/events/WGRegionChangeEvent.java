package com.zeoldcraft.chworldguard.abstraction.events;

import java.util.List;

import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.core.events.BindableEvent;

public interface WGRegionChangeEvent extends BindableEvent {

	public MCPlayer getPlayer();
	
	public MCLocation getFrom();
	
	public MCLocation getTo();
	
	public List<String> getFromRegions();
	
	public List<String> getToRegions();
}
