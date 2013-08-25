package com.blox.maze.model;

import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;
import com.blox.maze.util.R;

/***
 * Objective element(s) of {@link Maze}.
 * 
 * @author kadirello
 * 
 */
public class Objective extends MazeGameObject {

	Objective(float x, float y) {
		this.location.x = x;
		this.location.y = y;
		addAnimation(R.animations.Objective.def);
		width = Maze.blockWidth;
		height = Maze.blockHeight;
		bounds.add(new RectangleBound(this, new Vector(0, 0), Maze.blockWidth, Maze.blockHeight));
		startAnimation(R.animations.Objective.def);
	}
}