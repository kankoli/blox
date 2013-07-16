package com.blox.blockmaze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.blox.World;
import com.blox.framework.CustomGestureListener;
import com.blox.framework.CustomInputProcessor;

public class TurnMazeGame extends Game {
	private SpriteBatch spriteBatch;
	private float delta;
	
	private final int mazeWidth = 12;
	private final int mazeHeight = 12;
	TurnMaze maze;

	public TurnMazeGame() {

	}
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		
		World.width = Gdx.graphics.getWidth();
		World.height = Gdx.graphics.getHeight();

//		World.scale = 1 / 28f;
		
		maze = new TurnMaze(spriteBatch, mazeWidth, mazeHeight);
		
//		try {
//			InputStream fis = Gdx.files.internal("maze.dat").read();
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			int[][] arr = (int[][]) ois.readObject();
//			maze.setWalls(arr);
//			ois.close();
//			fis.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		CustomInputProcessor inputProcessor = new CustomInputProcessor();
		inputProcessor.register(maze);
		// inputProcessor.register(watermelon3);
		CustomGestureListener gestureListener = new CustomGestureListener();
		gestureListener.register(maze);
		// gestureListener.register(watermelon3);
		GestureDetector gestureDetector = new GestureDetector(gestureListener);

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(gestureDetector);
		multiplexer.addProcessor(inputProcessor);

		Gdx.input.setInputProcessor(multiplexer);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		delta = Gdx.graphics.getDeltaTime();

		spriteBatch.begin();
		maze.update(delta);
		spriteBatch.end();
	}
}
