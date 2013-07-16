package com.blox.blockmaze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.blox.World;
import com.blox.framework.CustomGestureListener;
import com.blox.framework.CustomInputProcessor;
import com.blox.framework.v0.impl.libgdx.GdxGameFactory;
import com.blox.framework.v0.util.ToolBox;

public class TurnMazeGame extends Game {
	private final int mazeWidth = 12;
	private final int mazeHeight = 12;
	TurnMaze maze;

	Block block;
	private SpriteBatch spriteBatch;
	
	public TurnMazeGame() {

	}
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		ToolBox.initialize(new GdxGameFactory(spriteBatch, null));
		
		block = new Block(100, 100);

//		World.scale = 1 / 28f;
		
		maze = new TurnMaze(mazeWidth, mazeHeight);
		
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
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA); 

		maze.update();
		
		spriteBatch.begin();
		//block.draw();
		ToolBox.getDrawManager().draw();
		spriteBatch.end();
	}
}
