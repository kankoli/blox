package com.blox.framework;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.blox.World;

public abstract class AnimatedSprite extends Sprite implements InputDetector {
	protected String defaultAnimation;
	protected BloxAnimation currentAnimation;
	protected Map<String, BloxAnimation> animations;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;

	public AnimatedSprite() {
		position = new Vector2();
		velocity = new Vector2();
		acceleration = new Vector2();
		animations = new HashMap<String, BloxAnimation>();
	}

	public String getDefaultAnimation() {
		return defaultAnimation;
	}

	public void setDefaultAnimation(String defaultAnimation) {
		this.defaultAnimation = defaultAnimation;
	}

	public void addAnimation(BloxAnimation animation) {
		animations.put(animation.getName(), animation);
	}

	public void startAnimation(String animationName) {
		if (currentAnimation != null)
			currentAnimation.stopAnimation();
		currentAnimation = animations.get(animationName);
	}

	public void stopAnimation() {
		if (currentAnimation != null)
			currentAnimation.stopAnimation();
		currentAnimation = animations.get(defaultAnimation);
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		super.setX(World.descale(position.x));
		super.setY(World.descale(position.y));

		if (currentAnimation != null) {
			spriteBatch.draw(currentAnimation.getFrame(), getX(), getY());
		} else {
			super.draw(spriteBatch);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
}