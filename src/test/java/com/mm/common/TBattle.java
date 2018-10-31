package com.mm.common;

import java.util.ArrayList;
import java.util.List;

import com.mm.common.battle.Battle;
import com.mm.common.battle.BattleSide;
import com.mm.common.battle.damage.DamageType;
import com.mm.common.battle.damage.ValueType;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.selector.SelectType;
import com.mm.common.battle.skill.Skill;
import com.mm.common.battle.template.EffectParam;
import com.mm.common.battle.template.EffectType;
import com.mm.common.battle.template.SkillTemplate;
import com.mm.common.battle.unit.Unit;
import com.mm.core.ApplicationContext;

public class TBattle {

	public static void main(String[] args) {
		
		ApplicationContext.initialize();
		
		SkillTemplate st1 = new SkillTemplate();
		st1.setPid(101);
		st1.setName("ABC");
		st1.setSelectType(SelectType.ALL);
		st1.setSelectSide(SelectSide.ENEMY);
		st1.setCoolDown(1000);
		st1.setBufferId(0);
		st1.setExecFrameNum(12);
		
		List<EffectParam> effectParams = new ArrayList<EffectParam>();
		
		EffectParam p1= new EffectParam();
		p1.setEffectType(EffectType.DAMAGE);
		p1.setParam("1|1|100");
		effectParams.add(p1);
		
		EffectParam p2= new EffectParam();
		p2.setEffectType(EffectType.GENERATEORDER);
		p2.setParam("101");
		effectParams.add(p2);
		
		st1.setEffectParams(effectParams);
		
		ApplicationContext.addTemplate(st1);
		
		
		Battle battle = new Battle();
		
		Unit unit1 = new Unit(1, BattleSide.ATTACKER, battle);
		Skill skill1 = new Skill();
		skill1.setSkillId(101);
		skill1.setLastCommonCD(50);
		
		unit1.addSkill(skill1);
		
		Unit unit2 = new Unit(2, BattleSide.DEFENDER, battle);
		Skill skill2 = new Skill();
		skill2.setSkillId(101);
		skill2.setLastCommonCD(10);
		
		unit2.addSkill(skill2);
		
		Unit unit3 = new Unit(3, BattleSide.DEFENDER, battle);
		Skill skill3 = new Skill();
		skill3.setSkillId(101);
		skill3.setLastCommonCD(15);
		
		unit3.addSkill(skill3);
		
		battle.addUnit(unit1);
		battle.addUnit(unit2);
		battle.addUnit(unit3);
		
		battle.initialize();

	}

}
