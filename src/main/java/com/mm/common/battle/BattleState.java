package com.mm.common.battle;

public enum BattleState {
	//加载
	LOADING{

		@Override
		public void exec(Battle battle) {
			battle.loadOver();
			
		}}, 
	//开始
	START {
		@Override
		public void exec(Battle battle) {
			battle.start();
			
		}
	}, 
	//战斗
	FIGHTING {
		@Override
		public void exec(Battle battle) {
			battle.execOrders();
			
		}
	},
	//结束
	OVER {
		@Override
		public void exec(Battle battle) {
			battle.over();
			
		}
	},//结束
	STOP{
		@Override
		public void exec(Battle battle) {
			battle.stop();
			
		}
		
	}
	;
	public abstract void exec(Battle battle);
}
