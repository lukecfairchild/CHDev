package com.zeoldcraft.dev.abstraction.events;

import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.core.events.BindableEvent;

public interface MCPortalEnterEvent extends BindableEvent {
	public MCEntity getEntity();
	public MCLocation getLocation();
}
