package com.mm.common.battle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.mm.action.ActionQueue;
import com.mm.common.battle.action.CheckBattleAction;
import com.mm.common.battle.damage.Damage;
import com.mm.common.battle.unit.Unit;
import com.mm.common.battle.unit.UnitCollection;

public class Battle {
	
	/**
	 * 战斗配置
	 */
	protected static final int fqcy = 400;
	
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
	
	protected float battleSpeed; //战斗速度
	
	public Battle() {
		this.battleId = BattleContext.idGenerator();
		this.actionQueue = new ActionQueue(BattleContext.executor());

		this.orderQueue = new ArrayList<AttackOrder>();	
		this.attackOrders = new ArrayList<AttackOrder>();
		this.historyAttackOrder = new ArrayList<AttackOrder>();
		
		this.uc = new UnitCollection(this);
		this.battleSpeed = 1.0f;
		this.curFrame = 0;
	}
	
	public void addUnit(Unit unit) {
		this.uc.addUnit(unit);
	}
	
	private void attaclClean() {
		this.attackOrders.clear();
	}
	
	public void initialize() {
		
		this.state = BattleState.LOADING;
		
		BattleContext.add(this);
		checkState(checkTime());
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
		
		checkState(checkTime());
	}
	/**
	 * 战斗开始
	 */
	public void start() {
		if(this.state == BattleState.START) {
			this.state = BattleState.FIGHTING;
			
		}
		checkState(checkTime());
	}

	/**
	 * 执行战斗
	 * 一个回合的攻击
	 */
	public void execOrders() {
		if(this.state == BattleState.FIGHTING) {
			
			uc.releaseOrders(curFrame);
			
			//清理上一回合数据
			attaclClean();
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
			this.sendAttackOrder();
			
		}
		checkState(checkTime());
	}
	/**
	 * 战斗结束
	 */
	public void over() {
		if(this.state == BattleState.OVER) {
			this.state = BattleState.STOP;
			System.out.println("Battle Over");
			List<Unit> allUnits = uc.getAllUnits();
			for(Unit unit : allUnits) {
				System.out.println(unit.getId()+ " hp " + unit.getHp());
			}
		}
		checkState(checkTime());
	}
	/**
	 * 战斗停止
	 */
	public void stop() {
		if(this.state == BattleState.STOP) {
			BattleContext.remove(this.battleId);
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
	
	public void checkState(int delay) {
		new CheckBattleAction(this, delay).start();;;
	}
	public int checkTime() {
		return fqcy;
	}
	
	
	
	public int getBattleId() {
		return battleId;
	}

	public void sendAttackOrder() {
		for(AttackOrder order : attackOrders) {
			System.out.println("socrce " + order.getSourec().getId() + "  Frame = " + order.getExecFrame() + " readyTime = "+ order.getSourec().getReadyTime());
			System.out.println(order.getSourec().getId() + "对 ");
			for(Damage damage : order.getDamages()) {
				System.out.println(damage.getTarget().getId() + " 造成了 " + damage.getDamageValue() + "伤害 当前 HP " + damage.getTarget().getHp());
			}
			System.out.println("---------------------------");
		}
	}

	public ActionQueue getActionQueue() {
		return actionQueue;
	}
	
	
	public BattleState getState() {
		return state;
	}


	public void setState(BattleState state) {
		this.state = state;
	}
	
	
	public float getBattleSpeed() {
		return battleSpeed;
	}

	public void setBattleSpeed(float battleSpeed) {
		this.battleSpeed = battleSpeed;
	}
	
	public UnitCollection unitCollection() {
		return this.uc;
	}
	public boolean isOver() {
		if(uc.isOver()) {
			this.state =  BattleState.OVER;
			this.checkState(getCurFrame());
			return true;
		}
		return false;
	}


}
