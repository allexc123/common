package com.mm.common.battle.selector;

import com.mm.common.battle.selector.select.AllSelect;
import com.mm.common.battle.selector.select.SelfSelect;;

/**
 * 选在类型
 * @author myb
 *
 */
public enum SelectType {
	//自己
	SELF(SelfSelect.class),
	//全部
	ALL(AllSelect.class),
	;
	
	public Class<?> clazz;
	
	private SelectType(Class<?> clazz) {
		this.clazz = clazz;
	}
}
