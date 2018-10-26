package com.mm.common.battle.selector.select;

import java.util.ArrayList;
import java.util.List;

import com.mm.common.battle.BattleSide;
import com.mm.common.battle.selector.ISelect;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.unit.Unit;
import com.mm.common.battle.unit.UnitCollection;

public class SelfSelect implements ISelect{

	public List<Unit> select(Unit source, UnitCollection uc, SelectSide side, String params) {
		List<Unit> result = new ArrayList<Unit>();
		result.add(source);
		return result;
	}

}
