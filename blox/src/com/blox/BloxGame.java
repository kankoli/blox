package com.blox;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.blox.entity.Maze2D;
import com.blox.entity.Room2D;
import com.blox.test.Ball;

public class BloxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private BitmapFont font;
	private ScaledShapeRenderer shapeRenderer;
	private BloxGestureListener gestureListener;

	private float deltaT;
	private long lastRender;
	private final float friction = 0.25f;
	private int screenWidth;
	private int screenHeight;
	private float worldWidth;
	private float worldHeight;

	private boolean manualMove;

	private Maze2D maze;
	private Ball ball;

	@Override
	public void create() {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, screenHeight / screenWidth);
		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(Color.WHITE);

		shapeRenderer = new ScaledShapeRenderer(new ShapeRenderer());
		gestureListener = new BloxGestureListener();
		Gdx.input.setInputProcessor(new GestureDetector(gestureListener));

		World.scale = 1 / 80f;
		worldWidth = World.scale(screenWidth);
		worldHeight = World.scale(screenHeight);

		buildMaze();
		ball = new Ball(worldWidth / 2, worldHeight / 2, worldWidth / 30);
		ball.a = new Vector2(0, 0);
		ball.v = new Vector2(0, 0);
		lastRender = TimeUtils.nanoTime();
	}

	private void buildMaze() {
		maze = new Maze2D(Room2D.rect(1, 1, 40, 40), Room2D.rect(1, 41, 40, 80));
		maze.addRoom(Room2D.rect(41, 1, 80, 40));
	}

	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
	}

	@Override
	public void render() {
		long t = TimeUtils.nanoTime();
		long dt = t - lastRender;
		lastRender = t;
		deltaT = dt / 1000000000f; // nanosec -> sec

		String info = "";

		float ax = Gdx.input.getAccelerometerX();
		float ay = Gdx.input.getAccelerometerY();

		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		boolean vibrate = false;

		if (Gdx.input.isTouched()) {
			int tx = Gdx.input.getX();
			int ty = screenHeight - Gdx.input.getY();

			if (!manualMove) {
				float px = World.descale(ball.position.x);
				float py = World.descale(ball.position.y);
				float f1 = (float) Math.sqrt((px - tx) * (px - tx) + (py - ty)
						* (py - ty));
				float f2 = World.descale(ball.radius);
				manualMove = f1 < 2 * f2;
			}

			if (manualMove) {
				ball.v.x = ball.v.y = ball.a.y = ball.a.y = 0;
				ball.position.x = World.scale(tx);
				ball.position.y = World.scale(ty);
			}
		}

		FlingInfo fi;
		if (manualMove && (fi = gestureListener.getFling()) != null) {
			ball.v.x = World.scale(fi.velocity.x);
			ball.v.y = World.scale(-fi.velocity.y);
			info = "fling";
			manualMove = false;
		}

		if (!Gdx.input.isTouched()) {
			manualMove = false;
		}

		if (!manualMove) {
			ball.a.x = -(ax / 10) * World.gravity;
			ball.a.y = -(ay / 10) * World.gravity;
			ball.move(deltaT, friction);
		}

		if (ball.position.y < ball.radius) {
			ball.v.y *= -1;
			ball.position.y = ball.radius;
		} else if (ball.position.y > worldHeight - ball.radius) {
			ball.v.y *= -1;
			ball.position.y = worldHeight - ball.radius;
		}
		if (ball.position.x < ball.radius) {
			ball.v.x *= -1;
			ball.position.x = ball.radius;
		} else if (ball.position.x > worldWidth - ball.radius) {
			ball.v.x *= -1;
			ball.position.x = worldWidth - ball.radius;
		}

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.drawMultiLine(batch, info, 10, screenHeight - 10);
		shapeRenderer.setColor(255);
		shapeRenderer.circle(ball.position, ball.radius);
		batch.end();

		if (vibrate)
			Gdx.input.vibrate(100);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
