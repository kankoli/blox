package com.blox.maze.view;

import com.badlogic.gdx.Input.Keys;
import com.blox.maze.controller.MazeController;
import com.blox.maze.model.Background;
import com.blox.maze.model.GameOptions;
import com.blox.maze.util.R;

public class MazeScreen extends MazeScreenBase {
	private MazeController controller;

	public MazeScreen(MazeGame game) {
		super(game);
	}

	@Override
	public void init() {
		super.init();
		GameOptions.currentLevel = 1;
		registerDrawable(new Background(R.animations.Background.zeroth), 1);
		registerInputListener(this);
		controller = new MazeController(this);
	}

	@Override
	public void update() {
		controller.work();
		super.update();
	}
   
	@Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK || keycode == Keys.ESCAPE)
    		game.showMainMenu();
    	return super.keyDown(keycode);
    }
}