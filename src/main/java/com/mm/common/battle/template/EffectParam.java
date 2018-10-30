package com.mm.common.battle.template;

import com.mm.common.battle.damage.DamageType;
import com.mm.common.battle.damage.ValueType;

public class EffectParam {

	//伤害类型
	private DamageType damageType;
	//百分比还是数字
	private ValueType type;
	//值
	private float value;
	//概率
	private int probability;
	
	
	public DamageType getDamageType() {
		return damageType;
	}
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	public ValueType getType() {
		return type;
	}
	public void setType(ValueType type) {
		this.type = type;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public int getProbability() {
		return probability;
	}
	public void setProbability(int probability) {
		this.probability = probability;
	}
	
	
}
