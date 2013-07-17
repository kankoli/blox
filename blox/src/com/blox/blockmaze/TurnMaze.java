package com.blox.blockmaze;

import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.ToolBox;

class TurnMaze extends Screen {
	private Block[][] blocks;

	private float tx;
	private float ty;

	float rotateStart;

	private int cols;
	private int rows;
	private int[][] data;

	private int mazeWidth;
	private int mazeHeight;

	TurnMaze(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
	}	

	@Override
	public void init() {
		super.init();
		
		data = new int[][] { { 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1 } };

		cols = data.length;
		rows = data[0].length;
		mazeWidth = cols * Block.blockWidth;
		mazeHeight = rows * Block.blockHeight;

		tx = (ToolBox.screenWidth - mazeWidth) / 2;
		ty = (ToolBox.screenHeight - mazeHeight) / 2;

		rotation.origin.x = tx + mazeWidth / 2;
		rotation.origin.y = ty + mazeHeight / 2;

		blocks = new Block[cols][rows];

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (data[i][j] == 1) {
					blocks[i][j] = new Block(tx + i * Block.blockWidth, ty + j * Block.blockHeight);
					blocks[i][j].setRotation(rotation);
					
					super.registerDrawable(blocks[i][j], 1);
				}
			}
		}
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		rotateStart = x;
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		rotation.rotation.z += (rotateStart - x) / 10f;
		rotateStart = x;
		return false;
	}
}
