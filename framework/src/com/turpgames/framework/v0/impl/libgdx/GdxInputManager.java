package com.turpgames.framework.v0.impl.libgdx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.turpgames.framework.v0.IInputListener;
import com.turpgames.framework.v0.IInputManager;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Layer;
import com.turpgames.framework.v0.util.Utils;
import com.turpgames.framework.v0.util.Vector;

/***
 * 'dialogListeners' is populated by listeners from dialogs. This list has precedence over 'listeners'; if it's not empty, 
 * the events are passed through this list instead of 'listeners'
 * 
 * getTempListeners() function provides temporary 'listeners' list in case of in-loop removal of elements.
 * Added after the issue of exit dialog appearing after hitting hardware back button (twice) to go back to MenuScreen -playForm and then menuForm.
 * @author kadirello
 *
 */
class GdxInputManager implements IInputManager, IInputListener {
	private List<Layer<IInputListener>> layers;
	private InputMultiplexer multiplexer;
	private boolean listening;

	GdxInputManager() {
		layers = new ArrayList<Layer<IInputListener>>();

		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new GdxInputProcessor(this));
		multiplexer.addProcessor(new GestureDetector(new GdxGestureListener(this)));
	}

	@Override
	public void activate() {
		Gdx.input.setInputProcessor(multiplexer);
		listening = true;
	}

	@Override
	public void deactivate() {
		Gdx.input.setInputProcessor(null);
		listening = false;
	}

	@Override
	public boolean isActive() {
		return listening;
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
			layer.unregister(obj);
		}
	}

	@Override
	public float getX() {
		return Game.screenToViewportX(Gdx.input.getX());
	}

	@Override
	public float getY() {
		return Game.screenToViewportY(Game.getScreenHeight() - Gdx.input.getY());
	}

	@Override
	public boolean isTouched() {
		return Gdx.input.isTouched();
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
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);

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
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);

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
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);

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
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);

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
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);

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
		vx = Game.screenToViewportX(vx);
		vy = Game.screenToViewportY(vy);

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
		x = Game.screenToViewportX(x);
		y = Game.screenToViewportY(Game.getScreenHeight() - y);

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

		Game.screenToViewport(p1Start);
		Game.screenToViewport(p2Start);
		Game.screenToViewport(p1End);
		Game.screenToViewport(p2End);

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
//		x = Game.screenToViewportX(x);
//		y = Game.screenToViewportY(Game.getScreenHeight() - y);
//
//		List<IInputListener> tempList = getTempListeners();
//		
//		for (int i = tempList.size() - 1; i >= 0 && i < tempList.size(); i--) {
//			IInputListener listener = tempList.get(i);
//			if (listener.mouseMoved(x, y))
//				return true;
//		}
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

	@Override
	public boolean isDialogActive() {
		for (int i = 0; i < layers.size(); i++) {
			if (layers.get(i).getIndex() == Utils.LAYER_DIALOG && layers.get(i).getObjects().size() > 0)
				return true;
		}
		return false;
	}
}
