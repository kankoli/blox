package com.blox.maze.designer;

import com.blox.maze.MazeRoom;

public abstract class WallToggler implements IWallToggler {
	private int ownWall;
	private int adjacentWall;

	protected WallToggler(int ownWall, int adjacentWall) {
		this.ownWall = ownWall;
		this.adjacentWall = adjacentWall;
	}

	public void toggle(MazeRoom room) {
		MazeRoom adjacent = getAdjacentRoom(room);
		if (room.isVisible(ownWall)) {
			room.hide(ownWall);
			if (adjacent != null)
				adjacent.hide(adjacentWall);
		} else {
			room.show(ownWall);
			if (adjacent != null)
				adjacent.show(adjacentWall);
		}
	}

	protected abstract MazeRoom getAdjacentRoom(MazeRoom room);
}