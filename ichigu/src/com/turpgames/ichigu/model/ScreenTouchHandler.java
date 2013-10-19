package com.turpgames.ichigu.model;

import com.turpgames.framework.v0.util.Game;

public class ScreenTouchHandler extends IchiguObject {
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
}
