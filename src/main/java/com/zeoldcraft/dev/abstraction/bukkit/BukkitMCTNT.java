package com.zeoldcraft.dev.abstraction.bukkit;

import org.bukkit.entity.TNTPrimed;

import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.bukkit.BukkitConvertor;
import com.laytonsmith.abstraction.bukkit.BukkitMCEntity;
import com.zeoldcraft.dev.abstraction.MCTNT;

public class BukkitMCTNT extends BukkitMCEntity implements MCTNT {

	// Here in the Bukkit-specific class, we are free to
	// refrence things like org.bukkit.entity.TNTPrimed,
	// and we need to start out by making such a reference
	TNTPrimed tnt;
	
	// Now we make a constructor that "fills" the current instance
	// of the class with a particular TNTPrimed entity
	public BukkitMCTNT(TNTPrimed e) {
		super(e);
		this.tnt = e;
	}

	// And then we add the methods from our MCTNT interface
	// Your CH function will only ever see MCTNT.getFuseTicks(),
	// but behind the scenes, if CH is running as a Bukkit plugin,
	// it will return the return of TNTPrimed.getFuseTicks().
	public int getFuseTicks() {
		// Every java program knows what an int is, so we can freely return that here
		return tnt.getFuseTicks();
	}

	public void setFuseTicks(int ticks) {
		// CH can pass primitives like the int as well, so this is simple too
		tnt.setFuseTicks(ticks);
	}

	
	public MCEntity getSource() {
		// when changing types, it's important to null check so we don't
		// throw an error if the new object doesn't exist
		if (tnt.getSource() == null) {
			return null;
		}
		// One type of return, however this forces the return to use the
		// BukkitMCEntity wrapper, to which only the MCEntity interface
		// will apply, limiting what you can do with this return.
		// 
		// return new BukkitMCEntity(tnt.getSource());
		//
		// What would be better is returning the entity using its own
		// wrapper with the following:
		return BukkitConvertor.BukkitGetCorrectEntity(tnt.getSource());
	}

	// Now your interface exists and has a bukkit implementation
	
	// Click here to finally see how to write a function
	
	// Click here to see the java form of this class
}
