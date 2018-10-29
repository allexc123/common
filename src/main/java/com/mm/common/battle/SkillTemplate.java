package com.mm.common.battle;

import com.mm.common.battle.damage.DamageType;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.selector.SelectType;
import com.mm.template.AbstractTempate;

public class SkillTemplate extends AbstractTempate {
	private String name;
	
	//目标选择类型
	private SelectType selectType;
	//我方  敌方
	private SelectSide selectSide;
	//冷却时间
	private int coolDown;
	//伤害类型
	private DamageType damageType;
	private int value;
	//技能产生的bufferId
	private int bufferId;
	//产生buff的概率
	private int bufferRandom;
}
