package com.blox.test.mummy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class CompositeInputDetector extends GestureDetector implements GestureListener {
	private List<InputDetector> detectors;
	
	public CompositeInputDetector() {
		this(new GestureListenerX());
	}
	
	private CompositeInputDetector(GestureListenerX gx) {
		super(gx);
		gx.setParent(this);
		detectors = new ArrayList<InputDetector>();
	}
	
	public void register(InputDetector detector) {
		detectors.add(detector);
	}
	
	public void unregister(InputDetector detector) {
		detectors.remove(detector);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		for (InputDetector detector : detectors)
			detector.touchDown(x, y, pointer, button);
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		for (InputDetector detector : detectors)
			detector.tap(x, y, count, button);
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		for (InputDetector detector : detectors)
			detector.longPress(x, y);
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		for (InputDetector detector : detectors)
			detector.fling(velocityX, velocityY, button);
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		for (InputDetector detector : detectors)
			detector.pan(x, y, deltaX, deltaY);
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		for (InputDetector detector : detectors)
			detector.zoom(initialDistance, distance);
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		for (InputDetector detector : detectors)
			detector.pinch(initialPointer1, initialPointer2, pointer1, pointer2);
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		for (InputDetector detector : detectors)
			detector.keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for (InputDetector detector : detectors)
			detector.keyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		for (InputDetector detector : detectors)
			detector.keyTyped(character);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for (InputDetector detector : detectors)
			detector.touchDown(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for (InputDetector detector : detectors)
			detector.touchUp(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for (InputDetector detector : detectors)
			detector.touchDragged(screenX, screenY, pointer);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		for (InputDetector detector : detectors)
			detector.mouseMoved(screenX, screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		for (InputDetector detector : detectors)
			detector.scrolled(amount);
		return false;
	}
}
