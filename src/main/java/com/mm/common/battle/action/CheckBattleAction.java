package com.mm.common.battle.action;

import com.mm.action.DelayAction;
import com.mm.common.battle.Battle;
import com.mm.common.battle.BattleState;

public class CheckBattleAction  extends DelayAction{

	private Battle battle;
	public CheckBattleAction(Battle battle, long delay) {
		super(battle.getActionQueue(), delay);
		this.battle = battle;
	}

	@Override
	public void execute() {
		
		BattleState state = battle.getState();
		switch (state) {
		case LOADING:
			
			break;
		case START:
			
			break;
		default:
			break;
		}
		
	}

}
