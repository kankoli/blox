package com.blox.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.blox.ScaledShapeRenderer;

public class Room2D {
	private List<Wall2D> walls;

	public Room2D() {
		walls = new ArrayList<Wall2D>();
	}
	
	public Room2D(Wall2D... walls) {
		this.walls = Arrays.asList(walls);
	}

	public List<Wall2D> getWalls() {
		return walls;
	}

	public void addWall(Wall2D wall) {
		this.walls.add(wall);
	}
	
	public static Room2D rect(float x, float y, float w, float h) {
		return new Room2D(
				new Wall2D(x, 		y, 		x, 		y + h),
				new Wall2D(x, 		y, 		x + w, 	y),
				new Wall2D(x + w, 	y, 		x + w, 	y + h),
				new Wall2D(x, 		y + h, 	x + w, 	y + h)
	    );
	}
	
	public void render(ScaledShapeRenderer renderer) {
		for (Wall2D wall : walls)
			wall.render(renderer);
	}
}
