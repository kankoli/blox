package com.blox.maze;

import java.util.HashMap;
import java.util.Map;

import com.blox.ScaledShapeRenderer;
import com.blox.maze.designer.DownWallToggler;
import com.blox.maze.designer.IWallToggler;
import com.blox.maze.designer.LeftWallToggler;
import com.blox.maze.designer.RightWallToggler;
import com.blox.maze.designer.UpWallToggler;

public class MazeRoom {
	// region static

	public static final int WallUp = 0x01;
	public static final int WallRight = 0x02;
	public static final int WallDown = 0x04;
	public static final int WallLeft = 0x08;

	private static Map<Integer, IWallToggler> togglers = new HashMap<Integer, IWallToggler>();

	static {
		togglers.put(WallUp, new UpWallToggler());
		togglers.put(WallRight, new RightWallToggler());
		togglers.put(WallDown, new DownWallToggler());
		togglers.put(WallLeft, new LeftWallToggler());
	}

	private static IWallToggler getToggler(float x, float y) {
		if (y > x && y > -x) {
			return togglers.get(WallUp);
		} else if (y < x && y > -x) {
			return togglers.get(WallRight);
		} else if (y < x && y < -x) {
			return togglers.get(WallDown);
		}
		return togglers.get(WallLeft);
	}

	// endregion static

	private Maze maze;
	private int wallData;
	private int rowIndex;
	private int colIndex;

	private float ox;
	private float oy;

	MazeRoom(Maze maze, int rowIndex, int colIndex) {
		this.maze = maze;
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;

		this.ox = maze.getColumnCount() / 2f;
		this.oy = maze.getRowCount() / 2f;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColumnIndex() {
		return colIndex;
	}

	public boolean isVisible(int wall) {
		return (wallData & wall) != 0;
	}

	public MazeRoom show(int wall) {
		wallData |= wall;
		return this;
	}

	public MazeRoom hide(int wall) {
		wallData &= ~wall;
		return this;
	}

	public MazeRoom setWallData(int data) {
		this.wallData = data;
		return this;
	}

	public int getWallData() {
		return wallData;
	}

	public void toggleWall(float x, float y) {
		IWallToggler toggler = getToggler(x, y);
		toggler.toggle(this);
	}

	public MazeRoom getUpRoom() {
		return maze.getRoom(rowIndex + 1, colIndex);
	}

	public MazeRoom getRightRoom() {
		return maze.getRoom(rowIndex, colIndex + 1);
	}

	public MazeRoom getDownRoom() {
		return maze.getRoom(rowIndex - 1, colIndex);
	}

	public MazeRoom getLeftRoom() {
		return maze.getRoom(rowIndex, colIndex - 1);
	}

	public void draw(ScaledShapeRenderer renderer, float sinr, float cosr,
			float tx, float ty) {
		drawUpWall(renderer, sinr, cosr, tx, ty);
		drawRightWall(renderer, sinr, cosr, tx, ty);
		drawDownWall(renderer, sinr, cosr, tx, ty);
		drawLeftWall(renderer, sinr, cosr, tx, ty);
	}

	private void drawUpWall(ScaledShapeRenderer renderer, float sinr,
			float cosr, float tx, float ty) {
		if (isVisible(WallUp))
			drawWall(renderer, sinr, cosr, tx, ty, colIndex, rowIndex + 1,
					colIndex + 1, rowIndex + 1);
	}

	private void drawRightWall(ScaledShapeRenderer renderer, float sinr,
			float cosr, float tx, float ty) {
		if (isVisible(WallRight))
			drawWall(renderer, sinr, cosr, tx, ty, colIndex + 1, rowIndex,
					colIndex + 1, rowIndex + 1);
	}

	private void drawDownWall(ScaledShapeRenderer renderer, float sinr,
			float cosr, float tx, float ty) {
		if (isVisible(WallDown))
			drawWall(renderer, sinr, cosr, tx, ty, colIndex, rowIndex,
					colIndex + 1, rowIndex);
	}

	private void drawLeftWall(ScaledShapeRenderer renderer, float sinr,
			float cosr, float tx, float ty) {
		if (isVisible(WallLeft))
			drawWall(renderer, sinr, cosr, tx, ty, colIndex, rowIndex,
					colIndex, rowIndex + 1);
	}

	private void drawWall(ScaledShapeRenderer renderer, float sinr, float cosr,
			float tx, float ty, float p0x, float p0y, float p1x, float p1y) {

		float x0 = cosr * (p0x - ox) - sinr * (p0y - oy) + ox;
		float y0 = sinr * (p0x - ox) + cosr * (p0y - oy) + oy;
		float x1 = cosr * (p1x - ox) - sinr * (p1y - oy) + ox;
		float y1 = sinr * (p1x - ox) + cosr * (p1y - oy) + oy;

		renderer.line(x0 + tx, y0 + ty, x1 + tx, y1 + ty);
	}
}
