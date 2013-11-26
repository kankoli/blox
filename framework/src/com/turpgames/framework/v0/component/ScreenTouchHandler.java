package com.turpgames.framework.v0.component;

import com.turpgames.framework.v0.impl.GameObject;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Utils;

public class ScreenTouchHandler extends GameObject {
	public ScreenTouchHandler() {
		setWidth(Game.getVirtualWidth());
		setHeight(Game.getVirtualHeight());
	}

	private IScreenTouchListener listener;

	public void activate(IScreenTouchListener listener) {
		listenInput(true);
		this.listener = listener;
	}

	public void deactivate() {
		listenInput(false);
		this.listener = null;
	}

	@Override
	public void draw() {

	}

	@Override
	protected boolean onTap() {
		if (listener != null) {
			listener.onScreenTouched();
			return true;
		}
		return false;
	}

	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Utils.LAYER_SCREEN);
	}
}
