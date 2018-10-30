package com.mm.common.battle.damage.calcutor;

import com.mm.common.battle.damage.Damage;
import com.mm.common.battle.damage.IDamageCalculator;
import com.mm.common.battle.damage.ValueType;
import com.mm.common.battle.skill.DamageParam;
import com.mm.common.battle.template.EffectParam;
import com.mm.common.battle.template.EffectType;
import com.mm.common.battle.unit.Unit;

public class PhysicalDamage implements IDamageCalculator {

	public Damage calcDamage(Unit source, Unit target, DamageParam param) {
		
		ValueType type = param.getValueType();
		float value = param.getValue();
		
		
		Damage damage = new Damage();
		damage.setTarget(target);
		damage.setTypel(EffectType.DAMAGE);
		damage.setDamageValue((long)value);
		damage.setOrder(1);

		return damage;
		
	}


}
