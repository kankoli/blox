package com.blox.maze;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;

public class Lokum extends MazeGameObject {
 	
	private Maze maze;
	
	private final class Animations {
		private static final String Lokum = "Lokum";
		private static final String LokumImagePath = "turnmaze/lokum.png";
		private static final int LokumFrameWidth = Maze.blockWidth;
		private static final int LokumFrameHeight = Maze.blockHeight;
		private static final float LokumFrameDuration = 0.1f;
	}
		
	Lokum(MazeScreen p, Maze maze, float x, float y) {
		super(p);
		this.maze = maze;
		this.location.x = maze.tx + x * Maze.blockWidth;
		this.location.y = maze.ty + y * Maze.blockHeight;
		addAnimation(Animations.Lokum, Animations.LokumImagePath,
				Animations.LokumFrameDuration, Animations.LokumFrameWidth,
				Animations.LokumFrameHeight);
		width = Maze.blockWidth;
		height = Maze.blockHeight;
		bounds.add(new RectangleBound(this, new Vector(2,2), Maze.blockWidth-4, Maze.blockHeight-4));
//		bounds.add(new RectangleBound(this, new Vector(0,0), Maze.blockWidth, Maze.blockHeight));
		startAnimation(Animations.Lokum);
		this.rotation = maze.getRotation();
	}
	
//	@Override
//	public void move() {
////		mover.move(this);
//		Vector vel = getVelocity();
//		location.x += vel.x;
//		location.y += vel.y;
//		Vector acc = ToolBox.gravity;
//		vel.x += acc.x;
//		vel.y += acc.y;
//	}
	
	@Override
	public boolean onCollide(IBound thisBound, IBound thatBound, ICollidable obj) {
		if (obj instanceof Portal)
			return false;
		if (this.acceleration.y < 0)
			this.location.y = thatBound.getLocation().y + ((RectangleBound)thatBound).getHeight() - thisBound.getOffset().y;
		else if(this.acceleration.x < 0)
			this.location.x = thatBound.getLocation().x + ((RectangleBound)thatBound).getWidth() - thisBound.getOffset().x;
		else if (this.acceleration.y > 0)
			this.location.y = thatBound.getLocation().y - ((RectangleBound)thatBound).getHeight() + thisBound.getInvOffset().y;
		else if(this.acceleration.x > 0)
			this.location.x = thatBound.getLocation().x - ((RectangleBound)thatBound).getWidth() + thisBound.getInvOffset().x;
		
		getAcceleration().set(0);
		getVelocity().set(0);
		return false;
	}
}