package com.blox.framework.v0.impl;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenFader;
import com.blox.framework.v0.IScreenSwicther;
import com.blox.framework.v0.IScreenSwitchListener;
import com.blox.framework.v0.util.Game;

public class FadingScreenSwitcher implements IScreenSwicther {
	private float elapsed;
	private float duration;

	private IScreen newScreen;
	private IScreen oldScreen;

	private IScreenFader fader;

	private IScreenSwitchListener listener;

	public FadingScreenSwitcher(float duration) {
		this.duration = duration;
		this.elapsed = duration;
		this.fader = Game.getScreenFader();
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
		} else {
			screen.activated();
		}
		this.newScreen = screen;
	}

	@Override
	public void render() {
		elapsed += Game.getDeltaTime();

		if (isSwitching())
			renderFaded();
		else
			endFading();
	}

	@Override
	public void setListener(IScreenSwitchListener listener) {
		this.listener = listener;
	}

	private void endFading() {
		fader.fade(1);
		if (listener != null)
			listener.switchEnd(oldScreen, newScreen);
		newScreen.render();
	}

	private void renderFaded() {
		float alpha = elapsed / duration;

		fader.fade(1 - alpha);
		oldScreen.render();

		fader.fade(alpha);
		newScreen.render();
	}
}