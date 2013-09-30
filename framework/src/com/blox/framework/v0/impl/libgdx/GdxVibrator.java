package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.blox.framework.v0.IVibrator;
import com.blox.framework.v0.impl.Settings;

final class GdxVibrator implements IVibrator {
	GdxVibrator() {

	}

	@Override
	public void vibrate(long... pattern) {
		if (Settings.isVibrationOn()) {
			if (pattern.length == 1)
				Gdx.input.vibrate((int)pattern[0]);
			else
				Gdx.input.vibrate(pattern, -1);
		}
	}

	@Override
	public void stop() {
		Gdx.input.cancelVibrate();
	}
}