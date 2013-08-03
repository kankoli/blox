package com.blox.test.camera;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.blox.framework.v0.util.Viewport;

public class ViewportGame implements ApplicationListener {

	private final static float viewportWidth = 500f;
	private final static float viewportHeight = 800f;

	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private Texture texture;

	private float x, y, r;
	private float vx, vy;
	private Viewport viewport;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);

		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();

		texture = new Texture(Gdx.files.internal("viewport.png"));

		shapeRenderer.setColor(Color.BLACK);

		x = 10;
		y = 10;
		r = 10;
		vx = 210;
		vy = 190;

		viewport = Viewport.create(viewportWidth, viewportHeight, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

	}

	@Override
	public void render() {
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
		else if (x > viewportWidth - r) {
			x = viewportWidth - r;
			vx = -vx;
		}
		if (y < r) {
			y = r;
			vy = -vy;
		}
		else if (y > viewportHeight - r) {
			y = viewportHeight - r;
			vy = -vy;
		}
	}

	private void draw() {
		Gdx.gl.glClearColor(0.5f, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		spriteBatch.begin();
		spriteBatch.draw(texture,
				0 * viewport.getScale() + viewport.getOffset().x,
				0 * viewport.getScale() + viewport.getOffset().y, 
				viewport.getScale() * texture.getWidth(), 
				viewport.getScale() * texture.getHeight());
		spriteBatch.end();

		shapeRenderer.begin(ShapeType.FilledCircle);
		shapeRenderer.filledCircle(
				x * viewport.getScale() + viewport.getOffset().x,
				y * viewport.getScale() + viewport.getOffset().y,
				r * viewport.getScale());
		shapeRenderer.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		viewport = Viewport.create(viewportWidth, viewportHeight, width, height);
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
