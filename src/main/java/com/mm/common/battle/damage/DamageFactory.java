package com.mm.common.battle.damage;

import java.util.EnumMap;
import java.util.Map;

import com.mm.common.battle.unit.Unit;

public class DamageFactory {
	private static Map<DamageType, IDamageCalculator> damageCache = new EnumMap<DamageType, IDamageCalculator>(DamageType.class);
	static {
		for(DamageType type : DamageType.values()) {
			try {
				damageCache.put(type, (IDamageCalculator) type.clazz.getConstructor().newInstance());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static double calc(DamageType type, Unit source, Unit target) {
		return damageCache.get(type).calcDamage(source, target);
	}
}
