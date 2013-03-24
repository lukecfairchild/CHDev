package com.zeoldcraft.dev.abstraction.blocks.bukkit;

import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;

import com.laytonsmith.abstraction.blocks.MCBlockFace;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlockState;
import com.zeoldcraft.dev.abstraction.blocks.MCSkull;

public class BukkitMCSkull extends BukkitMCBlockState implements MCSkull {

	Skull s;
	public BukkitMCSkull(Skull block) {
		super(block);
		s = block;
	}
	
	public String getOwner() {
		return s.getOwner();
	}

	public MCBlockFace getRotation() {
		return MCBlockFace.valueOf(s.getRotation().name());
	}

	public String getSkullType() {
		//TODO
		return "";
	}

	public boolean hasOwner() {
		return s.hasOwner();
	}

	public boolean setOwner(String owner) {
		return s.setOwner(owner);
	}

	public void setRotation(MCBlockFace rotation) {
		BlockFace.valueOf(rotation.name());
	}

	public void setSkullType(String type) {
		// TODO
	}

}
