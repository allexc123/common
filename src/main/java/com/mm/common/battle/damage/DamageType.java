package com.mm.common.battle.damage;

import com.mm.common.battle.damage.calcutor.MagicDamage;
import com.mm.common.battle.damage.calcutor.PhysicalDamage;

public enum DamageType {
	PHYSICAL(PhysicalDamage.class),
	MAGIC(MagicDamage.class),
	;
	
	public Class<?> clazz;
	private DamageType(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public static DamageType valueOf(int code) {
		return PHYSICAL;
	}
}
