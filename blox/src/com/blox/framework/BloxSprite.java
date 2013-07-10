package com.blox.framework;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.blox.World;

public abstract class BloxSprite extends Sprite implements InputDetector, AnimationFinishListener {
	protected Texture defaultTexture;
	protected String defaultAnimation;
	protected BloxAnimation currentAnimation;
	protected Map<String, BloxAnimation> animations;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;
	
	protected Array<Bounds> bounds;
	
	protected SpriteBatch spriteBatch;

	public BloxSprite(SpriteBatch sb) {
		spriteBatch = sb;
		position = new Vector2();
		velocity = new Vector2();
		acceleration = new Vector2();
		animations = new HashMap<String, BloxAnimation>();
		bounds = new Array<Bounds>();
	}
	
	public BloxSprite(SpriteBatch sb, Texture defaultTexture, int width, int height) {
		super(defaultTexture, width, height);

		spriteBatch = sb;
		position = new Vector2();
		velocity = new Vector2();
		acceleration = new Vector2();
		animations = new HashMap<String, BloxAnimation>();
		bounds = new Array<Bounds>();
	}
	
	public void addCircleBounds(float r) {
		bounds.add(new CircleBounds(this, r));
	}

	public void addCircleBounds(float r, Vector2 parentOffset) {
		bounds.add(new CircleBounds(this, r, parentOffset));
	}
	
	public void addRectangleBounds(float width, float height) {
		bounds.add(new RectangleBounds(this, width, height));
	}

	public void addRectangleBounds(float width, float height, float xOffset, float yOffset) {
		bounds.add(new RectangleBounds(this, width, height, xOffset, yOffset));
	}
	
	public static boolean collide(BloxSprite s1, BloxSprite s2) throws Exception {
		if (s1.bounds.size == 0 || s2.bounds.size == 0)
			throw new Exception("There are no bounds added to the sprite");
		for (Bounds b: s1.bounds) {
			if (b.collide(s2.bounds))
				return true;
		}
		return false;
	}
	
	public static boolean collide(BloxSprite s1, Array<BloxSprite> sArray) throws Exception {
		for (BloxSprite s2: sArray) {
			if (collide(s1,s2))
				return true;
		}
		return false;
	}
	
	public static boolean collide(Array<BloxSprite> sArray1, Array<BloxSprite> sArray2) throws Exception {
		for (BloxSprite s1: sArray1) {
			if (collide(s1,sArray2))
				return true;
		}
		return false;
	}

	public Texture getDefaultTexture() {
		return defaultTexture;
	}

	public void setDefaultTexture(Texture dt) {
		defaultTexture = dt;
	}
	
	public String getDefaultAnimation() {
		return defaultAnimation;
	}

	public void setDefaultAnimation(String da) {
		defaultAnimation = da;
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
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(float posX, float posY) {
		position.x = posX;
		position.y = posY;
	}
	
	public void setPosition(Vector2 pos) {
		position = pos;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public Vector2 getCenter() {
		return new Vector2(position.x + getWidth()/2, position.y + getHeight()/2);
	}
	
	abstract public void update(float delta);
	
	/**
	 * Default move function. First, position is updated by adding velocity * delta. 
	 * Then velocity is updated by adding acceleration * delta.
	 * @param delta
	 */
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
