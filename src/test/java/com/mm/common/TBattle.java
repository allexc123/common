package com.mm.common;

import com.mm.common.battle.Battle;
import com.mm.common.battle.BattleSide;
import com.mm.common.battle.skill.Skill;
import com.mm.common.battle.unit.Unit;

public class TBattle {

	public static void main(String[] args) {
		Battle battle = new Battle();
		
		Unit unit1 = new Unit(1, BattleSide.ATTACKER, battle);
		Skill skill1 = new Skill();
		skill1.setSkillId(101);
		skill1.setLastCommonCD(50);
		
		unit1.setDefaultSkill(skill1);
		
		Unit unit2 = new Unit(2, BattleSide.DEFENDER, battle);
		Skill skill2 = new Skill();
		skill2.setSkillId(102);
		skill2.setLastCommonCD(10);
		
		unit2.setDefaultSkill(skill2);
		
		battle.addUnit(unit1);
		battle.addUnit(unit2);
		
		battle.initialize();

	}

}
