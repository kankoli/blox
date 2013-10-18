package com.turpgames.framework.v0.impl;

import com.turpgames.framework.v0.IView;
import com.turpgames.framework.v0.IViewFinder;
import com.turpgames.framework.v0.IViewSwitcher;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Utils;

public abstract class ViewSwitcher implements IViewSwitcher {
	protected float elapsed;
	protected float duration;

	protected IView newView;
	protected IView oldView;

	protected IViewFinder viewFinder;
	private boolean back;
	private boolean requiresEndNotify;

	public ViewSwitcher(float duration) {
		this.duration = duration;
		this.elapsed = duration;
	}

	private boolean isSwitching() {
		return elapsed < duration;
	}

	private void cancelSwitching() {
		elapsed = duration + 100;
		requiresEndNotify = false;
	}

	@Override
	public boolean switchTo(String id, boolean back) {
		this.back = back;
		this.requiresEndNotify = true;

		IView view = viewFinder.findView(id);
		if (view == newView)
			return true;

		if (newView != null) {
			elapsed = 0;

			IView currentView = this.newView;

			if (currentView != null && !currentView.deactivate()) {
				cancelSwitching();
				return false;
			}
			else {
				this.oldView = currentView;
				this.newView = view;
				return true;
			}
		}
		else {
			this.newView = view;
			onSwitchEnd();
			return true;
		}
	}

	@Override
	public void draw() {
		elapsed += Game.getDeltaTime();

		if (isSwitching())
			renderSwitching(back);
		else {
			if (requiresEndNotify) {
				onSwitchEnd();
				requiresEndNotify = false;
			}
			newView.draw();
		}
	}

	@Override
	public void setViewFinder(IViewFinder finder) {
		this.viewFinder = finder;
	}

	protected void onSwitchEnd() {
		newView.activate();
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
