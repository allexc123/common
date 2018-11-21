package com.mm.common.battle.template;

import java.util.List;

import com.maoyb.template.AbstractTempate;
import com.mm.common.battle.damage.DamageType;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.selector.SelectType;

public class SkillTemplate extends AbstractTempate {
	private String name;
	
	//目标选择类型
	private SelectType selectType;
	//已方、敌方
	private SelectSide selectSide;
	//冷却时间
	private int coolDown;

	//技能产生的bufferId
	private int bufferId;
	
	//执行的帧数
	private int execFrameNum;
	//技能效果 （顺序执行）
	private List<EffectParam> effectParams;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SelectType getSelectType() {
		return selectType;
	}

	public void setSelectType(SelectType selectType) {
		this.selectType = selectType;
	}

	public SelectSide getSelectSide() {
		return selectSide;
	}

	public void setSelectSide(SelectSide selectSide) {
		this.selectSide = selectSide;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

	public int getBufferId() {
		return bufferId;
	}

	public void setBufferId(int bufferId) {
		this.bufferId = bufferId;
	}

	public List<EffectParam> getEffectParams() {
		return effectParams;
	}

	public void setEffectParams(List<EffectParam> effectParams) {
		this.effectParams = effectParams;
	}

	public int getExecFrameNum() {
		return execFrameNum;
	}

	public void setExecFrameNum(int execFrameNum) {
		this.execFrameNum = execFrameNum;
	}
	
}
