package com.blox.test.fruitgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.blox.framework.CustomGestureListener;
import com.blox.framework.CustomInputProcessor;

public class FruitGame implements ApplicationListener {
	SpriteBatch spriteBatch;
	float delta;
	
	Fruit watermelon1;
//	Fruit watermelon2;
//	Fruit watermelon3;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		spriteBatch = new SpriteBatch();
		watermelon1 = new Fruit(spriteBatch, 70f, 70f);
//		watermelon2 = new Fruit(0.2f, 40f, 1f);
//		watermelon3 = new Fruit(0.4f, 70f, 1f);
		
//		CompositeInputDetector mummyListener = new CompositeInputDetector();
//		mummyListener.register(mummy);

		CustomInputProcessor inputProcessor = new CustomInputProcessor();
		inputProcessor.register(watermelon1);
//		inputProcessor.register(watermelon2);
//		inputProcessor.register(watermelon3);
		CustomGestureListener gestureListener = new CustomGestureListener();
		gestureListener.register(watermelon1);
//		gestureListener.register(watermelon2);
//		gestureListener.register(watermelon3);
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
		delta = Gdx.graphics.getDeltaTime();
		
		spriteBatch.begin();
		watermelon1.update(delta);
//		watermelon2.draw(spriteBatch);
//		watermelon3.draw(spriteBatch);
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