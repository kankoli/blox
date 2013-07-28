package com.blox.test.camera;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OrthographicCameraController implements ApplicationListener {

	static final float WIDTH = 550;
	static final float HEIGHT = 800;

	private OrthographicCameraWithVirtualViewport cam;
	private Texture texture;
	private float rotationSpeed;
	private SpriteBatch spriteBatch;
	private MultipleVirtualViewportBuilder builder;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		spriteBatch = new SpriteBatch();
		rotationSpeed = 0.5f;
		texture = new Texture(Gdx.files.internal("bg.png"));

		builder = new MultipleVirtualViewportBuilder(450, 800, 640, 800);
		VirtualViewport viewPort = builder.getVirtualViewport(WIDTH, HEIGHT);
		cam = new OrthographicCameraWithVirtualViewport(viewPort);
		cam.position.set(viewPort.getWidth() / 2, viewPort.getHeight() / 2, 0);
	}

	@Override
	public void render() {
		handleInput();

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		cam.update();
		spriteBatch.setProjectionMatrix(cam.combined);

		spriteBatch.begin();
		spriteBatch.draw(texture, 0, 0);
		spriteBatch.end();
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.zoom += 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			cam.zoom -= 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (cam.position.x > 0)
				cam.translate(-3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (cam.position.x < 1024)
				cam.translate(3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			if (cam.position.y > 0)
				cam.translate(0, -3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if (cam.position.y < 1024)
				cam.translate(0, 3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			cam.rotate(-rotationSpeed, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			cam.rotate(rotationSpeed, 0, 0, 1);
		}
		System.out.println(cam.zoom);
	}

	@Override  
    public void resize(int width, int height) {  
          
        VirtualViewport viewPort = builder.getVirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
        cam.setVirtualViewport(viewPort);  
          
        cam.updateViewport();  
        // centers the camera at 0, 0 (the center of the virtual viewport)  
		cam.position.set(viewPort.getWidth() / 2 + 50, viewPort.getHeight() / 2 + 100, 0); 
    }  

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}
}
