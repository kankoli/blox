package com.blox;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blox.game.BlockGame;
import com.blox.maze.MazeGame;
import com.blox.test.mummy.MummyGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "blox";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 800;

		//new LwjglApplication(new MazeGame(new MazeSaveHandler()), cfg);
		new LwjglApplication(new BlockGame(), cfg);
		// new LwjglApplication(new MummyGame(), cfg);
	}
}
