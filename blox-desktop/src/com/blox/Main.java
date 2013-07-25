package com.blox;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blox.framework.v0.impl.libgdx.GdxGame;
import com.blox.maze.view.MazeGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "blox";
		cfg.useGL20 = false;

		float w = 9;
		float h = 16;		
		
		cfg.width = (int)(50 * w);
		cfg.height = (int)(50 * h);
		
		new LwjglApplication(new GdxGame(new MazeGame()), cfg);
	}
}
