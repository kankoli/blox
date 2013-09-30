package com.blox.framework.v0.effects;

import com.blox.framework.v0.impl.Manager;

public final class EffectManager extends Manager<Effect> {
	public static final EffectManager instance = new EffectManager();
	
	private EffectManager() {
		
	}
	
	@Override
	protected void execute(Effect effect) {
		effect.update();
	}
}
