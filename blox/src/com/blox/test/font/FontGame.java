package com.blox.test.font;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class FontGame implements ApplicationListener {
	BitmapFont font;

	SpriteBatch batch;
	ShapeRenderer shape;

	@Override
	public void create() {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/open-sans.ttf"));
		font = generator.generateFont(36);
		generator.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);


		drawText("Set Game", 100, 100, 1f);
		

	}
	
	private void drawText(String text, float x, float y, float fontScale) {
		batch.begin();
		font.setScale(fontScale);
		font.draw(batch, text, x, y);
		TextBounds bounds = font.getBounds(text);
		batch.end();
		shape.begin(ShapeType.Rectangle);
		shape.setColor(1, 0, 0, 1);
		shape.rect(x, y-bounds.height, bounds.width, bounds.height);
		shape.end();
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
		font.dispose();
		batch.dispose();
		shape.dispose();
	}

}
