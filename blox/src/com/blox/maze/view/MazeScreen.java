package com.blox.maze.view;

import com.blox.framework.v0.impl.Screen;
import com.blox.maze.controller.MazeController;
import com.blox.maze.model.Background;

public class MazeScreen extends Screen {

	private MazeGame game;
	private MazeController controller;

	public MazeScreen(MazeGame game) {
		this.game = game;
	}

	@Override
	public void init() {
		super.init();

		registerDrawable(new Background(), 1);

		controller = new MazeController(this);
	}

	@Override
	public void update() {
		controller.work();
		super.update();
	}

	@Override
	public void render() {
		super.render();
	}
}
