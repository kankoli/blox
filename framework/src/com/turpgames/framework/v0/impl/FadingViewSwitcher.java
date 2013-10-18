package com.turpgames.framework.v0.impl;

import com.turpgames.framework.v0.util.Game;

public class FadingViewSwitcher extends ViewSwitcher {

	public FadingViewSwitcher(float duration) {
		super(duration);
	}

	@Override
	protected void renderSwitching(boolean back) {
		float alpha = elapsed / duration;

		Game.renderingAlpha = 1 - alpha;
		oldView.draw();

		Game.renderingAlpha = alpha;
		newView.draw();

		Game.renderingAlpha = 1;
	}
}