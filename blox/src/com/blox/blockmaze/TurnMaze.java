package com.blox.blockmaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.blox.ScaledShapeRenderer;
import com.blox.World;
import com.blox.framework.BloxSprite;
import com.blox.test.fruitgame.Fruit.FruitState;

public class TurnMaze extends BloxSprite {

	private class Block {
		int neighbors;
		int posX;
		int posY;
		
		public Block(int neighbors, int x, int y) {
			this.neighbors = neighbors;
			this.posX = x;
			this.posY = y;
		}
		
		public void draw(SpriteBatch spriteBatch) {
			spriteBatch.draw(body, posX, posY);
			if ((neighbors & UP) == UP) {
				spriteBatch.draw(connection, posX, posY + blockHeight-connectionHeight);
				spriteBatch.draw(connection, posX + blockWidth-connectionWidth, posY + blockHeight-connectionHeight);
			}
			if ((neighbors & RIGHT) == RIGHT) {
				spriteBatch.draw(connection, posX + blockWidth-connectionWidth, posY + blockHeight-connectionHeight);
				spriteBatch.draw(connection, posX + blockWidth-connectionWidth, posY);
			}
			if ((neighbors & DOWN) == DOWN) {
				spriteBatch.draw(connection, posX, posY);
				spriteBatch.draw(connection, posX + blockWidth-connectionWidth, posY);
			}
			if ((neighbors & LEFT) == LEFT) {
				spriteBatch.draw(connection, posX, posY);
				spriteBatch.draw(connection, posX, posY + blockHeight-connectionHeight);
			}
		}
		
		public void update(float delta) { }
	}
	
	public enum MazeState {
		WAITING, USER_ROTATING, MAZE_ROTATING
	};
	private MazeState currentState;
	
	public static final int UP = 0x01;
	public static final int RIGHT = 0x02;
	public static final int DOWN = 0x04;
	public static final int LEFT = 0x08;
	
	private Block[][] sprites;
	public static Texture body = new Texture(Gdx.files.internal("turnmaze/body.png"));
	public static Texture connection = new Texture(Gdx.files.internal("turnmaze/connection.png"));
	public static int blockWidth = body.getWidth();
	public static int blockHeight = body.getHeight();
	public static int connectionWidth = connection.getWidth();
	public static int connectionHeight = connection.getHeight();
	
	private float tx;
	private float ty;

	private float rotation;
	private float targetRotation;
	private float tempRotation;

	int rotateStart;
	
	private int width;
	private int height;
	private int[][] blocks;
	
	private int mazeWidth;
	private int mazeHeight;
	
	private SpriteBatch spriteBatch;
	
	public TurnMaze(SpriteBatch sb, int w, int h) {
		spriteBatch = sb;
		blocks = new int[][]{{1,1,1,1,1,1},{1,0,0,0,0,1},{1,0,0,0,0,1},{1,0,1,1,0,1},{1,0,0,0,0,1},{1,1,1,1,1,1}};
//		blocks = new int[][]{{1,1},{1,1}};
		width = blocks.length;
		height = blocks[0].length;
		mazeWidth = width*blockWidth;
		mazeHeight = height*blockHeight;
		setTranslation((World.width-mazeWidth)/2, (World.height-mazeHeight)/2);
		
		sprites = new Block[width][height];
		
		currentState = MazeState.WAITING;
		
		int neighbors;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (blocks[i][j] == 1) {
					neighbors = 0;
					if (j+1 < height && blocks[i][j+1] == 1)
						neighbors |= UP;
					if (i+1 < width && blocks[i+1][j] == 1)
						neighbors |= RIGHT;
					if (j-1 >= 0 && blocks[i][j-1] == 1)
						neighbors |= DOWN;
					if (i-1 >= 0 && blocks[i-1][j] == 1)
						neighbors |= LEFT;
					sprites[i][j] = new Block(neighbors, i*blockWidth, j*blockHeight);
					}
				}
			}
		}

	public void setTranslation(float x, float y) {
		tx = x;
		ty = y;
	}

	@Override
	public void update(float delta) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (sprites[i][j] != null)
					sprites[i][j].update(delta);
			}
		}
		if (this.currentState == MazeState.WAITING) {
			draw(spriteBatch);
		}
		else if (this.currentState == MazeState.USER_ROTATING) {
			draw(spriteBatch);
			drawTemp(spriteBatch);
		}
		else if (this.currentState == MazeState.MAZE_ROTATING) {
			this.currentState = MazeState.WAITING;
		}
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		Gdx.gl10.glPushMatrix();
		Gdx.gl10.glTranslatef(tx, ty, 0);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (sprites[i][j] != null)
					sprites[i][j].draw(spriteBatch);
			}
		}
		Gdx.gl10.glPopMatrix();
	}

	private final float epsilon = (float) (20 * Math.PI) / 360;
	private final float turnSpeed = (float)Math.PI; // saniyede
	
	public void drawTemp(SpriteBatch spriteBatch) {
		float tmpDr = targetRotation - rotation;
		if (Math.abs(tmpDr) > epsilon) {
			if (tmpDr > 0) {
				rotation += turnSpeed * Gdx.graphics.getDeltaTime();
			} else {
				rotation -= turnSpeed * Gdx.graphics.getDeltaTime();
			}
		} else {
			rotation = targetRotation;
		}

		Gdx.gl10.glPushMatrix();
		Gdx.gl10.glTranslatef(tx, ty, 0);
		Gdx.gl10.glPushMatrix();
		Gdx.gl10.glTranslatef(mazeWidth/2, mazeHeight/2, 0);
		Gdx.gl10.glRotatef(90*(rotation + tempRotation), 0, 0, 1);
		Gdx.gl10.glTranslatef(-mazeWidth/2, -mazeHeight/2, 0);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (sprites[i][j] != null)
					sprites[i][j].draw(spriteBatch);
			}
		}
		Gdx.gl10.glPopMatrix();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		this.currentState = MazeState.USER_ROTATING;
		tempRotation = 0;
		rotation = 0;
		targetRotation = 0;
		rotateStart = screenX;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		tempRotation = (float) (Math.PI / 2 * ((rotateStart - screenX) / World.width));
		if (tempRotation >= 1) tempRotation = 1;
		else if (tempRotation <= -1) tempRotation = -1;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		this.currentState = MazeState.MAZE_ROTATING;
		turn();
		return false;
	}
	
//	public void tempRotate(float theta) {
//		tempRotation += theta;
//	}
	
	public void turn() {
		if (Math.abs(tempRotation) > Math.PI / 4)
			targetRotation = rotation + ((float) (tempRotation > 0 ? Math.PI : -Math.PI) / 2);
		tempRotation = 0;
	}
}
