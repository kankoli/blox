package com.blox.framework;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.blox.World;

public abstract class BloxSprite extends Sprite implements InputDetector, AnimationFinishListener {
	protected Texture defaultTexture;
	protected String defaultAnimation;
	protected BloxAnimation currentAnimation;
	protected Map<String, BloxAnimation> animations;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;
	
	protected Enum currentState;

	protected SpriteBatch spriteBatch;

	public BloxSprite(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
		this.position = new Vector2();
		this.velocity = new Vector2();
		this.acceleration = new Vector2();
		this.animations = new HashMap<String, BloxAnimation>();
	}
	
	public BloxSprite(SpriteBatch spriteBatch, Texture defaultTexture, int width, int height) {
		super(defaultTexture, width, height);

		this.spriteBatch = spriteBatch;
		this.position = new Vector2();
		this.velocity = new Vector2();
		this.acceleration = new Vector2();
		this.animations = new HashMap<String, BloxAnimation>();
	}

	public Texture getDefaultTexture() {
		return defaultTexture;
	}

	public void setDefaultTexture(Texture defaultTexture) {
		this.defaultTexture = defaultTexture;
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
	
	public String getCurrentAnimation() {
		return currentAnimation.getName();
	}
	
	public void setPosition(float posX, float posY) {
		this.position.x = posX;
		this.position.y = posY;
	}
	
	abstract public void update(float delta);
	
	public void move(float delta) {
		position.x += velocity.x * delta;
		position.y += velocity.y * delta;

		velocity.add(acceleration.tmp().mul(delta));
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
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}
	
	@Override
	public void onAnimationFinished(BloxAnimation animation) {
		
	}
}
