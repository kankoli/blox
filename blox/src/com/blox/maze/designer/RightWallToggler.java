package com.blox.maze.designer;

import com.blox.maze.MazeRoom;

public class RightWallToggler extends WallToggler {
	public RightWallToggler() {
		super(MazeRoom.WallRight, MazeRoom.WallLeft);
	}
	
	@Override
	protected MazeRoom getAdjacentRoom(MazeRoom room) {
		return room.getRightRoom();
	}
}