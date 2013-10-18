package com.turpgames.framework.v0.impl;

public class DefaultViewSwitcher extends ViewSwitcher {
	public DefaultViewSwitcher() {
		super(0);
	}

	@Override
	protected void renderSwitching(boolean back) {
		newView.draw();
	}
}