package com.blox.framework;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class CustomInputProcessor implements InputProcessor {
	private List<InputDetector> detectors;

	public CustomInputProcessor() {
		detectors = new ArrayList<InputDetector>();
	}

	public void register(InputDetector detector) {
		detectors.add(detector);
	}
	
	public void unregister(InputDetector detector) {
		detectors.remove(detector);
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
		Gdx.app.log("InputProcessor", "touchDown");
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
