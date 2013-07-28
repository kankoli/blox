package com.blox.maze.model;

import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;

/***
 * Objective element(s) of {@link Maze}.
 * 
 * @author kadirello
 * 
 */
public class Objective extends MazeGameObject {

	private final class Animations {
		private static final String Objective = "Objective";
		private static final String ObjectiveImagePath = "turnmaze/door.gif";
		private static final int ObjectiveFrameWidth = Maze.blockWidth;
		private static final int ObjectiveFrameHeight = Maze.blockHeight;
		private static final float ObjectiveFrameDuration = 1f;
	}

	Objective(float x, float y) {
		this.location.x = x;
		this.location.y = y;
		addAnimation(Animations.Objective, Animations.ObjectiveImagePath, Animations.ObjectiveFrameDuration, Animations.ObjectiveFrameWidth, Animations.ObjectiveFrameHeight);
		width = Maze.blockWidth;
		height = Maze.blockHeight;
		bounds.add(new RectangleBound(this, new Vector(0, 0), Maze.blockWidth, Maze.blockHeight));
		startAnimation(Animations.Objective);
	}
}