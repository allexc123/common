package com.mm.common.battle.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mm.common.battle.AttackOrder;
import com.mm.common.battle.Battle;
import com.mm.common.battle.BattleSide;

public class UnitCollection {
	
	private Battle battle;
	
	private List<Unit> allUnits; // 所有的战斗单位
	
	private Map<Long, Unit> aliveUnits; //存活列表
	private Map<Long, Unit> deathUnits; //死亡列表
	
	private Map<Long, Unit> attackUnits; //攻击方
	private Map<Long, Unit> defendeUnits; //防御方
	
	public UnitCollection(Battle battle) {
		this.battle = battle;
		
		this.allUnits = new ArrayList<Unit>();
		this.aliveUnits = new HashMap<Long, Unit>();
		this.deathUnits = new HashMap<Long, Unit>();
		this.attackUnits = new HashMap<Long, Unit>();
		this.defendeUnits = new HashMap<Long, Unit>();

	}
	public void addUnit(Unit unit) {
		BattleSide side = unit.getSide();
		if(side == null) {
			return;
		}
		Map<Long, Unit> units = side == BattleSide.ATTACKER ? attackUnits : defendeUnits;
		if(units == null) {
			return;
		}
		if(units.containsKey(unit.getId())) {
			return;
		}
		units.put(unit.getId(), unit);
		
		allUnits.add(unit);
	}
	public void walkTime(int interval) {
		
	}
	
	public void releaseOrders(int curFrame) {
		for(Unit unit : aliveUnits.values()) {
			AttackOrder order = unit.releaseOrder(curFrame);
		}
	}
	
	/**
	 * 从死亡列表移到存活列表中
	 * @param unit
	 */
	public void transferUnitAlive(Unit unit) {
		BattleSide side = unit.getSide();
		if(side == null) {
			return;
		}
		deathUnits.remove(unit.getId());
		aliveUnits.put(unit.getId(), unit);
	}
	/**
	 * 加入死亡列表
	 * @param unit
	 */
	public void transferUnitDeath(Unit unit) { 
		BattleSide side = unit.getSide();
		if(side == null) {
			return;
		}
		aliveUnits.remove(unit.getId());
		deathUnits.put(unit.getId(), unit);
	}

}
