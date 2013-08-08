package com.blox.maze.view;

import com.blox.framework.v0.impl.Screen;

public abstract class MazeScreenBase extends Screen {
	protected MazeGame game;
	
	MazeScreenBase(MazeGame game) {
		this.game = game;
	}	
}