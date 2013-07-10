package com.blox.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.blox.ScaledShapeRenderer;
import com.blox.World;
import com.blox.entity.Maze2D;
import com.blox.entity.Room2D;
import com.blox.framework.InputDetector;

public class MazeGame extends Game implements GestureListener, InputDetector {
	private SpriteBatch batch;
	private Maze2D maze;
	private ScaledShapeRenderer shapeRenderer;

	private final int boxSize = 1;
	private final int mazeWidth = 12;
	private final int mazeHeight = 12;
	Maze mazeArr;

	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ScaledShapeRenderer(new ShapeRenderer());
		shapeRenderer.setColor(0, 0xaa, 0);

		Gdx.input.setInputProcessor(new InputMultiplexer(new GestureDetector(
				this), this));

		World.width = Gdx.graphics.getWidth();
		World.height = Gdx.graphics.getHeight();

		World.scale = 1 / 28f;

		maze = new Maze2D(Room2D.rect(1, 1, 40, 40), Room2D.rect(1, 41, 40, 80));
		maze.addRoom(Room2D.rect(41, 1, 80, 40));

		mazeArr = new Maze(mazeWidth, mazeHeight, boxSize);
		for (int i = 0; i < mazeWidth; i++) {
			for (int j = 0; j < mazeWidth; j++) {
				mazeArr.close(i, j);
			}
		}

		mazeArr.setTranslation((World.scale(World.width) - mazeWidth) / 2,
				(World.scale(World.height) - mazeHeight) / 2);
		mazeArr.open(0, 0);
		mazeArr.open(0, 1);
		mazeArr.open(0, 2);
		mazeArr.open(1, 0);
		mazeArr.open(2, 0);
		mazeArr.open(2, 2);
		mazeArr.open(2, 1);
		mazeArr.open(1, 2);
		mazeArr.open(1, 3);
		mazeArr.open(1, 4);
		mazeArr.open(2, 4);
		mazeArr.open(3, 4);
		mazeArr.open(4, 4);
		mazeArr.open(5, 4);
		mazeArr.open(5, 5);
		mazeArr.open(5, 6);

		mazeArr.open(4, 7);
		mazeArr.open(5, 7);
		mazeArr.open(6, 7);

		mazeArr.open(4, 8);
		mazeArr.close(5, 8);
		mazeArr.open(6, 8);

		mazeArr.open(4, 9);
		mazeArr.open(5, 9);
		mazeArr.open(6, 9);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();

		// maze.render(shapeRenderer);
		mazeArr.draw(shapeRenderer);

		batch.end();
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		logMethodName();
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		logMethodName();
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		logMethodName();
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		logMethodName();
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		logMethodName();
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		logMethodName();
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		logMethodName();
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		logMethodName();
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		logMethodName();
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		logMethodName();
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		//logMethodName();
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		logMethodName();
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		logMethodName();
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		logMethodName();
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		logMethodName();
		return false;
	}

	private static void logMethodName() {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		System.out.println(ste[2].getMethodName());
	}
}
