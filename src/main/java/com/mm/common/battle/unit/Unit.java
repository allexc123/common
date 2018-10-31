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
	

	/**主动技能*/
	private List<Skill> skills;

	
	private List<Buffer> buffers;
	
	
	private int hp = 1000;
	
	public Unit(long id, BattleSide side, Battle battle){
		this.id = id;
		this.side = side;
		this.battle = battle;
		
		this.skills = new ArrayList<Skill>();
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
	
	public List<Skill> getSkills() {
		return skills;
	}

	public void addSkill(Skill skill) {
		this.skills.add(skill);
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
		for(Skill skill : skills) {
			if((skill.getLastUse() + skill.getLastCommonCD()) <= curFrame) {
				return skill;
			}
		}
		return null;
	}
	public Skill getSkill(int skillId) {
		for(Skill skill : skills) {
			if(skill.getSkillId() == skillId) {
				return skill;
			}
		}
		return null;
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
