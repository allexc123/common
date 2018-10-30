package com.mm.common.battle.damage;

import com.mm.common.battle.skill.DamageParam;
import com.mm.common.battle.template.EffectParam;
import com.mm.common.battle.unit.Unit;

public interface IDamageCalculator {
	
	public Damage calcDamage(Unit source, Unit target,DamageParam param);
	
}
