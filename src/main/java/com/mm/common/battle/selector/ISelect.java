package com.mm.common.battle.selector;

import java.util.List;

import com.mm.common.battle.unit.Unit;
import com.mm.common.battle.unit.UnitCollection;

public interface ISelect {

	public List<Unit> select(Unit source, UnitCollection uc, SelectSide side, String params);
}
