package com.blox.framework;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class CustomGestureListener implements GestureListener {
	private List<InputDetector> detectors;

	public CustomGestureListener() {
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
		Gdx.app.log("GestureListener",  "touchDown");
		for (InputDetector detector : detectors)
			detector.touchDown(x, y, pointer, button);
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Gdx.app.log("GestureListener", "tap");
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

}