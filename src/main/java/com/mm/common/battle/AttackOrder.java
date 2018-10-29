package com.mm.common.battle;

import java.util.List;

import com.mm.common.battle.damage.Damage;
import com.mm.common.battle.unit.Unit;

public class AttackOrder {

	private Battle battle;
	private Unit sourec;
	private List<Unit> targets;
	private List<Damage> damages;
	
	private int frameNum;
	private int execFrame;
	
	private int skillId;
	
	
	public AttackOrder(Unit source, List<Unit> targets) {
		this.battle = source.getBattle();
		this.sourec = source;
		this.targets = targets;
		
	}
	

	public int getExecFrame() {
		return execFrame;
	}
	

	public Unit getSourec() {
		return sourec;
	}

	public void setSourec(Unit sourec) {
		this.sourec = sourec;
	}

	public List<Unit> getTargets() {
		return targets;
	}

	public void setTargets(List<Unit> targets) {
		this.targets = targets;
	}

	public List<Damage> getDamages() {
		return damages;
	}

	public void setDamages(List<Damage> damages) {
		this.damages = damages;
	}

	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}

	public void setExecFrame(int execFrame) {
		this.execFrame = execFrame;
	}

	public boolean canExec(int curFrame) {
		
		if(curFrame < getExecFrame()) {
			return false;
		}
		return true;
	}
	
	public void exec(int curFrame) {
		
		this.setExecFrame(curFrame);
		boolean result = exec();
		
		
	
		int nextFrame = this.getExecFrame() + this.frameNum;
		sourec.setReadyTime(nextFrame);
		sourec.addCurTurn();
		//执行失败
		if(result)return;
		
		battle.setLastAttackOrder(this);
		battle.addAttackOrder(this);

	}
	
	private boolean exec() {
		calcFrameNum();
		//行动前buff
		
		//使用技能
		execSkill();
		//行动后buff
		
		return true;
	}
	private void execSkill() {
		//获取技能模板
	}
	/**
	 * 生成buff
	 */
	private void createBuff() {
		
	}
	
	private void calcFrameNum() {
		this.frameNum = 5;
	}
}
