package com.blox.test.screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.blox.framework.v0.ITextDrawer;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;

public class Maze extends GameObject {
	private List<Block> blocks;
	private ITextDrawer textDrawer;

	Maze() {

	}

	void init() {
		textDrawer = Game.getTextDrawer();
		textDrawer.getFont().setColor(new Color(0, 255, 128, 192));
		blocks = new ArrayList<Block>();

		int[][] data = new int[][] { { 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 1 }, { 1, 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 0, 1 }, { 1, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1 } };

		int cols = data.length;
		int rows = data[0].length;
		int mazeWidth = cols * Block.blockWidth;
		int mazeHeight = rows * Block.blockHeight;

		float tx = (Game.world.screenWidth - mazeWidth) / 2;
		float ty = (Game.world.screenHeight - mazeHeight) / 2;

		rotation.origin.x = tx + mazeWidth / 2;
		rotation.origin.y = ty + mazeHeight / 2;

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (data[i][j] == 1) {
					Block block = new Block(tx + i * Block.blockWidth, ty + j
							* Block.blockHeight);
					block.setRotation(rotation);
					blocks.add(block);
				}
			}
		}
	}

	@Override
	public void draw() {
		textDrawer.draw(rotation.rotation.z + "", 20, Game.world.screenHeight - 20);
	}

	Iterator<Block> getBlocks() {
		return blocks.iterator();
	}

	void rotate(float dr) {
		rotation.rotation.z += dr;
		;
	}
}