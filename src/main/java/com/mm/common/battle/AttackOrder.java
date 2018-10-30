package com.mm.common.battle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mm.common.battle.damage.Damage;
import com.mm.common.battle.damage.DamageFactory;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.selector.SelectType;
import com.mm.common.battle.selector.Selector;
import com.mm.common.battle.template.EffectParam;
import com.mm.common.battle.template.SkillTemplate;
import com.mm.common.battle.unit.Unit;
import com.mm.modular.Inject;
import com.mm.modular.Prototype;
import com.mm.template.Templates;

@Prototype
public class AttackOrder {
	
	@Inject
	private Templates temolates;

	private Battle battle;
	private Unit source;
	private List<Unit> targets;
	private List<Damage> damages;
	
	private int frameNum;
	private int execFrame;
	
	private int skillId;
	
	
	public AttackOrder(Unit source, List<Unit> targets, int skillId) {
		this.battle = source.getBattle();
		this.source = source;
		this.targets = targets;
		this.damages = new ArrayList<Damage>();
		this.skillId = skillId;
	}
	

	public int getExecFrame() {
		return execFrame;
	}
	

	public Unit getSourec() {
		return source;
	}

	public void setSourec(Unit sourec) {
		this.source = sourec;
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
		source.setReadyTime(nextFrame);
		source.addCurTurn();
		//执行失败
		if(!result)return;
		
		battle.setLastAttackOrder(this);
		battle.addAttackOrder(this);

	}
	
	private boolean exec() {
		calcFrameNum();
		//行动前buff
		
		if(checkTargesState()) {
			SkillTemplate skillTemplate = temolates.getTemplates(skillId, SkillTemplate.class);
			if(skillTemplate == null) {
				System.out.println("技能不存在" + skillId);
			}
			SelectType selectType = skillTemplate.getSelectType();
			SelectSide selectSide = skillTemplate.getSelectSide();
			//根据技能配置获取目标
			targets = Selector.select(selectType, source, battle.unitCollection(), selectSide, "");
		}
		
		//使用技能
		execSkill();
		//行动后buff
		
		return true;
	}
	private void execSkill() {
		//获取技能模板
		SkillTemplate skillTemplate = temolates.getTemplates(this.skillId, SkillTemplate.class);
	
		List<EffectParam> effectParams = skillTemplate.getEffectParams();
		for(EffectParam param : effectParams) {
			for(Unit target : targets) {
				Damage damage = DamageFactory.calc(param, source, target);
				this.damages.add(damage);
				target.takeDamage(damage, battle.unitCollection());
				
			}
		}
	}
	private boolean checkTargesState() {
		if(this.targets == null) {
			return true;
		}
		Iterator<Unit> it = targets.iterator();
		while(it.hasNext()) {
			Unit unit = it.next();
			if(unit.isDead()) {
				it.remove();
			}
		}
		if(targets.isEmpty()) {
			return true;
		}
		return false;
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
