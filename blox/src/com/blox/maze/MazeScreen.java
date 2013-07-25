package com.blox.maze;

import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.ToolBox;

public class MazeScreen extends Screen {

	private Maze maze;
	private Lokum lokum;
	
	@Override
	public void init() {
		super.init();
		
		registerDrawable(new Background(), 1);
		maze = new Maze(this);
		lokum = new Lokum(this, maze, 1, 4);
		MazeMover.instance.register(lokum);
		registerDrawable(lokum, 2);
		registerMovable(lokum);
		registerCollidable(lokum);
	}
	
	@Override
	public void update() {
		maze.update();
		super.update();
	}
}
