package com.blox.maze.model;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;

public class Portal extends MazeGameObject {

	public static enum PortalType {
		BLUE, GREEN
	};

	private PortalType type;

	private final class Animations {
		private static final String Portal = "Portal";

		private static final String BlueImagePath = "turnmaze/portalblue.png";
		private static final String GreenImagePath = "turnmaze/portalgreen.png";

		private static final int PortalFrameWidth = Maze.blockWidth;
		private static final int PortalFrameHeight = Maze.blockHeight;
		private static final float PortalFrameDuration = 0.3f;
		private static final boolean PortalIsLooping = true;
	}

	Portal(float x, float y, PortalType t) {
		this.location.x = x;
		this.location.y = y;
		this.type = t;

		width = Maze.blockWidth;
		height = Maze.blockHeight;
		bounds.add(new RectangleBound(this, new Vector(0, 0), Maze.blockWidth, Maze.blockHeight));

		if (type == PortalType.BLUE)
			addAnimation(Animations.Portal, Animations.BlueImagePath, Animations.PortalFrameDuration, Animations.PortalFrameWidth, Animations.PortalFrameHeight, Animations.PortalIsLooping);
		else if (type == PortalType.GREEN)
			addAnimation(Animations.Portal, Animations.GreenImagePath, Animations.PortalFrameDuration, Animations.PortalFrameWidth, Animations.PortalFrameHeight, Animations.PortalIsLooping);

		startAnimation(Animations.Portal);
	}

	@Override
	public boolean onCollide(IBound thisBound, IBound thatBound, ICollidable obj) {
		// if (!(obj instanceof Lokum))
		// return false;
		// Lokum lokum = (Lokum)obj;
		// if (lokum.getLocation().y
		return false;
	}
}