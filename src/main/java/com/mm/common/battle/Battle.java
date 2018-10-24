package com.mm.common.battle;

import java.util.List;

public class Battle {

	protected int battleId; //战斗Id
	
	protected long startTime; //战斗开始时间
	protected int curFrame; //战斗当前帧数
	
	protected AttackOrder lastAttackOrder; //上一个攻击的指令
	protected List<AttackOrder> orderQueue;	//当前使用的指令集
	
	protected List<AttackOrder> historyAttackOrder;//历史指令集
	
	
	/**
	 * 执行战斗
	 * 一个回合的攻击
	 */
	public void execOrders() {
		
	}
	public int getCurFrame() {
		return this.curFrame;
	}
}
