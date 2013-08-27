package com.blox.framework.v0.impl;

import com.blox.framework.v0.IView;
import com.blox.framework.v0.IViewFinder;
import com.blox.framework.v0.IViewSwitcher;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Utils;

public abstract class ViewSwitcher implements IViewSwitcher {
	protected float elapsed;
	protected float duration;

	protected IView newView;
	protected IView oldView;

	protected IViewFinder viewFinder;
	private boolean back;

	public ViewSwitcher(float duration) {
		this.duration = duration;
		this.elapsed = duration;
	}

	@Override
	public boolean isSwitching() {
		return elapsed < duration;
	}

	@Override
	public void switchTo(String id, boolean back) {
		this.back = back;
		
		IView view = viewFinder.findView(id);
		if (view == newView)
			return;

		if (newView != null) {
			elapsed = 0;
			this.oldView = this.newView;
			this.newView = view;
		}
		else {
			this.newView = view;
			onSwitchEnd(false);
		}
	}

	@Override
	public void render() {
		elapsed += Game.getDeltaTime();

		if (isSwitching())
			renderSwitching(back);
		else
			onSwitchEnd(true);
	}

	@Override
	public void setViewFinder(IViewFinder finder) {
		this.viewFinder = finder;
	}

	protected void onSwitchEnd(boolean forceRender) {
		if (oldView != null)
			oldView.deactivated();
		newView.activated();

		if (forceRender)
			newView.render();
	}

	protected abstract void renderSwitching(boolean back);

	public static IViewSwitcher createInstance(String key) {
		if (key.startsWith("fading,")) {
			String duration = key.substring(7);
			return new FadingViewSwitcher(Utils.parseFloat(duration));
		}
		else if (key.startsWith("sliding,")) {
			String duration = key.substring(8);
			return new SlidingViewSwitcher(Utils.parseFloat(duration));
		}
		return new DefaultViewSwitcher();
	}
}
