package com.blox.maze.designer;

import com.blox.maze.MazeRoom;

public class LeftWallToggler extends WallToggler {
	public LeftWallToggler() {
		super(MazeRoom.WallLeft, MazeRoom.WallRight);
	}
	
	@Override
	protected MazeRoom getAdjacentRoom(MazeRoom room) {
		return room.getLeftRoom();
	}
}