package com.mm.common.battle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.mm.action.ActionExecutor;

/**
 * 战斗集合
 * @author myb
 *
 */
public class BattleContext {
	private static final AtomicInteger idGenerator = new AtomicInteger();
	private static ActionExecutor executor;
	private static Map<Integer, Battle> battleCache = new ConcurrentHashMap<Integer, Battle>();
	
	public static ActionExecutor executor() {
		if(executor == null) {
			synchronized (BattleContext.class) {
				if(executor == null) {
					executor = new ActionExecutor(0, Runtime.getRuntime().availableProcessors() * 2 +1, "Battle");
				}
			}
		}
		return executor;
	}
	public static int idGenerator() {
		return idGenerator.getAndIncrement();
	}
	public static void add(Battle battle) {
		battleCache.put(battle.getBattleId(), battle);
	}
	
	public static void remove(int battleId) {
		battleCache.remove(battleId);
	}
	
	public static void stop() {
		if(executor != null) {
			executor.shutdown();
		}
	}
}
