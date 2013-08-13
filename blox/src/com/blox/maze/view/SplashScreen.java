package com.blox.maze.view;

import com.blox.framework.v0.util.Game;
import com.blox.maze.model.Background;
import com.blox.maze.util.R;

public class SplashScreen extends MazeScreenBase {
	public SplashScreen(MazeGame game) {
		super(game);
	}
	
	@Override
	public void init() {
		super.init();
		registerDrawable(new Background(R.animations.Background.second), 1);
	}
	
	@Override
	public void activated() {
		load();
		super.activated();
	}
	
	@Override
	public void render() {
		super.render();
		Game.getTextDrawer().draw("Loading...", Game.getScreenWidth() / 2 - 30, Game.getScreenHeight() / 2 - 10);
	}
	
	private void load() {
		Thread loadThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				game.showMainMenu();
			}
		});
		
		loadThread.start();
	}
}
