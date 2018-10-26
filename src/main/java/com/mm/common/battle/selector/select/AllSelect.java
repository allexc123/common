package com.mm.common.battle.selector.select;

import java.util.List;

import com.mm.common.battle.BattleSide;
import com.mm.common.battle.selector.ISelect;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.unit.Unit;
import com.mm.common.battle.unit.UnitCollection;

public class AllSelect implements ISelect{

	public List<Unit> select(Unit source, UnitCollection uc, SelectSide side, String params) {
		BattleSide battleSide = source.getSide();
		if(side == SelectSide.SELF) {
			return uc.getAliveUnit(battleSide);
		}
		return uc.getAliveUnit(battleSide == BattleSide.ATTACKER ? BattleSide.DEFENDER : BattleSide.ATTACKER);
	}

}
