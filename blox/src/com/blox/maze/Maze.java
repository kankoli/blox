package com.blox.maze;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.blox.ScaledShapeRenderer;
import com.blox.World;

public class Maze {
	public static final int WallUp = 0x01;
	public static final int WallRight = 0x02;
	public static final int WallDown = 0x04;
	public static final int WallLeft = 0x08;

	private float tx;
	private float ty;

	private float rotation;
	private float targetRotation;
	private float tempRotation;

	private boolean designMode;

	private int width;
	private int height;
	private int[][] walls;

	public Maze(int w, int h) {
		width = w;
		height = h;
		walls = new int[w][h];
	}

	public int[][] getWalls() {
		return walls;
	}

	public void tempRotate(float theta) {
		if (!isRotating())
			tempRotation += theta;
	}

	private boolean isRotating() {
		return targetRotation != rotation;
	}

	public void setDesignMode(boolean designMode) {
		this.designMode = designMode;
	}

	public void turn() {
		if (Math.abs(tempRotation) > Math.PI / 4)
			targetRotation = rotation
					+ ((float) (tempRotation > 0 ? Math.PI : -Math.PI) / 2);
		tempRotation = 0;
	}

	public void setWalls(int[][] walls) {
		this.walls = walls;
	}

	public void setTranslation(float x, float y) {
		tx = x;
		ty = y;
	}

	public void setRoomWalls(int row, int col, int roomWalls) {
		walls[row][col] = roomWalls;
	}

	public void toggleWall(float clickX, float clickY) {
		if (!designMode)
			return;

		float posX = World.scale(clickX);
		float posY = World.scale(clickY);

		if (posX <= tx || posX >= tx + width || posY <= ty
				|| posY >= ty + height)
			return;

		float x = posX - tx;
		float y = posY - ty;
		int i = (int) x;
		int j = (int) y;

		x = x - i - 0.5f;
		y = y - j - 0.5f;

		if (y > x && y > -x) {
			if ((walls[i][j] & WallUp) != 0) {
				walls[i][j] &= ~WallUp;
				if (j < height - 1)
					walls[i][j + 1] &= ~WallDown;
			} else {
				walls[i][j] |= WallUp;
				if (j < height - 1)
					walls[i][j + 1] |= WallDown;
			}
		}
		if (y < x && y > -x) {
			if ((walls[i][j] & WallRight) != 0) {
				walls[i][j] &= ~WallRight;
				if (i < width - 1)
					walls[i + 1][j] &= ~WallLeft;
			} else {
				walls[i][j] |= WallRight;
				if (i < width - 1)
					walls[i + 1][j] |= WallLeft;
			}
		}
		if (y < x && y < -x) {
			if ((walls[i][j] & WallDown) != 0) {
				walls[i][j] &= ~WallDown;
				if (j > 0)
					walls[i][j - 1] &= ~WallUp;
			} else {
				walls[i][j] |= WallDown;
				if (j > 0)
					walls[i][j - 1] |= WallUp;
			}
		}
		if (y > x && y < -x) {
			if ((walls[i][j] & WallLeft) != 0) {
				walls[i][j] &= ~WallLeft;
				if (i > 0)
					walls[i - 1][j] &= ~WallRight;
			} else {
				walls[i][j] |= WallLeft;
				if (i > 0)
					walls[i - 1][j] |= WallRight;
			}
		}

		fillLineList();
	}

	List<int[]> lines = new ArrayList<int[]>();
	private final float epsilon = (float) (20 * Math.PI) / 360;

	// saniyede
	private final float turnSpeed = (float)Math.PI;

	public void draw(ScaledShapeRenderer renderer) {
		if (lines.isEmpty()) {
			fillLineList();
		}

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

		float sinr = (float) Math.sin(rotation);
		float cosr = (float) Math.cos(rotation);

		float ox = width / 2;
		float oy = height / 2;

		renderer.setColor(0, 192, 0);
		for (int[] line : lines) {
			float x0 = cosr * (line[0] - ox) - sinr * (line[1] - oy) + ox;
			float y0 = sinr * (line[0] - ox) + cosr * (line[1] - oy) + oy;
			float x1 = cosr * (line[2] - ox) - sinr * (line[3] - oy) + ox;
			float y1 = sinr * (line[2] - ox) + cosr * (line[3] - oy) + oy;
			renderer.line(x0 + tx, y0 + ty, x1 + tx, y1 + ty);
		}

		if (tempRotation != 0) {
			sinr = (float) Math.sin(rotation + tempRotation);
			cosr = (float) Math.cos(rotation + tempRotation);

			renderer.setColor(127);
			for (int[] line : lines) {
				float x0 = cosr * (line[0] - ox) - sinr * (line[1] - oy) + ox;
				float y0 = sinr * (line[0] - ox) + cosr * (line[1] - oy) + oy;
				float x1 = cosr * (line[2] - ox) - sinr * (line[3] - oy) + ox;
				float y1 = sinr * (line[2] - ox) + cosr * (line[3] - oy) + oy;
				renderer.line(x0 + tx, y0 + ty, x1 + tx, y1 + ty);
			}
		}
	}

	private void fillLineList() {
		lines.clear();
		for (int i = 0; i < walls.length; i++) {
			for (int j = 0; j < walls[i].length; j++) {
				if ((walls[i][j] & WallUp) != 0) {
					lines.add(new int[] { i, j + 1, i + 1, j + 1 });
				}
				if ((walls[i][j] & WallRight) != 0) {
					lines.add(new int[] { i + 1, j, i + 1, j + 1 });
				}
				if ((walls[i][j] & WallDown) != 0) {
					lines.add(new int[] { i, j, i + 1, j });
				}
				if ((walls[i][j] & WallLeft) != 0) {
					lines.add(new int[] { i, j, i, j + 1 });
				}
			}
		}
	}
}
