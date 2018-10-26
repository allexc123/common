package com.mm.common.battle.selector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mm.common.battle.unit.Unit;
import com.mm.common.battle.unit.UnitCollection;

public class Selector {
	
	private static Map<SelectType, ISelect> selectCache = new HashMap<SelectType, ISelect>();
	
	static {
		for(SelectType type : SelectType.values()) {
			try {
				
				selectCache.put(type, (ISelect) type.clazz.getConstructor().newInstance());
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static List<Unit> select(SelectType selectType, Unit source, UnitCollection uc, SelectSide side, String params){
		return selectCache.get(selectType).select(source, uc, side, params);
	}
}
