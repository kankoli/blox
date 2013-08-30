package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.blox.framework.v0.IVibrator;
import com.blox.framework.v0.util.Game;

final class GdxVibrator implements IVibrator {
	GdxVibrator() {

	}

	@Override
	public void vibrate(int millis) {
		if (Game.isVibrationOn())
			Gdx.input.vibrate(millis);
	}

	@Override
	public void stop() {
		Gdx.input.cancelVibrate();
	}
}