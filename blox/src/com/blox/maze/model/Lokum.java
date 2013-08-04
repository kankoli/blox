package com.blox.maze.model;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;
import com.blox.maze.util.R;

public class Lokum extends MazeGameObject {
	private Vector startLocation;

	public Lokum(Maze maze, float x, float y) {
		this.startLocation = new Vector();
		this.startLocation.x = maze.tx + x * Maze.blockWidth;
		this.startLocation.y = maze.ty + y * Maze.blockHeight;
		this.location.x = this.startLocation.x;
		this.location.y = this.startLocation.y;
		addAnimation(R.animations.Lokum.def);
		addAnimation(R.animations.Lokum.onTrap);
		addAnimation(R.animations.Lokum.onObjective);
		addAnimation(R.animations.Lokum.onPortal);
		
		width = Maze.blockWidth;
		height = Maze.blockHeight;
		// bounds.add(new RectangleBound(this, new Vector(2, 2), Maze.blockWidth
		// - 4, Maze.blockHeight - 4));
		bounds.add(new RectangleBound(this, new Vector(0, 0), Maze.blockWidth, Maze.blockHeight));
		startAnimation(R.animations.Lokum.def);
		this.rotation = maze.getRotation();
	}

	/***
	 * Repositions {@link Lokum} to outside the colliding boundary.
	 * 
	 * @param thisBound
	 * @param thatBound
	 * @param obj
	 */
	public void fellOnBlock(IBound thisBound, IBound thatBound, ICollidable obj) {
		if (this.acceleration.y < 0)
			this.location.y = thatBound.getLocation().y + ((RectangleBound) thatBound).getHeight() - thisBound.getOffset().y;
		else if (this.acceleration.x < 0)
			this.location.x = thatBound.getLocation().x + ((RectangleBound) thatBound).getWidth() - thisBound.getOffset().x;
		else if (this.acceleration.y > 0)
			this.location.y = thatBound.getLocation().y - ((RectangleBound) thatBound).getHeight() + thisBound.getInvOffset().y;
		else if (this.acceleration.x > 0)
			this.location.x = thatBound.getLocation().x - ((RectangleBound) thatBound).getWidth() + thisBound.getInvOffset().x;
	}

	public void stopLokum() {
		getAcceleration().set(0);
		getVelocity().set(0);
	}

	public void fellOnTrap() {
		startAnimation(R.animations.Lokum.onTrap);
	}

	public void fellOnPortal() {
		startAnimation(R.animations.Lokum.onPortal);
	}

	public void fellOnObjective() {
		startAnimation(R.animations.Lokum.onObjective);
	}

	/***
	 * {@link Lokum} is reset to its starting state.
	 */
	public void reset() {
		this.location.x = this.startLocation.x;
		this.location.y = this.startLocation.y;
		startAnimation(R.animations.Lokum.def);
	}

	/***
	 * {@link Lokum} is teleported to position of the given
	 * {@link com.blox.framework.v0.ICollidable ICollidable} object.
	 * 
	 * @param obj
	 * @see {@link com.blox.maze.controller.MazeController#portalFinished()
	 *      portalFinished()}
	 */
	public void teleport(ICollidable obj) {
		this.location.set(obj.getLocation());
	}
}