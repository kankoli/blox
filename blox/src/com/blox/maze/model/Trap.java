package com.blox.maze.model;

import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;

/***
 * Trap elements of {@link Maze}.
 * 
 * @author kadirello
 * 
 */
public class Trap extends MazeGameObject {

	private final class Animations {
		private static final String Trap = "Trap";
		private static final String TrapImagePath = "turnmaze/trap.png";
		private static final int TrapFrameWidth = Maze.blockWidth;
		private static final int TrapFrameHeight = Maze.blockHeight;
		private static final float TrapFrameDuration = 0.1f;
		private static final boolean TrapIsLooping = true;
	}

	Trap(float x, float y) {
		this.location.x = x;
		this.location.y = y;
		addAnimation(Animations.Trap, Animations.TrapImagePath, Animations.TrapFrameDuration, Animations.TrapFrameWidth, Animations.TrapFrameHeight, Animations.TrapIsLooping);
		width = Maze.blockWidth;
		height = Maze.blockHeight;
		bounds.add(new RectangleBound(this, new Vector(0, 0), Maze.blockWidth, Maze.blockHeight));
		startAnimation(Animations.Trap);
	}
}