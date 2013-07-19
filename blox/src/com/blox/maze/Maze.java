package com.blox.maze;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.ToolBox;
import com.blox.maze.Portal.PortalType;
import com.blox.maze.states.MazeStateManager;

class Maze extends GameObject {
	private MazeStateManager stateManager;
	public static final int blockWidth = 40;
	public static final int blockHeight = 40;
	
	public Maze(Screen p) {
		super(p);
		
		int[][] data = new int[][] { 
				{ 1, 1, 1, 1, 1, 1 }, 
				{ 1, 2, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1 },
				{ 1, 3, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 4, 1 }, 
				{ 1, 1, 1, 1, 1, 1 } };

		int cols = data.length;
		int rows = data[0].length;
		int mazeWidth = cols * blockWidth;
		int mazeHeight = rows * blockHeight;

		float tx = (ToolBox.screenWidth - mazeWidth) / 2;
		float ty = (ToolBox.screenHeight - mazeHeight) / 2;

		rotation.origin.x = tx + mazeWidth / 2;
		rotation.origin.y = ty + mazeHeight / 2;

		parent.registerInputListener(this);
		parent.registerDrawable(new Background(), 1);
		
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (data[i][j] == 1) {
					Block block = new Block(tx + i * blockWidth, ty + j * blockHeight);
					block.setRotation(rotation);
					
					parent.registerDrawable(block, 2);
				}
				else if (data[i][j] == 2) {
					Trap trap = new Trap(tx + i * blockWidth, ty + j * blockHeight);
					trap.setRotation(rotation);
					
					parent.registerDrawable(trap, 2);
				}
				else if (data[i][j] == 3) {
					Portal portal = new Portal(tx + i * blockWidth, ty + j * blockHeight, PortalType.BLUE);
					portal.setRotation(rotation);
					
					parent.registerDrawable(portal, 2);
				}
				else if (data[i][j] == 4) {
					Portal portal = new Portal(tx + i * blockWidth, ty + j * blockHeight, PortalType.GREEN);
					portal.setRotation(rotation);
					
					parent.registerDrawable(portal, 2);
				}
			}
		}
		stateManager = new MazeStateManager(this);
	}
	
	public void update() {
		stateManager.work();
	}
}
