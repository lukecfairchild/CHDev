package com.zeoldcraft.dev.abstraction;

import com.laytonsmith.abstraction.MCEntity;

public interface MCTNT extends MCEntity {

	// "Well this is simple, and exactly the same as the bukkit interface"
	// That is correct, since the official API does not exist yet, we base
	// interface methods off bukkit's interface, it also makes it very easy
	// to come from programming other plugins
	public int getFuseTicks();

	// "Woah, wtf is an MCEntity?"
	// For the sake of portability, things that aren't in the java API
	// (ie, things that would be different on a different server)
	// need to use other CommandHelper interfaces.
	public MCEntity getSource();

	public void setFuseTicks(int ticks);

	// Ok, now to make code that does something!
	
	// Click here to go to implementation
	
	// Click here to go to the java file
}
