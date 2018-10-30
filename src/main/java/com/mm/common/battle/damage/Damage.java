package com.mm.common.battle.damage;

import com.mm.common.battle.unit.Unit;

public class Damage {
	private Unit target;
	private DamageType type;
	private long damageValue; //伤害值
	private int order; //第几次伤害
	
	
	public Unit getTarget() {
		return target;
	}
	public void setTarget(Unit target) {
		this.target = target;
	}
	
	public DamageType getType() {
		return type;
	}
	public void setTypel(DamageType type) {
		this.type = type;
	}
	public long getDamageValue() {
		return damageValue;
	}
	public void setDamageValue(long damageValue) {
		this.damageValue = damageValue;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
