package com.mm.common.battle.skill;

public class Skill {
	private int skillId;
	private long lastUse;
	private long lastCommonCD;
	
	private int templateId;
	
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public long getLastUse() {
		return lastUse;
	}
	public void setLastUse(long lastUse) {
		this.lastUse = lastUse;
	}
	public long getLastCommonCD() {
		return lastCommonCD;
	}
	public void setLastCommonCD(long lastCommonCD) {
		this.lastCommonCD = lastCommonCD;
	}
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	
}
