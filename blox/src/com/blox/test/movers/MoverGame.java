package com.blox.test.movers;

import com.blox.framework.v0.impl.BaseGame;

public class MoverGame extends BaseGame {
	@Override
	public void init() {
		super.init();
		MoverScreen sc = new MoverScreen();
		sc.init();
		setScreen(sc);
	}
	
	@Override
	public float getVirtualHeight() {
		return 800;
	}

	@Override
	public float getVirtualWidth() {
		return 450;
	}
}