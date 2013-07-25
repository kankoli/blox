package com.blox.maze.model;

import com.blox.framework.v0.util.Game;
import com.blox.maze.model.Portal.PortalType;
import com.blox.maze.view.MazeScreen;

public class Maze extends MazeGameObject {

	public static final int blockWidth = 40;
	public static final int blockHeight = 40;

	public float tx;
	public float ty;

	public Maze(MazeScreen screen) {
		int[][] data = new int[][] { 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1 },
				{ 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		int cols = data.length;
		int rows = data[0].length;
		int mazeWidth = cols * blockWidth;
		int mazeHeight = rows * blockHeight;

		tx = (Game.world.screenWidth - mazeWidth) / 2;
		ty = (Game.world.screenHeight - mazeHeight) / 2;

		rotation.origin.x = tx + mazeWidth / 2;
		rotation.origin.y = ty + mazeHeight / 2;

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (data[j][i] == 1) {
					Block block = new Block(tx + i * blockWidth, ty + j
							* blockHeight);
					block.setRotation(rotation);

					screen.registerDrawable(block, 2);
					screen.registerCollidable(block);
				} else if (data[j][i] == 2) {
					Trap trap = new Trap(tx + i * blockWidth, ty + j
							* blockHeight);
					trap.setRotation(rotation);

					screen.registerDrawable(trap, 2);
					screen.registerCollidable(trap);
				} else if (data[j][i] == 3) {
					Portal portal = new Portal(tx + i * blockWidth, ty + j
							* blockHeight, PortalType.BLUE);
					portal.setRotation(rotation);

					screen.registerDrawable(portal, 2);
					screen.registerCollidable(portal);
				} else if (data[j][i] == 4) {
					Portal portal = new Portal(tx + i * blockWidth, ty + j
							* blockHeight, PortalType.GREEN);
					portal.setRotation(rotation);

					screen.registerDrawable(portal, 2);
					screen.registerCollidable(portal);
				}
			}
		}
	}
}
