package com.blox.test.camera;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Camera2 implements ApplicationListener {

	private float maxWidth = 650f;
	private float maxHeight = 1000f;
	private float width = 550f;
	private float height = 800f;

	private float viewportWidth;
	private float viewportHeight;

	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private Texture texture;

	private float x, y, r;
	private float vx, vy;
	private float offsetX, offsetY;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);

		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();

		texture = new Texture(Gdx.files.internal("bg.png"));

		shapeRenderer.setColor(Color.BLACK);

		x = 10;
		y = 10;
		r = 10;
		vx = 210;
		vy = 190;

		float screenWidth = (float) Gdx.graphics.getWidth();
		float screenHeight = (float) Gdx.graphics.getHeight();

		float wr = width / screenWidth;
		float hr = height / screenHeight;

		if (wr > hr) {
			viewportWidth = width;
			viewportHeight = screenHeight * wr;
		}
		else {
			viewportWidth = screenWidth * hr;
			viewportHeight = height;
		}
		offsetX = Math.abs(viewportWidth - screenWidth) / 2;
		offsetY = Math.abs(viewportHeight - screenHeight) / 2;

		System.out.println(viewportWidth + ", " + viewportHeight);

		camera = new OrthographicCamera(viewportWidth, viewportHeight);
		// camera.zoom = scale * scale;
		camera.position.set(screenWidth/2 + offsetX, screenHeight/2 + offsetY, 0);

	}

	@Override
	public void render() {
		handleInput();
		move();
		draw();
	}

	private void move() {
		float dt = Gdx.graphics.getDeltaTime();
		x += vx * dt;
		y += vy * dt;

		if (x < r) {
			x = r;
			vx = -vx;
		}
		else if (x > width - r) {
			x = width - r;
			vx = -vx;
		}
		if (y < r) {
			y = r;
			vy = -vy;
		}
		else if (y > height - r) {
			y = height - r;
			vy = -vy;
		}
	}

	private void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		camera.update();

		spriteBatch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		spriteBatch.begin();
		spriteBatch.draw(texture, 0, 0);
		spriteBatch.end();

		shapeRenderer.begin(ShapeType.FilledCircle);
		shapeRenderer.filledCircle(x, y, r);
		shapeRenderer.end();
	}

	private void handleInput() {
		float x = 1;
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
			x = 0.1f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.translate(-3 * x, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.translate(3 * x, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.translate(0, -3 * x, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.translate(0, 3 * x, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			camera.zoom += 0.02 * x;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			camera.zoom -= 0.02 * x;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			camera.rotate(-0.5f * x, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			camera.rotate(0.5f * x, 0, 0, 1);
		}
	}

	@Override
	public void resize(int width, int height) {
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
