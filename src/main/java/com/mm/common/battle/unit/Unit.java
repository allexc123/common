package com.mm.common.battle.unit;

import java.util.ArrayList;
import java.util.List;

import com.mm.common.battle.AttackOrder;
import com.mm.common.battle.Battle;
import com.mm.common.battle.BattleSide;
import com.mm.common.battle.buffer.Buffer;
import com.mm.common.battle.damage.Damage;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.selector.SelectType;
import com.mm.common.battle.selector.Selector;
import com.mm.common.battle.skill.Skill;

public class Unit {
	
	private Battle battle;
	
	private long id;
	private BattleSide side;
	
	
	/** 准备时间*/
	private int readyTime;
	/**战斗的回合数*/
	private int curTurn;
	
	/**普通攻击技能*/
	private Skill defaultSkill;
	/**主动技能*/
	private List<Skill> selects;
	/**主动技能*/
	private List<Skill> passives;
	
	private List<Buffer> buffers;
	
	
	private int hp = 1000;
	
	public Unit(long id, BattleSide side, Battle battle){
		this.id = id;
		this.side = side;
		this.battle = battle;
		
		this.selects = new ArrayList<Skill>();
		this.passives = new ArrayList<Skill>();
	}
	
	public Battle getBattle() {
		return battle;
	}

	public long getId() {
		return id;
	}
	public BattleSide getSide() {
		return side;
	}
	public int getReadyTime() {
		return readyTime;
	}
	
	public int getCurTurn() {
		return curTurn;
	}

	public void addCurTurn() {
		this.curTurn++;
	}
	

	public Skill getDefaultSkill() {
		return defaultSkill;
	}

	public void setDefaultSkill(Skill defaultSkill) {
		this.defaultSkill = defaultSkill;
	}
	public List<Skill> getSelects() {
		return selects;
	}
	public void addSelect(Skill skill) {
		this.selects.add(skill);
	}
	public List<Skill> getPassives() {
		return passives;
	}
	public void addPassive(Skill skill) {
		this.passives.add(skill);
	}

	public void setReadyTime(int readyTime) {
		this.readyTime = readyTime;
	}
	
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	public boolean isDead() {
		return this.hp <= 0;
	}
	/**
	 * 目标对象是否能行动
	 * @return
	 */
	public boolean canAction(int curFrame) {
		if(curFrame < readyTime) {
			return false;
		}
		return true;
	}
	/**
	 * 获取技能
	 * @param curFrame
	 * @return
	 */
	public Skill acquireSkill(int curFrame) {
		if(!canAction(curFrame)) {
			return null;
		}
		Skill skill = this.getDefaultSkill();
		if((skill.getLastUse() + skill.getLastCommonCD()) >= curFrame) {
			return null;
		}
		return skill;
	}
	
	public void takeDamage(Damage damage, UnitCollection uc) {
		if(this.isDead()) {
			return;
		}
		//处理伤害 加深、减伤 （如护盾等） 
		int oldValue = this.hp;
		
		int damageValue = (int) damage.getDamageValue() - 8;
		
		int curValue = oldValue - damageValue;
		curValue = curValue < 0? 0 : curValue;
		this.setHp(curValue);
		
		damage.setDamageValue(damageValue);
		
		if(this.isDead()) {
			uc.transferUnitDeath(this);
		}
	}
	
	
	
	
	
}
