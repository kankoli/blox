package com.blox.maze;

import com.blox.framework.v0.impl.Screen;

public class MazeScreen extends Screen {

	private Maze maze;
	
	@Override
	public void init() {
		super.init();
		
		maze = new Maze(this);
	}
	
	@Override
	public void update() {
		maze.update();
	}
}
