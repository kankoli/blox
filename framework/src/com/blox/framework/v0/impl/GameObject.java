package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.blox.framework.v0.IAnimationEndListener;
import com.blox.framework.v0.IBound;
import com.blox.framework.v0.IGameObject;
import com.blox.framework.v0.IMover;
import com.blox.framework.v0.metadata.AnimationMetadata;
import com.blox.framework.v0.metadata.GameMetadata;
import com.blox.framework.v0.util.Animation;
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
	
	protected GameObject() {
		location = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		scale = new Vector(1, 1, 1);
		rotation = new Rotation();

		animator = new Animator();
		animator.registerEndListener(new AnimationEndListener(this));

		bounds = new ArrayList<IBound>();

		mover = IMover.NULL;
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

	protected Animation addAnimation(String animationId) {
		AnimationMetadata metadata = GameMetadata.getAnimation(animationId);
		Animation animation = Animation.fromMetadata(metadata);

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

	protected Animation startAnimation(String animationId) {
		return animator.start(animationId);
	}

	protected void pauseAnimation() {
		animator.pause();
	}

	protected Animation getAnimation() {
		return animator.getAnimation();
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
		Animation curr = getAnimation();
		if (curr != null)
			curr.getFrame().draw(this);
	}

	@Override
	public boolean ignoreViewportOffset() {
		return false;
	}

	@Override
	public boolean ignoreViewportScaling() {
		return false;
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
		// Vector vel = getVelocity();
		// location.x += vel.x;
		// location.y += vel.y;
		// Vector acc = getAcceleration();
		// vel.x += acc.x;
		// vel.y += acc.y;
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

	// endregion
}
