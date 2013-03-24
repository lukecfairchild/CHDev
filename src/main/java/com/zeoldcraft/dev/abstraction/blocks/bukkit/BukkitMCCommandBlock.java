package com.zeoldcraft.dev.abstraction.blocks.bukkit;

import org.bukkit.block.CommandBlock;

import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlockState;
import com.zeoldcraft.dev.abstraction.blocks.MCCommandBlock;

public class BukkitMCCommandBlock extends BukkitMCBlockState implements
		MCCommandBlock {

	CommandBlock cb;
	public BukkitMCCommandBlock(CommandBlock block) {
		super(block);
		cb = block;
	}
	
	public String getCommand() {
		return cb.getCommand();
	}

	public String getName() {
		return cb.getName();
	}

	public void setCommand(String command) {
		cb.setCommand(command);
	}

	public void setName(String name) {
		cb.setName(name);
	}

}
