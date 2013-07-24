package com.blox.maze;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.ToolBox;
import com.blox.framework.v0.util.Vector;

public class Lokum extends GameObject {
 	
	private Maze maze;
	
	private final class Animations {
		private static final String Lokum = "Lokum";
		private static final String LokumImagePath = "turnmaze/lokum.png";
		private static final int LokumFrameWidth = Maze.blockWidth;
		private static final int LokumFrameHeight = Maze.blockHeight;
		private static final float LokumFrameDuration = 0.1f;
	}
	
	Lokum(Screen p, Maze maze, float x, float y) {
		super(p);
		this.maze = maze;
		this.location.x = maze.tx + x * Maze.blockWidth;
		this.location.y = maze.ty + y * Maze.blockHeight;;
		addAnimation(Animations.Lokum, Animations.LokumImagePath,
				Animations.LokumFrameDuration, Animations.LokumFrameWidth,
				Animations.LokumFrameHeight);
		width = ToolBox.scale(Maze.blockWidth);
		height = ToolBox.scale(Maze.blockHeight);
		bounds.add(new RectangleBound(this, new Vector(0,0), Maze.blockWidth, Maze.blockHeight));
		startAnimation(Animations.Lokum);
		parent.registerDrawable(new Background(), 1);
	}
	
	@Override
	public void move() {
//		mover.move(this);
		Vector vel = getVelocity();
		location.x += vel.x;
		location.y += vel.y;
		Vector acc = ToolBox.gravity;
		vel.x += acc.x;
		vel.y += acc.y;
	}
	
	@Override
	public boolean onCollide(IBound thisBound, IBound thatBound, ICollidable obj) {
		setVelocity(0, 0);
		return super.onCollide(thisBound, thatBound, obj);
	}
}