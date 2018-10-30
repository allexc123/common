package com.mm.common.battle.skill;

import com.mm.common.battle.damage.DamageType;
import com.mm.common.battle.damage.ValueType;

public class DamageParam {
	private DamageType damageType;
	private ValueType valueType;
	private float value;
	
	public DamageType getDamageType() {
		return damageType;
	}
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	public ValueType getValueType() {
		return valueType;
	}
	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	
}
