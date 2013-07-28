package com.blox.test.camera;

import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.Game;

public class CameraScreen extends Screen {
	private Man man;
	
	float sec;
	
	@Override
	public void init() {
		super.init();
		registerDrawable(man = new Man(), 1);
		registerInputListener(this);
	}
	
	@Override
	public void render() {
		super.render();
		sec += Game.getDeltaTime();
		Game.getTextDrawer().draw(sec + "", 20, 180);
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		System.out.println("uýý");
		man.restart();
		return super.touchDown(x, y, pointer, button);
	}
}
