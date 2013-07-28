package com.blox.test.camera;

import com.blox.framework.v0.impl.BaseGame;

public class CameraGame extends BaseGame {	
	@Override
	public void init() {
		CameraScreen screen = new CameraScreen();
		screen.init();
		setScreen(screen);
	}
}
