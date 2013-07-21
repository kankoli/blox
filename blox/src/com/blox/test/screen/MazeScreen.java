package com.blox.test.screen;

import java.util.Iterator;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

class MazeScreen extends Screen {
	private static final float maxSpeed = 100;

	private float deltaRotation;

	private TurnMazeGame game;
	private Maze maze;
	private Vector rotateStart;

	MazeScreen(TurnMazeGame game) {
		this.game = game;
		this.maze = new Maze();
		this.rotateStart = new Vector();
	}
	
	@Override
	public void init() {
		super.init();

		maze.init();

		super.registerDrawable(new Background("screen2.jpg"), 1);
		
		Iterator<Block> blocks = maze.getBlocks();
		while (blocks.hasNext())
			super.registerDrawable(blocks.next(), 2);

		super.registerDrawable(maze, 3);
		super.registerInputListener(this);
	}

	@Override
	public void update() {
		super.update();

		maze.rotate(deltaRotation);
		deltaRotation = 0;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		rotateStart.x = x;
		rotateStart.y = y;
		return false;
	}
	
	@Override
	public boolean scrolled(float amount) {
		deltaRotation = amount * 10;
		return super.scrolled(amount);
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		float dx = ((x - rotateStart.x) /  Game.world.screenWidth) * 90;
		float dy = ((y - rotateStart.y) /  Game.world.screenWidth) * 90;

		if (y > Game.world.scale(Game.world.screenHeight) / 2)
			dx = -dx;
		if (x <  Game.world.scale(Game.world.screenWidth) / 2)
			dy = -dy;

		dx = ensureSpeed(dx);
		dy = ensureSpeed(dy);

		deltaRotation = dx + dy;

		rotateStart.x = x;
		rotateStart.y = y;
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
			game.showSplashScreen();
			return true;
		}
		return super.keyDown(keycode);
	}

	private float ensureSpeed(float dr) {
		if (Math.abs(dr) > maxSpeed)
			return dr > 0 ? maxSpeed : -maxSpeed;
		return dr;
	}
}