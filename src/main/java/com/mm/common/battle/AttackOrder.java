package com.mm.common.battle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.maoyb.modular.Inject;
import com.maoyb.template.Templates;
import com.mm.common.battle.damage.Damage;
import com.mm.common.battle.damage.DamageFactory;
import com.mm.common.battle.damage.DamageType;
import com.mm.common.battle.damage.ValueType;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.selector.SelectType;
import com.mm.common.battle.selector.Selector;
import com.mm.common.battle.skill.DamageParam;
import com.mm.common.battle.skill.Skill;
import com.mm.common.battle.template.EffectParam;
import com.mm.common.battle.template.EffectType;
import com.mm.common.battle.template.SkillTemplate;
import com.mm.common.battle.unit.Unit;


public class AttackOrder {
	
	@Inject
	private Templates templates;
	@Inject
	private OrderFactory orderFactory;

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
		
		boolean result = action(curFrame);
		
		
	
		int nextFrame = this.getExecFrame() + this.frameNum;
		source.setReadyTime(nextFrame);
		source.addCurTurn();
		//执行失败
		if(!result)return;
		
		battle.setLastAttackOrder(this);
		battle.addAttackOrder(this);

	}
	
	private boolean action(int curFrame) {
		calcFrameNum();
		//行动前buff
		
		if(checkTargesState()) {
			SkillTemplate skillTemplate = templates.getTemplates(skillId, SkillTemplate.class);
			if(skillTemplate == null) {
				System.out.println("技能不存在" + skillId);
			}
			SelectType selectType = skillTemplate.getSelectType();
			SelectSide selectSide = skillTemplate.getSelectSide();
			//根据技能配置获取目标
			targets = Selector.select(selectType, source, battle.unitCollection(), selectSide, "");
		}
		
		//使用技能
		execSkill(curFrame);
		//行动后buff
		
		return true;
	}
	private void execSkill(int curFrame) {
		//获取技能模板
		SkillTemplate skillTemplate = templates.getTemplates(this.skillId, SkillTemplate.class);
	
		List<EffectParam> effectParams = skillTemplate.getEffectParams();
		for(EffectParam param : effectParams) {
			disposeEffect(param, curFrame);
		}
	}
	/**
	 * 处理各种效果
	 * @param param
	 */
	private void disposeEffect(EffectParam param, int curFrame) {
		EffectType type = param.getEffectType();
		switch (type) {
		case DAMAGE:
			DamageParam damageParam = new DamageParam();
			String paramStr = param.getParam();
			String[] s = paramStr.split("\\|");
			//第一位伤害类型
			DamageType damageType = DamageType.valueOf(Integer.parseInt(s[0]));
			//第二位 数字类型
			damageParam.setValueType(ValueType.VALUE);
			//第三位 值
			damageParam.setValue(Float.parseFloat(s[2]));
			for(Unit target : targets) {
				Damage damage = DamageFactory.calc(damageType, source, target, damageParam);
				this.damages.add(damage);
				target.takeDamage(damage, battle.unitCollection());
				
			}
			break;
			case GENERATEORDER:
				int skillId = Integer.parseInt(param.getParam());
				
				AttackOrder order = orderFactory.createOrder(skillId, source, battle.uc);
				Skill skill = source.getSkill(skillId);
				int execFrame = curFrame;
				if(skill != null) {
					SkillTemplate skillTemplate = templates.getTemplates(skillId, SkillTemplate.class);
					if(skillTemplate != null) {
						if(skill.getLastUse() + skillTemplate.getExecFrameNum() < execFrame) {
							execFrame += skillTemplate.getExecFrameNum();
						}
					}
					
				}
				order.setExecFrame(execFrame);
				battle.addWaitExecOrder(order);
				break;
		default:
			break;
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
