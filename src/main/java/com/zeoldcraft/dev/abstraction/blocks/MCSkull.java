package com.zeoldcraft.dev.abstraction.blocks;

import com.laytonsmith.abstraction.blocks.MCBlockFace;
import com.laytonsmith.abstraction.blocks.MCBlockState;

public interface MCSkull extends MCBlockState {

	public String getOwner();
	
	public MCBlockFace getRotation();
	
	public String getSkullType();
	
	public boolean hasOwner();
	
	public boolean setOwner(String owner);
	
	public void setRotation(MCBlockFace rotation);
	
	public void setSkullType(String type);
}
