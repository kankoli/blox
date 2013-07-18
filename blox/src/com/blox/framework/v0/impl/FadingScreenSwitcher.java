package com.blox.framework.v0.impl;

import com.blox.framework.v0.IScreen;
import com.blox.framework.v0.IScreenSwicther;
import com.blox.framework.v0.util.Game;

public class FadingScreenSwitcher implements IScreenSwicther {
	private float elapsed;
	private float duration;
	
	private IScreen newScreen;
	private IScreen oldScreen;
	
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
		}
		else {
			screen.show();
		}
		this.newScreen = screen;
	}

	@Override
	public void render() {
		elapsed += Game.getDeltaTime();
		
		if (!isSwitching()) {
			Game.getScreenFader().fade(1);
			oldScreen.hide();
			newScreen.show();
			newScreen.render();
			return;
		}
		
		float alpha = elapsed / duration;

		Game.getScreenFader().fade(1 - alpha);
		oldScreen.render();

		Game.getScreenFader().fade(alpha);
		newScreen.render();
	}		
}