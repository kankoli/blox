package com.blox.maze.model;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IAnimationEndListener;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionListener;
import com.blox.framework.v0.util.Game;
import com.blox.maze.view.MazeScreen;

public class Maze extends MazeGameObject {

	private static enum DataType {
		EMPTY, BLOCK, TRAP, OBJECTIVE
	}; // make BlockType and collect all in one class?

	public static final int blockWidth = 40;
	public static final int blockHeight = 40;

	public float tx;
	public float ty;

	private List<ICollidable> blocks;
	private List<ICollidable> traps;
	private List<ICollidable> objectives;
	private List<ICollidable> portalDoors;
	
	public Maze(MazeScreen screen) {
		int[][] data = new int[][] { 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 2, 0, 0, 0, 1 }, 
				{ 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
				{ 1, 0, 1, 1, 1, 1, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 3, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		int[][][] portalData = new int[][][] { { { 1, 8 }, { 2, 1 } } };

		int cols = data.length;
		int rows = data[0].length;
		int mazeWidth = cols * blockWidth;
		int mazeHeight = rows * blockHeight;

		tx = (Game.world.screenWidth - mazeWidth) / 2;
		ty = (Game.world.screenHeight - mazeHeight) / 2;

		rotation.origin.x = tx + mazeWidth / 2;
		rotation.origin.y = ty + mazeHeight / 2;

		blocks = new ArrayList<ICollidable>();
		traps = new ArrayList<ICollidable>();
		objectives = new ArrayList<ICollidable>();
		portalDoors = new ArrayList<ICollidable>();
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (data[i][j] == DataType.BLOCK.ordinal()) {
					Block block = new Block(tx + i * blockWidth, ty + j * blockHeight);
					block.setRotation(rotation);

					screen.registerDrawable(block, 2);
					
					blocks.add(block);
				} else if (data[i][j] == DataType.TRAP.ordinal()) {
					Trap trap = new Trap(tx + i * blockWidth, ty + j * blockHeight);
					trap.setRotation(rotation);

					screen.registerDrawable(trap, 2);

					traps.add(trap);
				} else if (data[i][j] == DataType.OBJECTIVE.ordinal()) {
					Objective objective = new Objective(tx + i * blockWidth, ty + j * blockHeight);
					objective.setRotation(rotation);

					screen.registerDrawable(objective, 2);
					
					objectives.add(objective);
				}
			}
		}

		for (int i = 0; i < portalData.length; i++) {
			float blueX = tx + portalData[i][0][0] * Maze.blockWidth;
			float blueY = ty + portalData[i][0][1] * Maze.blockHeight;
			float greenX = tx + portalData[i][1][0] * Maze.blockWidth;
			float greenY = ty + portalData[i][1][1] * Maze.blockHeight;
			Portal portal = new Portal(screen, blueX, blueY, greenX, greenY);
			portal.setRotation(rotation);
			
			portalDoors.addAll(portal.getDoors());
		}
	}

	public List<ICollidable> getBlocks() {
		return blocks;
	}
	
	public List<ICollidable> getTraps() {
		return traps;
	}

	public List<ICollidable> getObjectives() {
		return objectives;
	}
	
	public List<ICollidable> getPortals() {
		return portalDoors;
	}
	
	public void reset() {
		rotation.rotation.z = 0;
	}

	public void collidedPortalDoor(PortalDoor door) {
		door.getParent().enterPortal(door);
	}

	public void finishedPortal(PortalDoor door) {
		door.getParent().finishPortal();
	}

	public void registerPortalsAnimationEndListener(IAnimationEndListener listener) {
		for(ICollidable p : portalDoors) {
			((MazeGameObject)p).registerAnimationEndListener(listener);
		}
	}

	public void unregisterPortalsAnimationEndListener(IAnimationEndListener listener) {
		for(ICollidable p : portalDoors) {
			((MazeGameObject)p).unregisterAnimationEndListener(listener);
		}
	}
}
