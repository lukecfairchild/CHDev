package com.zeoldcraft.dev.abstraction;

import com.laytonsmith.abstraction.MCProjectile;

public interface MCFishHook extends MCProjectile {
	public double getBiteChance();
	public void setBiteChance(double chance);
}
