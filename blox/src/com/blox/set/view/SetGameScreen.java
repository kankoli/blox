package com.blox.set.view;

import com.blox.framework.v0.impl.Screen;

public abstract class SetGameScreen extends Screen {
	private boolean hasInited;
	
	protected SetGame game;

	protected SetGameScreen(SetGame game) {
		this.game = game;
	}

	@Override
	public void init() {
		if (hasInited)
			return;
		super.init();
		onInit();
		hasInited = true;
	}
	
	protected abstract void onInit();
}
