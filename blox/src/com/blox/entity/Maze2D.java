package com.blox.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.blox.ScaledShapeRenderer;

public class Maze2D {
	private List<Room2D> rooms;

	public Maze2D() {
		rooms = new ArrayList<Room2D>();
	}
	
	public Maze2D(Room2D... rooms) {
		this.rooms = new ArrayList<Room2D>(Arrays.asList(rooms));
	}
	
	public List<Room2D> getRooms() {
		return rooms;
	}

	public void addRoom(Room2D room) {
		rooms.add(room);
	}
	
	public void render(ScaledShapeRenderer renderer) {
		for (Room2D room : rooms)
			room.render(renderer);
	}
}
