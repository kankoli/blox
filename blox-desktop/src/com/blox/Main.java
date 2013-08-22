package com.blox;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blox.framework.v0.impl.libgdx.GdxGame;
import com.blox.maze.view.MazeGame;
import com.blox.test.movers.MoverGame;
import com.blox.set.view.CardGame;
import com.blox.set.view.SetGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "blox";
		cfg.useGL20 = false;

		float w = 9;
		float h = 16;
		float x = 50;

		cfg.width = (int) (x * w);
		cfg.height = (int) (x * h);

		// new LwjglApplication(new GdxGame(new MoverGame()), cfg);

		new LwjglApplication(new GdxGame(new SetGame()), cfg);
	}
}
