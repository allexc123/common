package com.mm.common.battle.action;

import com.maoyb.action.DelayAction;
import com.mm.common.battle.Battle;
import com.mm.common.battle.BattleState;
import com.mm.common.battle.FrameUtil;

public class CheckBattleAction  extends DelayAction{

	private Battle battle;
	private long lastTime;
	public CheckBattleAction(Battle battle, long delay) {
		super(battle.getActionQueue(), delay);
		this.battle = battle;
		this.lastTime = System.currentTimeMillis();
		
	}

	@Override
	public void execute() {
		long time = System.currentTimeMillis();
		battle.addFrame(FrameUtil.timeToFrame((int)(time -lastTime) , battle.getBattleSpeed()));
		
		BattleState state = battle.getState();
		if(state != null) {
			state.exec(battle);
		}
		
	}

}
