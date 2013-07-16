package com.blox.maze.designer;

import com.blox.maze.MazeRoom;

public class DownWallToggler extends WallToggler {
	public DownWallToggler() {
		super(MazeRoom.WallDown, MazeRoom.WallUp);
	}
	
	@Override
	protected MazeRoom getAdjacentRoom(MazeRoom room) {
		return room.getDownRoom();
	}
}