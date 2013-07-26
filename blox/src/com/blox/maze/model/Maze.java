package com.blox.maze.model;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.ICollisionListener;
import com.blox.framework.v0.util.Game;
import com.blox.maze.model.Portal.PortalType;
import com.blox.maze.view.MazeScreen;

public class Maze extends MazeGameObject {

	public static final int blockWidth = 40;
	public static final int blockHeight = 40;

	public float tx;
	public float ty;

	private List<Trap> traps;
	
	public Maze(MazeScreen screen) {
		int[][] data = new int[][] { 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 2, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 5, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		int cols = data.length;
		int rows = data[0].length;
		int mazeWidth = cols * blockWidth;
		int mazeHeight = rows * blockHeight;

		tx = (Game.world.screenWidth - mazeWidth) / 2;
		ty = (Game.world.screenHeight - mazeHeight) / 2;

		rotation.origin.x = tx + mazeWidth / 2;
		rotation.origin.y = ty + mazeHeight / 2;

		traps = new ArrayList<Trap>();
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (data[i][j] == 1) {
					Block block = new Block(tx + i * blockWidth, ty + j * blockHeight);
					block.setRotation(rotation);

					screen.registerDrawable(block, 2);
					screen.registerCollidable(block);
				} else if (data[i][j] == 2) {
					Trap trap = new Trap(tx + i * blockWidth, ty + j * blockHeight);
					trap.setRotation(rotation);

					screen.registerDrawable(trap, 2);
					screen.registerCollidable(trap);
					
					traps.add(trap);
				} else if (data[i][j] == 3) {
					Portal portal = new Portal(tx + i * blockWidth, ty + j * blockHeight, PortalType.BLUE);
					portal.setRotation(rotation);

					screen.registerDrawable(portal, 2);
					screen.registerCollidable(portal);
				} else if (data[i][j] == 4) {
					Portal portal = new Portal(tx + i * blockWidth, ty + j * blockHeight, PortalType.GREEN);
					portal.setRotation(rotation);

					screen.registerDrawable(portal, 2);
					screen.registerCollidable(portal);
				} else if (data[i][j] == 5) {
					Objective objective = new Objective(tx + i * blockWidth, ty + j * blockHeight);
					objective.setRotation(rotation);

					screen.registerDrawable(objective, 2);
					screen.registerCollidable(objective);
				}
			}
		}
	}

	public void reset() {
		rotation.rotation.z = 0;
	}
	
	public void trapsRegisterCollisionListener(ICollisionListener listener) {
		for(Trap t: traps) {
			t.registerCollisionListener(listener);
		}
	}
	
	public void trapsUnregisterCollisionListener(ICollisionListener listener) {
		for(Trap t: traps) {
			t.unregisterCollisionListener(listener);
		}
	}
}
