package com.zeoldcraft.dev.abstraction.events;

import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.core.events.BindableEvent;
import com.zeoldcraft.dev.abstraction.MCFishHook;
import com.zeoldcraft.dev.abstraction.enums.MCFishingState;

public interface MCPlayerFishEvent extends BindableEvent {
	public MCEntity getCaught();
	public int getExpToDrop();
	public MCFishHook getHook();
	public MCFishingState getState();
	public void setExpToDrop(int exp);
}
