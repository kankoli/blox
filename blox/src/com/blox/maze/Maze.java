package com.blox.maze;

import com.badlogic.gdx.graphics.Color;
import com.blox.ScaledShapeRenderer;

public class Maze {
	private float tx;
	private float ty;
	private int[][] mazeArr;
	private int boxSize;

	public Maze(int width, int height, int boxSize) {
		mazeArr = new int[width][height];
		for (int i = 0; i < width; i++) {
			mazeArr[i] = new int[height];
			for (int j = 0; j < height; j++) {
				mazeArr[i][j] = 1;
			}
		}

		this.boxSize = boxSize;
	}

	public void set(int i, int j, int x) {
		mazeArr[i][j] = x > 0 ? 1 : 0;
	}

	public void open(int i, int j) {
		mazeArr[i][j] = 1;
	}

	public void close(int i, int j) {
		mazeArr[i][j] = 0;
	}

	public void setTranslation(float tx, float ty) {
		this.tx = tx;
		this.ty = ty;
	}

	public int getRightMaze(int i, int j) {
		if (j >= mazeArr[i].length - 1) {
			return -1;
		}

		return mazeArr[i][j + 1];
	}

	public int getLeftMaze(int i, int j) {
		if (j == 0) {
			return -1;
		}

		return mazeArr[i][j - 1];
	}

	public int getUpMaze(int i, int j) {
		if (i >= mazeArr.length - 1) {
			return -1;
		}

		return mazeArr[i + 1][j];
	}

	public int getDownMaze(int i, int j) {
		if (i == 0) {
			return -1;
		}

		return mazeArr[i - 1][j];
	}

	public void draw(ScaledShapeRenderer renderer) {
		
		renderer.rect(tx, ty, boxSize * mazeArr[0].length, boxSize* mazeArr.length);
		
		for (int i = 0; i < mazeArr.length; i++) {
			for (int j = 0; j < mazeArr[i].length; j++) {
				if (mazeArr[i][j] == 0) {
					renderer.rect(i * boxSize + tx, j * boxSize + ty, boxSize,
							boxSize, Color.GREEN);
					continue;
				}
			}
		}
	}
}
