package com.mm.common.battle.damage;

import com.mm.common.battle.unit.Unit;

public interface IDamageCalculator {
	
	public double calcDamage(Unit source, Unit target);
	
}
