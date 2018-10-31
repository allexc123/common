package com.mm.common.battle.damage;

import com.mm.common.battle.damage.calcutor.MagicDamage;
import com.mm.common.battle.damage.calcutor.PhysicalDamage;
import com.mm.tools.Codec;
import com.mm.tools.EnumCodec;

public enum DamageType {
	@Codec(1) PHYSICAL(PhysicalDamage.class),
	@Codec(2) MAGIC(MagicDamage.class),
	;
	
	public int code;
	public Class<?> clazz;
	private DamageType(Class<?> clazz) {
		this.code = EnumCodec.encode(this);
		this.clazz = clazz;
	}
	
	public static DamageType valueOf(int code) {
		return EnumCodec.decode(DamageType.class, code);
	}
}
