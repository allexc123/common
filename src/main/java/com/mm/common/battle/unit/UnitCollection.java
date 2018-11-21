package com.mm.common.battle.unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maoyb.modular.Inject;
import com.maoyb.modular.Prototype;
import com.maoyb.template.Templates;
import com.mm.common.battle.AttackOrder;
import com.mm.common.battle.Battle;
import com.mm.common.battle.BattleSide;
import com.mm.common.battle.OrderFactory;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.selector.SelectType;
import com.mm.common.battle.selector.Selector;
import com.mm.common.battle.skill.Skill;
import com.mm.common.battle.template.SkillTemplate;

@Prototype
public class UnitCollection {
	
	@Inject
	private OrderFactory orderFactory;

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
		
		aliveUnits.put(unit.getId(), unit);
		allUnits.add(unit);
	}
	
	public List<Unit> getAliveUnit(BattleSide side) {
		return new ArrayList<Unit>(side == BattleSide.ATTACKER ? attackUnits.values() : defendeUnits.values());
	}
	
	public void walkTime(int interval) {
		
	}
	
	public void releaseOrders(int curFrame) {
		for(Unit unit : aliveUnits.values()) {

			Skill skill = unit.acquireSkill(curFrame);
			if(skill == null) {
				return;
			}
			skill.setLastUse(curFrame);
			AttackOrder order = orderFactory.createOrder(skill.getSkillId(), unit, this);
			order.setExecFrame(curFrame);
			if(order != null) {
//				
//				for(Unit u : select) {
//					System.out.println("select ====  source " + unit.getId() + " target " + u.getId());
//				}

				battle.addOrder(order);
			}
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
		Map<Long, Unit> unitMap = side == BattleSide.ATTACKER ? attackUnits : defendeUnits;
		unitMap.put(unit.getId(), unit);
		
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
		
		Map<Long, Unit> unitMap = side == BattleSide.ATTACKER ? attackUnits : defendeUnits;
		unitMap.remove(unit.getId());
		
		deathUnits.put(unit.getId(), unit);
	}
	public Collection<Unit> getAliveUnits() {
		return aliveUnits.values();
	}
	
	public List<Unit> getAllUnits() {
		return allUnits;
	}
	public boolean isOver() {
		return this.attackUnits.isEmpty() || this.defendeUnits.isEmpty();
	}

}
