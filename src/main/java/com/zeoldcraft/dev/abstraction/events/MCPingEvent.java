package com.zeoldcraft.dev.abstraction.events;

import java.net.InetAddress;

import com.laytonsmith.core.events.BindableEvent;

/**
 * 
 * @author jb_aero
 */
public interface MCPingEvent extends BindableEvent {
	
	public InetAddress getAddress();
	
	public int getMaxPlayers();
	
	public String getMOTD();
	
	public int getNumPlayers();
	
	public void setMaxPlayers(int max);
	
	public void setMOTD(String motd);
}
