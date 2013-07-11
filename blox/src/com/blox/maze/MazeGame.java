package com.blox.maze;

import java.io.InputStream;
import java.io.ObjectInputStream;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.blox.ScaledShapeRenderer;
import com.blox.World;
import com.blox.framework.InputDetector;
import com.blox.maze.designer.IMazeSaveHandler;

public class MazeGame extends Game implements GestureListener, InputDetector {
	private SpriteBatch batch;
	private ScaledShapeRenderer shapeRenderer;

	private final int mazeWidth = 12;
	private final int mazeHeight = 12;
	Maze maze;

	public MazeGame() {

	}

	private IMazeSaveHandler saveHandler;

	public MazeGame(IMazeSaveHandler saveHandler) {
		this.saveHandler = saveHandler;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ScaledShapeRenderer(new ShapeRenderer());

		Gdx.input.setInputProcessor(new InputMultiplexer(new GestureDetector(
				this), this));

		World.width = Gdx.graphics.getWidth();
		World.height = Gdx.graphics.getHeight();

		World.scale = 1 / 28f;

		maze = new Maze(mazeWidth, mazeHeight);
		maze.setTranslation((World.scale(World.width) - mazeWidth) / 2,
				(World.scale(World.height) - mazeHeight) / 2);
		maze.setDesignMode(false);

		if (maze.isDesignMode()) {
			for (int i = 0; i < mazeWidth; i++) {
				for (int j = 0; j < mazeHeight; j++) {
					maze.getRoom(i, j)
						.show(MazeRoom.WallUp)
						.show(MazeRoom.WallRight)
						.show(MazeRoom.WallDown)
						.show(MazeRoom.WallLeft);
				}
			}
		} else {
			try {
				InputStream fis = Gdx.files.internal("maze.dat").read();
				ObjectInputStream ois = new ObjectInputStream(fis);
				int[][] wallData = (int[][]) ois.readObject();
				maze.importWallData(wallData);
				ois.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();

		// maze.render(shapeRenderer);
		maze.draw(shapeRenderer);

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
		maze.toggleWall(World.scale(x), World.scale(World.height - y));
		logMethodName();
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		logMethodName();
		return false;
	}

	private boolean ctrlPressed;

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.CONTROL_LEFT
				|| keycode == Input.Keys.CONTROL_RIGHT)
			ctrlPressed = false;
		logMethodName();
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.CONTROL_LEFT
				|| keycode == Input.Keys.CONTROL_RIGHT)
			ctrlPressed = true;

		if (ctrlPressed && keycode == Input.Keys.S && saveHandler != null) {
			saveHandler.save(maze.exportWallData());
		}

		logMethodName();
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		logMethodName();
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// logMethodName();
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		logMethodName();
		return false;
	}

	int rotateStart;

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		rotateStart = screenX;
		logMethodName();
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float theta = (float) ((Math.PI / 2) * ((rotateStart - screenX) / World.width));
		if (screenY > World.height / 2)
			theta = -theta;
		rotateStart = screenX;
		maze.tempRotate(theta);
		logMethodName();
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		maze.turn();
		logMethodName();
		return false;
	}

	private static void logMethodName() {
		// final StackTraceElement[] ste =
		// Thread.currentThread().getStackTrace();
		// System.out.println(ste[2].getMethodName());
	}
}
