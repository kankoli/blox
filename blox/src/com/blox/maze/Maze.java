package com.blox.maze;

import com.blox.framework.v0.util.Game;
import com.blox.maze.Portal.PortalType;

class Maze extends MazeGameObject {
//	private MazeStateManager stateManager;
 	private enum MazeState {
 		WAITING, USER_ROTATING, MAZE_ROTATING
 	};

 	private MazeState currState;
 	
	public static final int blockWidth = 40;
	public static final int blockHeight = 40;
	
	public float tx;
	public float ty;
	
	public Maze(MazeScreen p) {
		super(p);
		
		int[][] data = new int[][] { 
				{ 1, 1, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 1, 0, 1 },
				{ 1, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 1 },
				{ 1, 0, 1, 0, 0, 1 }, 
				{ 1, 1, 1, 1, 1, 1 } };

		int cols = data.length;
		int rows = data[0].length;
		int mazeWidth = cols * blockWidth;
		int mazeHeight = rows * blockHeight;

		tx = (Game.world.screenWidth - mazeWidth) / 2;
		ty = (Game.world.screenHeight - mazeHeight) / 2;

		rotation.origin.x = tx + mazeWidth / 2;
		rotation.origin.y = ty + mazeHeight / 2;

		getScreen().registerInputListener(this);
		
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (data[i][j] == 1) {
					Block block = new Block(getScreen(), tx + i * blockWidth, ty + j * blockHeight);
					block.setRotation(rotation);
					
					getScreen().registerDrawable(block, 2);
					getScreen().registerCollidable(block);
				}
				else if (data[i][j] == 2) {
					Trap trap = new Trap(getScreen(), tx + i * blockWidth, ty + j * blockHeight);
					trap.setRotation(rotation);
					
					getScreen().registerDrawable(trap, 2);
					getScreen().registerCollidable(trap);
				}
				else if (data[i][j] == 3) {
					Portal portal = new Portal(getScreen(), tx + i * blockWidth, ty + j * blockHeight, PortalType.BLUE);
					portal.setRotation(rotation);
					
					getScreen().registerDrawable(portal, 2);
					getScreen().registerCollidable(portal);
				}
				else if (data[i][j] == 4) {
					Portal portal = new Portal(getScreen(), tx + i * blockWidth, ty + j * blockHeight, PortalType.GREEN);
					portal.setRotation(rotation);
					
					getScreen().registerDrawable(portal, 2);
					getScreen().registerCollidable(portal);
				}
			}
		}
		getScreen().registerInputListener(this);
//		stateManager = new MazeStateManager(this);
		currState = MazeState.WAITING;
	}
	
	private float rotationSpeed = 90; // degrees per second
	
	private float epsilon = 0.1f;
	
	public void update() {
//		stateManager.work();
		if (currState == MazeState.MAZE_ROTATING) {
			float increment = rotationSpeed * Game.getDeltaTime();
			rotation.rotation.z += userRotation * increment;
			mazeTempRotation -= increment;
			if (mazeTempRotation <= 0 + epsilon) { // MAZE_ROTATE FINISHED
				rotation.rotation.z = mazeOldRotation + userRotation * 90;
				currState = MazeState.WAITING;
				MazeMover.instance.turn(userRotation == 1);
			}
		}
	}
	
	private float rotateStart;		// used to keep track of userRotation
	private float userTempRotation; // draw temp grayscale with this angle
	private int userRotation; 		// init -1 or 1 for maze rotation. add to mazeOldRotation and set rotation when animation is finished
	private float mazeOldRotation; 	// keep old rotation
	private float mazeTempRotation; // decrement while rotating
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		if (currState == MazeState.WAITING) {
			rotateStart = x;
			userTempRotation = 0;
			userRotation = 0;
			mazeOldRotation = rotation.rotation.z;
			currState = MazeState.USER_ROTATING;
		}
		return false;
	}
	
	private float maxTemp = 25;
	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		if (currState == MazeState.USER_ROTATING) {
			userTempRotation -= (rotateStart - x) / 3f;
			if (userTempRotation > maxTemp) 
				userTempRotation = maxTemp;
			else if (userTempRotation < -maxTemp) 
				userTempRotation = -maxTemp;
			rotation.rotation.z = mazeOldRotation + userTempRotation;
			rotateStart = x;
		}
		return false;
	}
	
	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		if (currState == MazeState.USER_ROTATING) {
			if (userTempRotation > 0)
				userRotation = 1;
			else if (userTempRotation < 0)
				userRotation = -1;
			else {
				userTempRotation = 0;
				rotation.rotation.z = mazeOldRotation;
				return false;
			}
				
			currState = MazeState.MAZE_ROTATING;
			
			mazeTempRotation = 90 - userRotation * userTempRotation;
		}
		return false;
	}
}