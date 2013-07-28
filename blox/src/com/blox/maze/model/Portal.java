package com.blox.maze.model;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.util.Rotation;
import com.blox.maze.model.PortalDoor.DoorType;
import com.blox.maze.view.MazeScreen;

public class Portal extends MazeGameObject {

	private PortalDoor blueDoor;
	private PortalDoor greenDoor;
	
	public Portal(MazeScreen screen, float blueX, float blueY, float greenX, float greenY) {
		blueDoor = new PortalDoor(this, blueX, blueY, PortalDoor.DoorType.BLUE);
		greenDoor = new PortalDoor(this, greenX, greenY, PortalDoor.DoorType.GREEN);

		screen.registerDrawable(blueDoor, 2);
		screen.registerDrawable(greenDoor, 2);
	}
	
	@Override
	public void setRotation(Rotation r) {
		blueDoor.setRotation(r);
		greenDoor.setRotation(r);
	}

	public List<PortalDoor> getDoors() {
		List<PortalDoor> list = new ArrayList<PortalDoor>();
		list.add(blueDoor);
		list.add(greenDoor);
		return list;
	}
	
	public void enterPortal(PortalDoor door) {
		if (door.getType() == DoorType.BLUE) 
			enterBluePortal();
		else
			enterGreenPortal();
	}
	
	public void enterBluePortal() {
		blueDoor.startEnter();
		greenDoor.startExit();
	}
	
	public void enterGreenPortal() {
		greenDoor.startEnter();
		blueDoor.startExit();
	}

	public void finishPortal() {
		greenDoor.finish();
		blueDoor.finish();
	}
	
//	@Override
//	public void registerCollisionListener(ICollisionListener listener) {
//		blueDoor.registerCollisionListener(listener);
//		greenDoor.registerCollisionListener(listener);
//	}
//	
//	@Override
//	public void unregisterCollisionListener(ICollisionListener listener) {
//		blueDoor.unregisterCollisionListener(listener);
//		greenDoor.unregisterCollisionListener(listener);
//	}
//	
//	@Override
//	public void registerAnimationEndListener(IAnimationEndListener listener) {
//		blueDoor.registerAnimationEndListener(listener);
//		greenDoor.registerAnimationEndListener(listener);
//	}
//	
//	@Override
//	public void unregisterAnimationEndListener(IAnimationEndListener listener) {
//		blueDoor.unregisterAnimationEndListener(listener);
//		greenDoor.unregisterAnimationEndListener(listener);
//	}

	public PortalDoor getOther(PortalDoor portalDoor) {
		if (portalDoor.equals(blueDoor))
			return greenDoor;
		return blueDoor;
	}
}