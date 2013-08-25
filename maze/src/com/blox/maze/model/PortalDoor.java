package com.blox.maze.model;

import com.blox.framework.v0.impl.RectangleBound;
import com.blox.framework.v0.util.Vector;
import com.blox.maze.util.R;

/***
 * PortalDoor elements of {@link Maze}.
 * 
 * @author kadirello
 * 
 */
public class PortalDoor extends MazeGameObject {

	public static enum DoorType {
		BLUE, GREEN
	};

	private DoorType type;

	private Portal parent;

	PortalDoor(Portal parent, float x, float y, DoorType t) {
		this.parent = parent;
		this.location.x = x;
		this.location.y = y;
		this.type = t;

		width = Maze.blockWidth;
		height = Maze.blockHeight;
		bounds.add(new RectangleBound(this, new Vector(0, 0), Maze.blockWidth, Maze.blockHeight));

		if (type == DoorType.BLUE) {
			addAnimation(R.animations.PortalDoor.defBlue);
			addAnimation(R.animations.PortalDoor.enterBlue);
			addAnimation(R.animations.PortalDoor.exitBlue);
		} else if (type == DoorType.GREEN) {
			addAnimation(R.animations.PortalDoor.defGreen);
			addAnimation(R.animations.PortalDoor.enterGreen);
			addAnimation(R.animations.PortalDoor.exitGreen);
		}

		startAnimation(R.animations.PortalDoor.defName);
	}

	public void startEnter() {
		startAnimation(R.animations.PortalDoor.enterName);
	}

	public void startExit() {
		startAnimation(R.animations.PortalDoor.exitName);
	}

	public void finish() {
		startAnimation(R.animations.PortalDoor.defName);
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