package com.turpgames.framework.v0.impl;

import java.util.ArrayList;
import java.util.List;

import com.turpgames.framework.v0.ICompositeInputListener;
import com.turpgames.framework.v0.IInputListener;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Layer;
import com.turpgames.framework.v0.util.Utils;
import com.turpgames.framework.v0.util.Vector;

public class CompositeInputListener implements ICompositeInputListener {

	private List<Layer<IInputListener>> layers;
	private boolean listening;

	public CompositeInputListener() {		
		layers = new ArrayList<Layer<IInputListener>>();
	}
	
	@Override
	public void register(IInputListener obj, int layerIndex) {
		unregister(obj);

		int i = 0;
		for (; i < layers.size(); i++) {
			Layer<IInputListener> layer = layers.get(i);
			if (layerIndex == layer.getIndex()) {
				layer.register(obj);
				return;
			}
			if (layerIndex < layer.getIndex()) {
				break;
			}
		}
		Layer<IInputListener> newLayer = new Layer<IInputListener>(layerIndex);
		newLayer.register(obj);
		layers.add(i, newLayer);
	}

	@Override
	public void unregister(IInputListener obj) {
		for (Layer<IInputListener> layer : layers) {
			if (layer.unregister(obj))
				return;
		}
	}

	@Override
	public void activate() {
		if (!listening)
			Game.getInputManager().register(this, Utils.LAYER_SCREEN);
		listening = true;
	}
	
	@Override
	public void deactivate() {
		if (listening)
			Game.getInputManager().unregister(this);
		listening = false;
	}

	@Override
	public boolean isActive() {
		return listening;
	}
	
	private List<IInputListener> getTempListeners() {
		List<IInputListener> tempList = new ArrayList<IInputListener>();
		for (int i = 0; i < layers.size(); i++)
			for (IInputListener listener : layers.get(i).getObjects())
				tempList.add(listener);
		return tempList;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
		
			if (listener.touchDown(x, y, pointer, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(float x, float y, int pointer, int button) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.touchUp(x, y, pointer, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(float x, float y, int pointer) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.touchDragged(x, y, pointer))
				return true;
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.tap(x, y, count, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.longPress(x, y))
				return true;
		}
		return false;
	}

	@Override
	public boolean fling(float vx, float vy, int button) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.fling(vx, vy, button))
				return true;
		}
		return false;
	}

	@Override
	public boolean pan(float x, float y, float dx, float dy) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.pan(x, y, dx, dy))
				return true;
		}
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.zoom(Game.scale(initialDistance), Game.scale(distance)))
				return true;
		}
		return false;
	}

	@Override
	public boolean pinch(Vector p1Start, Vector p2Start, Vector p1End, Vector p2End) {
		p1Start.y = Game.getScreenHeight() - p1Start.y;
		p2Start.y = Game.getScreenHeight() - p2Start.y;
		p1End.y = Game.getScreenHeight() - p1End.y;
		p2End.y = Game.getScreenHeight() - p2End.y;
		
		List<IInputListener> tempList = getTempListeners();

		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.pinch(p1Start, p2Start, p1End, p2End))
				return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(float x, float y) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.mouseMoved(x, y))
				return true;
		}
		return false;
	}

	@Override
	public boolean scrolled(float amount) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.scrolled(amount))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.keyDown(keycode))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.keyUp(keycode))
				return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		List<IInputListener> tempList = getTempListeners();
		
		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
			IInputListener listener = tempList.get(i);
			if (listener.keyTyped(character))
				return true;
		}
		return false;
	}
}
