package com.mm.common.battle.buffer;

import java.util.List;

import com.mm.common.battle.damage.Damage;
import com.mm.common.battle.unit.Unit;

public class Buffer {
	//使用者
	private Unit source;
	//被使用者
	private Unit target;
	//造成的伤害
	protected List<Damage> damages;
	//执行帧数
	private int execFrame;
	//剩下的回合数
	protected int remainTurn;
	//最大的回合数
	private int maxTurn;
}
