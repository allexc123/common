package com.mm.common.battle;

import java.util.Iterator;
import java.util.List;

import com.mm.action.ActionQueue;
import com.mm.common.battle.action.CheckBattleAction;
import com.mm.common.battle.unit.UnitCollection;

public class Battle {
	
	protected ActionQueue actionQueue;
	
	protected int battleId; //战斗Id
	
	protected long startTime; //战斗开始时间
	protected int curFrame; //战斗当前帧数
	
	protected AttackOrder lastAttackOrder; //上一个攻击的指令
	protected List<AttackOrder> orderQueue;	//当前使用的指令集
	protected List<AttackOrder> attackOrders; //待发送的指令集
	
	protected List<AttackOrder> historyAttackOrder;//历史指令集
	
	protected UnitCollection uc; //战斗单位集合
	
	protected BattleState state;
	
	
	public ActionQueue getActionQueue() {
		return actionQueue;
	}
	
	
	public BattleState getState() {
		return state;
	}


	public void setState(BattleState state) {
		this.state = state;
	}
	/**
	 * 前端加载完成
	 */
	public void loadOver(){
		if(this.state == BattleState.LOADING) {
			
			this.state = BattleState.START;
			this.startTime = System.currentTimeMillis();
			
			
			//通知前端战斗开始
		}
		
		checkState(10);
	}
	public void initialize() {
		
		this.state = BattleState.LOADING;
	}
	/**
	 * 战斗开始
	 */
	public void start() {
		if(this.state == BattleState.START) {
			this.state = BattleState.FIGHTING;
			
		}
		checkState(10);
	}

	/**
	 * 执行战斗
	 * 一个回合的攻击
	 */
	public void execOrders() {
		if(this.state == BattleState.FIGHTING) {
			
			uc.releaseOrders(curFrame);
			
			Iterator<AttackOrder> it = orderQueue.iterator();
			while(it.hasNext()) {
				AttackOrder order = it.next();
				boolean result = order.canExec(curFrame);
				if(result) {
					order.exec(curFrame);
					
					historyAttackOrder.add(order);
					it.remove();
				}
			}
		}
		checkState(10);
	}
	/**
	 * 战斗结束
	 */
	public void over() {
		if(this.state == BattleState.OVER) {
			this.state = BattleState.STOP;
		}
	}
	/**
	 * 战斗停止
	 */
	public void stop() {
		if(this.state == BattleState.STOP) {
			
		}
	}
	public int getCurFrame() {
		return this.curFrame;
	}
	
	public void addOrder(AttackOrder order) {
		int index = 0;
		for(AttackOrder temp : orderQueue) {
			if(temp.getExecFrame() > order.getExecFrame()) {
				break;
			}
			index++;
		}
		orderQueue.add(index, order);
	}
	
	public AttackOrder getLastAttackOrder() {
		return lastAttackOrder;
	}
	public void setLastAttackOrder(AttackOrder lastAttackOrder) {
		this.lastAttackOrder = lastAttackOrder;
	}
	public void addAttackOrder(AttackOrder order) {
		this.attackOrders.add(order);
	}
	public void addFrame(int frame) {
		this.curFrame += frame;
	}
	
	public void sendAttackOrder() {
		
	}
	public void checkState(int delay) {
		new CheckBattleAction(this, delay).start();;;
	}
}
