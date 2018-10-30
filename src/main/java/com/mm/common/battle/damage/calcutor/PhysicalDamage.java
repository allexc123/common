package com.mm.common.battle.damage.calcutor;

import com.mm.common.battle.damage.Damage;
import com.mm.common.battle.damage.IDamageCalculator;
import com.mm.common.battle.damage.ValueType;
import com.mm.common.battle.template.EffectParam;
import com.mm.common.battle.unit.Unit;

public class PhysicalDamage implements IDamageCalculator {

	public Damage calcDamage(EffectParam param, Unit source, Unit target) {
		
		ValueType type = param.getType();
		float value = param.getValue();
		
		
		Damage damage = new Damage();
		damage.setTarget(target);
		damage.setTypel(param.getDamageType());
		damage.setDamageValue((long)value);
		damage.setOrder(1);

		return damage;
		
	}


}
