package com.blox.test.screen;

import java.util.Date;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.ITextDrawer;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.Game;

class SplashScreen extends Screen {
	private TurnMazeGame game;
	private boolean loading;
	private ITextDrawer textDrawer;
	private Thread initThread = new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				loading = false;
				startMazeGame();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});

	SplashScreen(TurnMazeGame game) {
		this.game = game;
	}

	private void startMazeGame() {
		game.showMazeScreen();
	}
	
	@Override
	public void render() {
		super.render();
		textDrawer.draw(loading ? "loading..." : new Date().toString(), 20, 780);
	}
	
	@Override
	public void init() {
		textDrawer = Game.getTextDrawer();
		super.init();
		super.registerInputListener(this);
		super.registerDrawable(new Background("screen1.jpg"), 1);
		loading = true;
		initThread.start();
	}
	
	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		if (!loading)
			startMazeGame();
		return super.touchUp(x, y, pointer, button);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
			initThread.interrupt();
			Game.exit();
			return true;
		}
		return super.keyDown(keycode);
	}
}