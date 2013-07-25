package com.blox.maze.controller;

import com.blox.framework.v0.util.Game;

public class MazeUserRotatingState extends MazeState {
	private final static float maxTemp = 45;
	private final static float minTemp = 15;
	private MazeController controller;

	public MazeUserRotatingState(MazeController parent) {
		this.controller = parent;
	}

	private float rotateStartX, rotateStartY; // used to keep track of
												// userRotation

	private float userTempRotation; // draw temp grayscale with this angle
	private int userRotation; // init -1 or 1 for maze rotation. add to
								// mazeOldRotation and set rotation when
								// animation is finished

	@Override
	public boolean touchDragged(float x, float y, int pointer) {		
		float dx = ((x - rotateStartX) / (Game.world.screenWidth)) * 90;
		float dy = ((y - rotateStartY) / (Game.world.screenWidth)) * 90;

		if (y > Game.world.screenHeight / 2)
			dx = -dx;
		if (x < Game.world.screenWidth / 2)
			dy = -dy;

		userTempRotation += dx + dy;
		userTempRotation = limitAngle(userTempRotation);
		
		rotateStartX = x;
		rotateStartY = y;
		
		controller.userRotated(userTempRotation);
		return false;

	}

	private float limitAngle(float angle) {
		if (angle > maxTemp)
			angle = maxTemp;
		else if (angle < -maxTemp)
			angle = -maxTemp;
		return angle;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		if (userTempRotation > minTemp)
			userRotation = 1;
		else if (userTempRotation < -minTemp)
			userRotation = -1;
		else {
			userTempRotation = 0;
			controller.userAbortRotation();
			return false;
		}

		controller.startMazeRotate(userRotation, userTempRotation);
		return false;
	}

	public void setStarts(float rotateStartX, float rotateStartY) {
		this.rotateStartX = rotateStartX;
		this.rotateStartY = rotateStartY;
		this.userTempRotation = 0;
	}
}
