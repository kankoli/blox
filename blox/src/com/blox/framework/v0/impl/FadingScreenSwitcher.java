package com.blox.framework.v0.impl;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenSwicther;
import com.blox.framework.v0.IScreenSwitchListener;
import com.blox.framework.v0.util.Game;

public class FadingScreenSwitcher implements IScreenSwicther {
	private float elapsed;
	private float duration;

	private IScreen newScreen;
	private IScreen oldScreen;

	private IScreenSwitchListener listener;

	public FadingScreenSwitcher(float duration) {
		this.duration = duration;
		this.elapsed = duration;
	}

	@Override
	public boolean isSwitching() {
		return elapsed < duration;
	}

	@Override
	public void switchTo(IScreen screen) {
		if (screen == newScreen)
			return;

		if (newScreen != null) {
			elapsed = 0;
			this.oldScreen = this.newScreen;
			this.newScreen = screen;
		} else {
			this.newScreen = screen;
			endFading(false);
		}
	}

	@Override
	public void render() {
		elapsed += Game.getDeltaTime();

		if (isSwitching())
			renderFaded();
		else
			endFading(true);
	}

	@Override
	public void setListener(IScreenSwitchListener listener) {
		this.listener = listener;
	}

	private void endFading(boolean forceRender) {
		if (listener != null)
			listener.switchEnd(oldScreen, newScreen);
		if (forceRender)
			newScreen.render();
	}

	private void renderFaded() {
		float tmp = Game.renderingAlpha;
		
		float alpha = elapsed / duration;

		Game.renderingAlpha = 1 - alpha;
		oldScreen.render();

		Game.renderingAlpha = alpha;
		newScreen.render();
		
		Game.renderingAlpha = tmp;
	}
}