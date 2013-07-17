package com.blox;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blox.blockmaze.TurnMazeGame;
import com.blox.test.screen.MultiScreenGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "blox";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 800;

		// new LwjglApplication(new MazeGame(new MazeSaveHandler()), cfg);
		new LwjglApplication(new MultiScreenGame(), cfg);
		// new LwjglApplication(new MummyGame(), cfg);
	}
}
