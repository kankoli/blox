package com.blox.test.movers;

import com.blox.framework.v0.impl.Screen;

public class MoverScreen extends Screen {
	@Override
	public void init() {
		super.init();
		MovableObj o = new MovableObj();
		registerDrawable(o, 1);
		registerMovable(o);
	}
}
