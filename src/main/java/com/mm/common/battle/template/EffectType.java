package com.mm.common.battle.template;

import com.maoyb.tools.Codec;
import com.maoyb.tools.EnumCodec;

public enum EffectType {
	//伤害
	@Codec(1) DAMAGE,
	//生成命令
	@Codec(2)GENERATEORDER,
	;
	public final int code;
	private EffectType() {
		this.code = EnumCodec.encode(this);
	}
	
	public static EffectType valueOf(int code) {
		return EnumCodec.decode(EffectType.class, code);
	}
}
