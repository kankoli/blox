package com.blox.setgame.model;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.util.Game;

public class Toolbar implements IDrawable {
	public static interface IToolbarListener {
		void onToolbarBack();
	}

	private ToolbarButton backButton;
	private IToolbarListener listener;

	public Toolbar() {
		backButton = new ToolbarButton();
		backButton.setTexture("back");
		backButton.setWidth(Game.scale(196) * 0.3f);
		backButton.setHeight(Game.scale(118) * 0.3f);
		backButton.getLocation().set(Game.scale(10), Game.getScreenHeight() - backButton.getHeight() - Game.scale(10));
		backButton.listenInput(true);
		backButton.setListener(new ToolbarButton.IToolbarButtonListener() {
			@Override
			public void onToolbarButtonTapped(ToolbarButton button) {
				if (listener != null)
					listener.onToolbarBack();
			}
		});
	}

	public void setListener(IToolbarListener listener) {
		this.listener = listener;
	}

	@Override
	public void draw() {
		backButton.draw();
	}
}
