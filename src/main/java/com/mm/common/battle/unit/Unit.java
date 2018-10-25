package com.mm.common.battle.unit;

import com.mm.common.battle.AttackOrder;
import com.mm.common.battle.Battle;
import com.mm.common.battle.BattleSide;

public abstract class Unit {
	
	private Battle battle;
	
	private long id;
	private BattleSide side;
	
	/** 准备时间*/
	private int readyTime;
	/**战斗的回合数*/
	private int curTurn;
	
	
	public Unit(long id, BattleSide side, Battle battle){
		this.id = id;
		this.side = side;
		this.battle = battle;
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

	public void setReadyTime(int readyTime) {
		this.readyTime = readyTime;
	}
	
	public boolean canAction(int curTime) {
		if(curTime < readyTime) {
			return false;
		}
		return true;
	}
	
	public AttackOrder releaseOrder(int curFrame) {
		return null;
	}

	
	
	
}
