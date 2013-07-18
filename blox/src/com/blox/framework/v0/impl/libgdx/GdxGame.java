package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.blox.framework.v0.IGame;

public final class GdxGame {
	private GdxGame() {

	}

	public static ApplicationListener create(IGame game) {
		return new GdxApplication(game);
	}
}
