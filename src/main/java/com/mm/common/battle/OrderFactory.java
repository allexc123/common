package com.mm.common.battle;

import java.util.List;

import com.maoyb.modular.Bean;
import com.maoyb.modular.Inject;
import com.maoyb.template.Templates;
import com.mm.common.battle.selector.SelectSide;
import com.mm.common.battle.selector.SelectType;
import com.mm.common.battle.selector.Selector;
import com.mm.common.battle.template.SkillTemplate;
import com.mm.common.battle.unit.Unit;
import com.mm.common.battle.unit.UnitCollection;


@Bean
public class OrderFactory {
	
	@Inject
	private Templates templates;
	
	public AttackOrder createOrder(int skillId, Unit source, UnitCollection uc) {
		
		SkillTemplate skillTemplate = templates.getTemplates(skillId, SkillTemplate.class);
		if(skillTemplate == null) {
			System.out.println("技能不存在" + skillId);
		}
		SelectType selectType = skillTemplate.getSelectType();
		SelectSide selectSide = skillTemplate.getSelectSide();
		//根据技能配置获取目标
		List<Unit> targets = Selector.select(selectType, source, uc, selectSide, "");
		AttackOrder order = new AttackOrder(source, targets, skillId);
		return order;
	}
}
