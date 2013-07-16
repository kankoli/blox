package com.blox.blockmaze;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.ToolBox;

public class TurnMaze extends GameObject {

	public enum MazeState {
		WAITING, USER_ROTATING, MAZE_ROTATING
	};

	private MazeState currentState;

	// public static final int UP = 0x01;
	// public static final int RIGHT = 0x02;
	// public static final int DOWN = 0x04;
	// public static final int LEFT = 0x08;

	private Block[][] blocks;

	private float tx;
	private float ty;

	private float rotationTheta;
	private float targetRotation;
	private float tempRotation;

	float rotateStart;

	private int cols;
	private int rows;
	private int[][] data;

	private int mazeWidth;
	private int mazeHeight;

	public TurnMaze(int w, int h) {
		data = new int[][] { { 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1 } };
		// blocks = new int[][]{{1,1},{1,1}};
		cols = data.length;
		rows = data[0].length;
		mazeWidth = cols * Block.blockWidth;
		mazeHeight = rows * Block.blockHeight;

		tx = (ToolBox.screenWidth - mazeWidth) / 2;
		ty = (ToolBox.screenHeight - mazeHeight) / 2;

		rotation.origin.x = tx + mazeWidth / 2;
		rotation.origin.y = ty + mazeHeight / 2;

		blocks = new Block[cols][rows];

		currentState = MazeState.WAITING;

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (data[i][j] == 1) {
					blocks[i][j] = new Block(tx + i * Block.blockWidth, ty + j
							* Block.blockHeight);
					blocks[i][j].setRotation(rotation);
					ToolBox.getDrawManager().register(blocks[i][j], 1);
				}
			}
		}
	}

	public void update() {
		if (this.currentState == MazeState.WAITING) {
			// draw();
		} else if (this.currentState == MazeState.USER_ROTATING) {
			// draw();
			// drawTemp();
		} else if (this.currentState == MazeState.MAZE_ROTATING) {
			this.currentState = MazeState.WAITING;
		}
	}

	// public void draw() {
	// for (int i = 0; i < cols; i++) {
	// for (int j = 0; j < rows; j++) {
	// if (blocks[i][j] != null)
	// blocks[i][j].draw();
	// }
	// }
	// }

	private final float epsilon = (float) (20 * Math.PI) / 360;
	private final float turnSpeed = (float) Math.PI; // saniyede

	// public void drawTemp() {
	// float tmpDr = targetRotation - rotation;
	// if (Math.abs(tmpDr) > epsilon) {
	// if (tmpDr > 0) {
	// rotation += turnSpeed * Gdx.graphics.getDeltaTime();
	// } else {
	// rotation -= turnSpeed * Gdx.graphics.getDeltaTime();
	// }
	// } else {
	// rotation = targetRotation;
	// }
	//
	// Gdx.gl10.glPushMatrix();
	// Gdx.gl10.glTranslatef(tx, ty, 0);
	// Gdx.gl10.glPushMatrix();
	// Gdx.gl10.glTranslatef(mazeWidth/2, mazeHeight/2, 0);
	// Gdx.gl10.glRotatef(90*(rotation + tempRotation), 0, 0, 1);
	// Gdx.gl10.glTranslatef(-mazeWidth/2, -mazeHeight/2, 0);
	// for (int i = 0; i < cols; i++) {
	// for (int j = 0; j < rows; j++) {
	// if (blocks[i][j] != null)
	// blocks[i][j].draw(spriteBatch);
	// }
	// }
	// Gdx.gl10.glPopMatrix();
	// }

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		this.currentState = MazeState.USER_ROTATING;
		tempRotation = 0;
		rotationTheta = 0;
		targetRotation = 0;
		rotateStart = x;
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		rotation.rotation.z += (rotateStart - x) / 10f;
		rotateStart = x;
		// tempRotation = (float) (Math.PI / 2 * ((rotateStart - x) /
		// ToolBox.screenWidth));
		// if (tempRotation >= 1) tempRotation = 1;
		// else if (tempRotation <= -1) tempRotation = -1;
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		this.currentState = MazeState.MAZE_ROTATING;
		turn();
		return false;
	}

	public void turn() {
		if (Math.abs(tempRotation) > Math.PI / 4)
			targetRotation = rotationTheta
					+ ((float) (tempRotation > 0 ? Math.PI : -Math.PI) / 2);
		tempRotation = 0;
	}
}
