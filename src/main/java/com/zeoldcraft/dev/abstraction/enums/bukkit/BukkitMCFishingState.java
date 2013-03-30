package com.zeoldcraft.dev.abstraction.enums.bukkit;

import org.bukkit.event.player.PlayerFishEvent;

import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.abstraction.enums.EnumConvertor;
import com.laytonsmith.annotations.abstractionenum;
import com.zeoldcraft.dev.abstraction.enums.MCFishingState;

@abstractionenum(
		implementation= Implementation.Type.BUKKIT,
		forAbstractEnum=MCFishingState.class,
		forConcreteEnum=PlayerFishEvent.State.class
)
public class BukkitMCFishingState extends EnumConvertor<MCFishingState, PlayerFishEvent.State> {
	
	private static BukkitMCFishingState instance;
	
	public static BukkitMCFishingState getConvertor(){
		if(instance == null){
			instance = new BukkitMCFishingState();
		}
		return instance;
	}
}
