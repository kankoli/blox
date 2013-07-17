package com.blox;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blox.blockmaze.TurnMazeGame;
import com.blox.framework.v0.impl.libgdx.ILibGdxGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "blox";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 800;

		ILibGdxGame gdxGame = new TurnMazeGame();
		new LwjglApplication(gdxGame.getApplicationListener(), cfg);
	}
}
