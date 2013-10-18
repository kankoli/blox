package com.turpgames.framework.v0.impl;

import java.util.Iterator;

import com.turpgames.framework.v0.IBound;
import com.turpgames.framework.v0.IMover;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Rotation;
import com.turpgames.framework.v0.util.Vector;

public abstract class GameObjectDecorator extends GameObject {
	private final GameObject gameObject;
	
	public GameObjectDecorator(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	@Override
	public Iterator<IBound> getBounds() {
		return gameObject.getBounds();
	}

	@Override
	public Vector getVelocity() {
		return gameObject.getVelocity();
	}

	@Override
	public Vector getAcceleration() {
		return gameObject.getAcceleration();
	}

	@Override
	public void move() {
		gameObject.move();		
	}

	@Override
	public void beginMove(IMover mover) {
		gameObject.beginMove(mover);
	}

	@Override
	public void stopMoving() {
		gameObject.stopMoving();
	}

	@Override
	public void draw() {
		gameObject.draw();		
	}

	@Override
	public float getWidth() {
		return gameObject.getWidth();
	}

	@Override
	public float getHeight() {
		return gameObject.getHeight();
	}

	@Override
	public Vector getLocation() {
		return gameObject.getLocation();
	}

	@Override
	public Vector getScale() {
		return gameObject.getScale();
	}

	@Override
	public Rotation getRotation() {
		return gameObject.getRotation();
	}

	@Override
	public Color getColor() {
		return gameObject.getColor();
	}

	@Override
	public boolean isFlipX() {
		return gameObject.isFlipX();
	}

	@Override
	public boolean isFlipY() {
		return gameObject.isFlipY();
	}

	@Override
	public boolean ignoreViewport() {
		return gameObject.ignoreViewport();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return gameObject.touchDown(x, y, pointer, button);
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		return gameObject.touchUp(x, y, pointer, button);
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		return gameObject.touchDragged(x, y, pointer);
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return gameObject.tap(x, y, count, button);
	}

	@Override
	public boolean longPress(float x, float y) {
		return gameObject.longPress(x, y);
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		return gameObject.fling(vx, vy, button);
	}

	@Override
	public boolean pan(float x, float y, float dx, float xy) {
		return gameObject.pan(x, y, dx, xy);
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return gameObject.zoom(initialDistance, distance);
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		return gameObject.pinch(p1Start, p2Start, p1End, p2End);
	}

	@Override
	public boolean keyDown(int keycode) {
		return gameObject.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		return gameObject.keyUp(keycode);
	}

	@Override
	public boolean keyTyped(char character) {
		return gameObject.keyTyped(character);
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		return gameObject.mouseMoved(x, y);
	}

	@Override
	public boolean scrolled(float amount) {
		return gameObject.scrolled(amount);
	}
	
	@Override
	protected void flipX() {
		gameObject.flipX();
	}
	
	@Override
	protected void flipY() {
		gameObject.flipY();
	}
	
	@Override
	public void setWidth(float width) {
		gameObject.setWidth(width);
	}
	
	@Override
	public void setHeight(float height) {
		gameObject.setHeight(height);
	}
	
	@Override
	public void listenInput(boolean listen) {
		gameObject.listenInput(listen);
	}
	
	@Override
	public boolean isListeningInput() {
		return gameObject.isListeningInput();
	}
}
