package com.blox.maze.model;

import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;
import com.blox.maze.util.R;

/***
 * Trap elements of {@link Maze}.
 * 
 * @author kadirello
 * 
 */
public class Trap extends MazeGameObject {

	Trap(float x, float y) {
		this.location.x = x;
		this.location.y = y;
		addAnimation(R.animations.Trap.def);
		width = Maze.blockWidth;
		height = Maze.blockHeight;
		bounds.add(new RectangleBound(this, new Vector(0, 0), Maze.blockWidth, Maze.blockHeight));
		startAnimation(R.animations.Trap.def);
	}
}