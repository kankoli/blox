package com.blox.maze.model;

import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;

public class PortalDoor extends MazeGameObject {

	public static enum DoorType {
		BLUE, GREEN
	};

	private DoorType type;

	private final class Animations {
		private static final String Portal = "Portal";

		private static final String BlueImagePath = "turnmaze/portalblue.png";
		private static final String GreenImagePath = "turnmaze/portalgreen.png";

		private static final int PortalFrameWidth = Maze.blockWidth;
		private static final int PortalFrameHeight = Maze.blockHeight;
		private static final float PortalFrameDuration = 0.3f;
		
		private static final String EnterDoor = "EnterDoor";
		private static final String ExitDoor = "ExitDoor";
		
		private static final String EnterBlueDoorImagePath = "turnmaze/portalenterblue.png";
		private static final String EnterGreenDoorImagePath = "turnmaze/portalentergreen.png";
		
		private static final String ExitBlueDoorImagePath = "turnmaze/portalexitblue.png";
		private static final String ExitGreenDoorImagePath = "turnmaze/portalexitgreen.png";
	}

	private Portal parent;
	PortalDoor(Portal parent, float x, float y, DoorType t) {
		this.parent = parent;
		this.location.x = x;
		this.location.y = y;
		this.type = t;

		width = Maze.blockWidth;
		height = Maze.blockHeight;
		bounds.add(new RectangleBound(this, new Vector(0, 0), Maze.blockWidth, Maze.blockHeight));

		if (type == DoorType.BLUE){
			addAnimation(Animations.Portal, Animations.BlueImagePath, Animations.PortalFrameDuration, 
					Animations.PortalFrameWidth, Animations.PortalFrameHeight, true);
			addAnimation(Animations.EnterDoor, Animations.EnterBlueDoorImagePath, Animations.PortalFrameDuration, 
					Animations.PortalFrameWidth, Animations.PortalFrameHeight, false);
			addAnimation(Animations.ExitDoor, Animations.ExitBlueDoorImagePath, Animations.PortalFrameDuration, 
					Animations.PortalFrameWidth, Animations.PortalFrameHeight, false);
		}
		else if (type == DoorType.GREEN) {
			addAnimation(Animations.Portal, Animations.GreenImagePath, Animations.PortalFrameDuration, 
					Animations.PortalFrameWidth, Animations.PortalFrameHeight, true);
			addAnimation(Animations.EnterDoor, Animations.EnterGreenDoorImagePath, Animations.PortalFrameDuration, 
					Animations.PortalFrameWidth, Animations.PortalFrameHeight, false);
			addAnimation(Animations.ExitDoor, Animations.ExitGreenDoorImagePath, Animations.PortalFrameDuration, 
					Animations.PortalFrameWidth, Animations.PortalFrameHeight, false);
		}

		startAnimation(Animations.Portal);
	}

	public void startEnter() {
		startAnimation(Animations.EnterDoor);
	}

	public void startExit() {
		startAnimation(Animations.ExitDoor);
	}

	public void finish() {
		startAnimation(Animations.Portal);
	}
	
	public Portal getParent() {
		return this.parent;
	}
	
	public DoorType getType() {
		return type;
	}

	public PortalDoor getPair() {
		return parent.getOther(this);
	}

}