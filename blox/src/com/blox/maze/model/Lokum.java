package com.blox.maze.model;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;

public class Lokum extends MazeGameObject {

	private final class Animations {
		private static final String Lokum = "Lokum";
		private static final String LokumImagePath = "turnmaze/lokum.png";
		private static final int LokumFrameWidth = Maze.blockWidth;
		private static final int LokumFrameHeight = Maze.blockHeight;
		private static final float LokumFrameDuration = 0.1f;
		
		private static final String FellOnTrap = "FellOnTrap";
		private static final String FellOnTrapImagePath = "turnmaze/lokumtrap.png";
		private static final int FellOnTrapFrameWidth = Maze.blockWidth;
		private static final int FellOnTrapFrameHeight = Maze.blockHeight;
		private static final float FellOnTrapFrameDuration = 0.15f;
		
		private static final String FellOnObjective = "FellOnObjective";
		private static final String FellOnObjectiveImagePath = "turnmaze/lokumobj.png";
		private static final int FellOnObjectiveFrameWidth = Maze.blockWidth;
		private static final int FellOnObjectiveFrameHeight = Maze.blockHeight;
		private static final float FellOnObjectiveFrameDuration = 0.1f;
		
		private static final String FellOnPortal = "FellOnPortal";
		private static final String FellOnPortalImagePath = "turnmaze/lokum.png";
		private static final int FellOnPortalFrameWidth = Maze.blockWidth;
		private static final int FellOnPortalFrameHeight = Maze.blockHeight;
		private static final float FellOnPortalFrameDuration = 0.15f;
		
	}

	private Vector startLocation;
	
	public Lokum(Maze maze, float x, float y) {
		this.startLocation = new Vector();
		this.startLocation.x = maze.tx + x * Maze.blockWidth;
		this.startLocation.y = maze.ty + y * Maze.blockHeight;
		this.location.x = this.startLocation.x;
		this.location.y = this.startLocation.y;
		addAnimation(Animations.Lokum, Animations.LokumImagePath, Animations.LokumFrameDuration, Animations.LokumFrameWidth, Animations.LokumFrameHeight);
		addAnimation(Animations.FellOnTrap, Animations.FellOnTrapImagePath, Animations.FellOnTrapFrameDuration, 
				Animations.FellOnTrapFrameWidth, Animations.FellOnTrapFrameHeight);
		addAnimation(Animations.FellOnObjective, Animations.FellOnObjectiveImagePath, Animations.FellOnObjectiveFrameDuration, 
				Animations.FellOnObjectiveFrameWidth, Animations.FellOnObjectiveFrameHeight);
		addAnimation(Animations.FellOnPortal, Animations.FellOnPortalImagePath, Animations.FellOnPortalFrameDuration, 
				Animations.FellOnPortalFrameWidth, Animations.FellOnPortalFrameHeight);
		
		width = Maze.blockWidth;
		height = Maze.blockHeight;
//		bounds.add(new RectangleBound(this, new Vector(2, 2), Maze.blockWidth - 4, Maze.blockHeight - 4));
		 bounds.add(new RectangleBound(this, new Vector(0,0), Maze.blockWidth, Maze.blockHeight));
		startAnimation(Animations.Lokum);
		this.rotation = maze.getRotation();
	}

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
		startAnimation(Animations.FellOnTrap);
	}
	
	public void fellOnPortal() {
		startAnimation(Animations.FellOnPortal);
	}
	
	public void fellOnObjective() {
		startAnimation(Animations.FellOnObjective);
	}

	public void reset() {
		this.location.x = this.startLocation.x;
		this.location.y = this.startLocation.y;
		startAnimation(Animations.Lokum);
	}

	public void teleport(ICollidable pair) {
		this.location.set(pair.getLocation());
	}
}