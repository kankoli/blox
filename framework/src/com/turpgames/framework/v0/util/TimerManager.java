package com.turpgames.framework.v0.util;

import com.turpgames.framework.v0.impl.Manager;

public final class TimerManager extends Manager<Timer> {
	public static final TimerManager instance = new TimerManager();
	
	private TimerManager() {
		
	}
	
	@Override
	protected void execute(Timer timer) {
		timer.update();
	}	
}