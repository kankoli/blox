package com.blox.maze;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.ToolBox;
import com.blox.framework.v0.util.Vector;

public class Portal extends GameObject {
	
	public static enum PortalType { BLUE, GREEN };
	
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

		width = ToolBox.scale(Maze.blockWidth);
		height = ToolBox.scale(Maze.blockHeight);
		bounds.add(new RectangleBound(this, new Vector(0,0), Maze.blockWidth, Maze.blockHeight));
		
		if (type == PortalType.BLUE)
			addAnimation(Animations.Portal, Animations.BlueImagePath,
					Animations.PortalFrameDuration, Animations.PortalFrameWidth,
					Animations.PortalFrameHeight, Animations.PortalIsLooping);
		else if (type == PortalType.GREEN)
			addAnimation(Animations.Portal, Animations.GreenImagePath,
					Animations.PortalFrameDuration, Animations.PortalFrameWidth,
					Animations.PortalFrameHeight, Animations.PortalIsLooping);
		
		startAnimation(Animations.Portal);
	}
	
	void setRotation(Rotation rotation) {
		super.rotation = rotation;
	}
}