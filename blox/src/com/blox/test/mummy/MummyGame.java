package com.blox.test.mummy;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;

public class MummyGame implements ApplicationListener {
	SpriteBatch spriteBatch;
	Mummy mummy;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);

		mummy = new Mummy();
		spriteBatch = new SpriteBatch();
		
//		CompositeInputDetector mummyListener = new CompositeInputDetector();
//		mummyListener.register(mummy);

		CustomInputProcessor inputProcessor = new CustomInputProcessor();
		inputProcessor.register(mummy);
		CustomGestureListener gestureListener = new CustomGestureListener();
		gestureListener.register(mummy);
		GestureDetector gestureDetector = new GestureDetector(gestureListener);
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(gestureDetector);
		multiplexer.addProcessor(inputProcessor);
		
		Gdx.input.setInputProcessor(multiplexer);
		//Gdx.input.setInputProcessor(new GestureDetector(mummyListener));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // #14
		
		spriteBatch.begin();
		mummy.draw(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
