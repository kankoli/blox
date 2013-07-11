package com.blox.maze.designer;

import com.blox.maze.MazeRoom;

public class UpWallToggler extends WallToggler {
	public UpWallToggler() {
		super(MazeRoom.WallUp, MazeRoom.WallDown);
	}
	
	@Override
	protected MazeRoom getAdjacentRoom(MazeRoom room) {
		return room.getUpRoom();
	}
}