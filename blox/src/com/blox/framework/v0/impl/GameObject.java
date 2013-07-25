package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.blox.framework.v0.IAnimationEndListener;
import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.IDrawer;
import com.blox.framework.v0.IGameObject;
import com.blox.framework.v0.IMover;
import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.util.Animation;
import com.blox.framework.v0.util.AnimationBuilder;
import com.blox.framework.v0.util.Animator;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

public abstract class GameObject implements IGameObject {
	protected float width;
	protected float height;
	protected Vector location;
	protected Vector velocity;
	protected Vector acceleration;
	protected Vector scale;
	protected Rotation rotation;
	protected boolean flipX;
	protected boolean flipY;

	protected Animator animator;
	protected List<IBound> bounds;
	protected IMover mover;
	protected IDrawer drawer;
	
	protected Screen parent;

	protected GameObject() {
		this(null);
	}
	
	protected GameObject(Screen p) {
		parent = p;
		location = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		scale = new Vector(1, 1, 1);
		rotation = new Rotation();

		animator = new Animator();
		animator.registerEndListener(new AnimationEndListener(this));

		bounds = new ArrayList<IBound>();

		mover = IMover.NULL;
		drawer = IDrawer.NULL;
	}
	
	// region animations

	private class AnimationEndListener implements IAnimationEndListener {
		private GameObject gameObj;

		AnimationEndListener(GameObject gameObj) {
			this.gameObj = gameObj;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			gameObj.onAnimationEnd(animation);
		}
	}

	protected Animation addAnimation(String name, String resourcePath,
			float frameDuration, int frameWidth, int frameHeight) {
		return addAnimation(name, resourcePath, frameDuration, frameWidth, frameHeight, false);
	}
	
	protected Animation addAnimation(String name, String resourcePath,
			float frameDuration, int frameWidth, int frameHeight, boolean isLooping) {
		Animation animation = AnimationBuilder.createAnimation(name)
				.from(resourcePath).withFrameDuration(frameDuration)
				.withFrameSize(frameWidth, frameHeight).setLooping(isLooping).build();

		animator.addAnimation(animation);

		return animation;
	}
	protected void removeAnimation(String name) {
		animator.removeAnimation(name);
	}

	protected void onAnimationEnd(Animation animation) {

	}

	protected void stopAnimation() {
		animator.stop();
	}

	protected Animation startAnimation() {
		return startAnimation(false);
	}

	protected Animation startAnimation(boolean forceRestart) {
		return animator.start(forceRestart);
	}

	protected Animation startAnimation(String name) {
		return animator.start(name);
	}

	protected void pauseAnimation() {
		animator.pause();
	}

	protected Animation getAnimation() {
		return animator.getAnimation();
	}

	// endregion

	// region input handling

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
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
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
	public boolean fling(float vx, float vy, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End,
			Vector p2End) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		// TODO Auto-generated method stub
		return false;
	}

	// endregion

	// region IDrawable

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public Vector getScale() {
		return scale;
	}

	@Override
	public boolean isFlipX() {
		return flipX;
	}

	@Override
	public boolean isFlipY() {
		return flipY;
	}

	@Override
	public void draw() {
		getAnimation().getFrame().draw(this);
	}

	@Override
	public void setDrawer(IDrawer drawer) {
		this.drawer = drawer;
	}

	protected void flipX() {
		flipX = !flipX;
	}

	protected void flipY() {
		flipY = !flipY;
	}

	// endregion

	// region IMovable
	@Override
	public Vector getVelocity() {
		return velocity;
	}

	@Override
	public Vector getAcceleration() {
		return acceleration;
	}

	@Override
	public void move() {
		mover.move(this);
//		Vector vel = getVelocity();
//		location.x += vel.x;
//		location.y += vel.y;
//		Vector acc = getAcceleration();
//		vel.x += acc.x;
//		vel.y += acc.y;
	}
	
	@Override
	public void setMover(IMover mover) {
		this.mover = mover;
	}

	// endregion

	// region ICollidable

	@Override
	public Iterator<IBound> getBounds() {
		return bounds.iterator();
	}

	@Override
	public boolean onCollide(IBound thisBound, IBound thatBound, ICollidable obj) {
		return false;
	}

	// endregion

	// region IMovable & IDrawable Common

	@Override
	public Vector getLocation() {
		return location;
	}
	
	// endregion
	
	// region ICollidable & IDrawable Common

	@Override
	public Rotation getRotation() {
		return rotation;
	}
	
	@Override
	public void setRotation(Rotation r) {
		rotation = r;
	}
	
	// endregion

	public Screen getParent() {
		return parent;
	}

}
