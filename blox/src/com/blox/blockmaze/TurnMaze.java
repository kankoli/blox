package com.blox.blockmaze;

import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.ToolBox;

class TurnMaze extends Screen {
	private float rotateStart;

	@Override
	public void init() {
		super.init();
		
		int[][] data = new int[][] { 
				{ 1, 1, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 1 }, 
				{ 1, 1, 1, 1, 1, 1 } };

		int cols = data.length;
		int rows = data[0].length;
		int mazeWidth = cols * Block.blockWidth;
		int mazeHeight = rows * Block.blockHeight;

		float tx = (ToolBox.screenWidth - mazeWidth) / 2;
		float ty = (ToolBox.screenHeight - mazeHeight) / 2;

		rotation.origin.x = tx + mazeWidth / 2;
		rotation.origin.y = ty + mazeHeight / 2;

		super.registerInputListener(this);
		super.registerDrawable(new Background(), 1);
		
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (data[i][j] == 1) {
					Block block = new Block(tx + i * Block.blockWidth, ty + j * Block.blockHeight);
					block.setRotation(rotation);
					
					super.registerDrawable(block, 2);
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
		rotation.rotation.z -= (rotateStart - x) / 3f;
		rotateStart = x;
		return false;
	}
}
