package com.zeoldcraft.dev.abstraction.blocks;

import com.laytonsmith.abstraction.blocks.MCBlockState;

public interface MCCommandBlock extends MCBlockState {
	
	public String getCommand();
	
	public String getName();
	
	public void setCommand(String command);
	
	public void setName(String name);
}
