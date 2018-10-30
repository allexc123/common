package com.mm.common.battle.damage;

import com.mm.common.battle.template.EffectParam;
import com.mm.common.battle.unit.Unit;

public interface IDamageCalculator {
	
	public Damage calcDamage(EffectParam param, Unit source, Unit target);
	
}
