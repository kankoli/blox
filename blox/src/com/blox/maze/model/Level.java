package com.blox.maze.model;

public class Level {
	private int level;
	private int[][] mazeData;
	private int[][][] portalData;

	private Level(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public int[][] getMazeData() {
		return mazeData;
	}

	public int[][][] getPortalData() {
		return portalData;
	}

	public static Level loadCurrentLevel() {
		Level level = new Level(GameOptions.currentLevel);

		level.mazeData = new int[][] { 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 1 }, 
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
				{ 1, 1, 1, 1, 0, 1, 0, 0, 1, 1 },
				{ 1, 0, 0, 0, 0, 1, 3, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, 
				{ 1, 0, 1, 1, 1, 1, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } 
			};

		level.portalData = new int[][][] { 
				{ { 2, 4 }, { 5, 1 } }, 
				{ { 5, 4 }, { 7, 1 } } 
			};

		return level;
	}
}
