package com.blox.setgame.view;

import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.ScreenManager;
import com.blox.setgame.model.Logo;
import com.blox.setgame.model.Toolbar;
import com.blox.setgame.utils.R;

public abstract class SetGameScreen extends Screen {
	protected ISetGameViewListener screenListener;
	private Toolbar toolbar;

	protected void notifyScreenActivated() {
		if (screenListener != null)
			screenListener.onScreenActivated();
	}

	protected void notifyScreenDeactivated() {
		if (screenListener != null)
			screenListener.onScreenDeactivated();
	}

	protected void notifyDraw() {
		if (screenListener != null)
			screenListener.draw();
	}

	protected void setScreenListener(ISetGameViewListener screenListener) {
		this.screenListener = screenListener;
	}

	@Override
	public void activated() {
		super.activated();
		notifyScreenActivated();
	}

	@Override
	public void deactivated() {
		super.deactivated();
		notifyScreenDeactivated();
	}

	@Override
	public void init() {
		super.init();

		Logo logo = new Logo();
		logo.getColor().a = 0.25f;
		registerDrawable(logo, 1);
		
		toolbar = new Toolbar();
		toolbar.setListener(new Toolbar.IToolbarListener() {
			@Override
			public void onToolbarBack() {
				onBack();				
			}
		});

		registerInputListener(this);
	}

	@Override
	public void render() {
		super.render();
		
		if (super.isActive())
			notifyDraw();
		
		toolbar.draw();
	}

	@Override
	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.game.screens.menu, true);
		return true;
	}
}