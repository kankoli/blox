package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.blox.framework.v0.IDeltaTime;

class GdxDeltaTime implements IDeltaTime {
	GdxDeltaTime() {

	}

	@Override
	public float getDeltaTime() {
		return Gdx.graphics.getDeltaTime();
	}
}