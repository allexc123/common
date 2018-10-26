package com.mm.common;

import com.mm.common.battle.Battle;
import com.mm.common.battle.BattleSide;
import com.mm.common.battle.unit.Unit;

public class TBattle {

	public static void main(String[] args) {
		Battle battle = new Battle();
		
		Unit unit1 = new Unit(1, BattleSide.ATTACKER, battle);
		Unit unit2 = new Unit(2, BattleSide.DEFENDER, battle);
		
		battle.addUnit(unit1);
		battle.addUnit(unit2);
		
		battle.initialize();

	}

}
