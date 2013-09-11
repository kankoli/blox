package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.IInputListener;
import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.IMover;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

public abstract class GameObject implements IInputListener, IDrawingInfo, IDrawable, IMovable, ICollidable {
	protected float width;
	protected float height;
	protected Vector location;
	protected Vector velocity;
	protected Vector acceleration;
	protected Vector scale;
	protected Color color;
	protected Rotation rotation;
	protected boolean flipX;
	protected boolean flipY;

	protected boolean isListeningInput;
	protected List<IBound> bounds;
	protected IMover mover;

	protected GameObject() {
		location = new Vector();
		velocity = new Vector();
		acceleration = new Vector();
		scale = new Vector(1, 1, 1);
		color = Color.white();
		rotation = new Rotation();

		bounds = new ArrayList<IBound>();

		mover = IMover.NULL;
	}

	// region IInputListener

	public boolean isListeningInput() {
		return isListeningInput;
	}

	public void listenInput(boolean listen) {
		if (listen && !isListeningInput)
			Game.getInputManager().register(this);
		else if (!listen && isListeningInput)
			Game.getInputManager().unregister(this);
		isListeningInput = listen;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		return false;
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		return false;
	}

	// endregion

	// region IDrawingInfo & IDrawable

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
	public Color getColor() {
		return color;
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
