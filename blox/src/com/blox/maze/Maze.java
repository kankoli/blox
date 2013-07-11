package com.blox.maze;

import com.badlogic.gdx.Gdx;
import com.blox.ScaledShapeRenderer;

public class Maze {
	private final static float turnSpeed = (float) Math.PI;
	private final static float epsilon = (float) Math.PI / 30;
	private final static float minTurn = (float) Math.PI / 6;

	private float tx;
	private float ty;

	private float rotation;
	private float targetRotation;
	private float tempRotation;

	private boolean designMode;

	private int rows;
	private int cols;
	private MazeRoom[][] rooms;

	public Maze(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		initRooms();
	}

	public int getRowCount() {
		return rows;
	}

	public int getColumnCount() {
		return cols;
	}
	
	private void initRooms() {
		rooms = new MazeRoom[rows][cols];
		for (int i = 0; i < rows; i++) {
			rooms[i] = new MazeRoom[cols];
			for (int j = 0; j < cols; j++) {
				rooms[i][j] = new MazeRoom(this, i, j);
			}
		}
	}

	private boolean isRotating() {
		return targetRotation != rotation;
	}

	public void tempRotate(float theta) {
		if (designMode)
			return;

		if (!isRotating())
			tempRotation += theta;
	}

	public void setDesignMode(boolean designMode) {
		this.designMode = designMode;
	}

	public boolean isDesignMode() {
		return designMode;
	}

	public void turn() {
		if (designMode)
			return;

		if (Math.abs(tempRotation) > minTurn)
			targetRotation = rotation
					+ ((float) (tempRotation > 0 ? Math.PI : -Math.PI) / 2);
		tempRotation = 0;
	}

	public void importWallData(int[][] roomData) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				rooms[i][j].setWallData(roomData[i][j]);
			}
		}
	}

	public int[][] exportWallData() {
		int[][] data = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = rooms[i][j].getWallData();
			}
		}
		return data;
	}

	public void setTranslation(float x, float y) {
		tx = x;
		ty = y;
	}

	public MazeRoom getRoom(int row, int col) {
		if (row >= rows || row < 0 || col >= cols || col < 0)
			return null;
		return rooms[row][col];
	}

	public void toggleWall(float clickX, float clickY) {
		if (!designMode)
			return;

		if (clickX <= tx || clickX >= tx + cols || clickY <= ty
				|| clickY >= ty + rows)
			return;

		float x = clickX - tx;
		float y = clickY - ty;
		int i = (int) x;
		int j = (int) y;

		x = x - i - 0.5f;
		y = y - j - 0.5f;

		rooms[j][i].toggleWall(x, y);
	}

	public void draw(ScaledShapeRenderer renderer) {

		float tmpDr = targetRotation - rotation;

		if (Math.abs(tmpDr) > epsilon) {
			if (tmpDr > 0) {
				rotation += turnSpeed * Gdx.graphics.getDeltaTime();
			} else {
				rotation -= turnSpeed * Gdx.graphics.getDeltaTime();
			}
		} else {
			rotation = targetRotation;
		}

		renderer.setColor(0, 192, 0);
		drawWalls(renderer, rotation);

		if (tempRotation != 0) {
			renderer.setColor(127);
			drawWalls(renderer, rotation + tempRotation);
		}
	}

	private void drawWalls(ScaledShapeRenderer renderer, float rotation) {
		float sinr = (float) Math.sin(rotation);
		float cosr = (float) Math.cos(rotation);

		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				rooms[i][j].draw(renderer, sinr, cosr, tx, ty);
			}
		}
	}
}
