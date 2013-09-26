package com.blox.setgame.model;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.setgame.utils.R;

public class Toolbar implements IDrawable {
	public static interface IToolbarListener {
		void onToolbarBack();
	}

	private ToolbarButton backButton;
	private ToolbarButton settingsButton;

	private OnOffButton soundButton;
	private OnOffButton musicButton;
	private OnOffButton vibrationButton;

	private IToolbarListener listener;

	private String title;

	public Toolbar() {
		final float buttonSize = Game.scale(48);
		final float buttonSpacing = Game.scale(10);

		backButton = new ToolbarButton();
		backButton.setTexture(R.game.textures.toolbar.back);
		backButton.setWidth(buttonSize);
		backButton.setHeight(buttonSize);
		backButton.setLocation(ToolbarButton.AlignNW, buttonSpacing, buttonSpacing);
		backButton.listenInput(true);
		backButton.setListener(new ToolbarButton.IToolbarButtonListener() {
			@Override
			public void onToolbarButtonTapped(ToolbarButton button) {
				if (listener != null)
					listener.onToolbarBack();
			}
		});

		settingsButton = new ToolbarButton();
		settingsButton.setTexture(R.game.textures.toolbar.settings);
		settingsButton.setWidth(buttonSize);
		settingsButton.setHeight(buttonSize);
		settingsButton.setLocation(ToolbarButton.AlignNE, buttonSpacing, buttonSpacing);
		settingsButton.listenInput(true);
		settingsButton.setListener(new ToolbarButton.IToolbarButtonListener() {
			@Override
			public void onToolbarButtonTapped(ToolbarButton button) {
				musicButton.toggle();
				soundButton.toggle();
				vibrationButton.toggle();
			}
		});

		musicButton = new OnOffButton(R.settings.music);
		musicButton.setWidth(buttonSize);
		musicButton.setHeight(buttonSize);
		musicButton.setLocation(ToolbarButton.AlignNE, buttonSize + 3 * buttonSpacing, buttonSpacing);
		musicButton.setTextures(R.game.textures.toolbar.musicStop, R.game.textures.toolbar.musicPlay);

		soundButton = new OnOffButton(R.settings.sound);
		soundButton.setWidth(buttonSize);
		soundButton.setHeight(buttonSize);
		soundButton.setLocation(ToolbarButton.AlignNE, 2 * buttonSize + 4 * buttonSpacing, buttonSpacing);
		soundButton.setTextures(R.game.textures.toolbar.soundOn, R.game.textures.toolbar.soundOff);

		vibrationButton = new OnOffButton(R.settings.vibration);
		vibrationButton.setWidth(buttonSize);
		vibrationButton.setHeight(buttonSize);
		vibrationButton.setLocation(ToolbarButton.AlignNE, 3 * buttonSize + 5 * buttonSpacing, buttonSpacing);
		vibrationButton.setTextures(R.game.textures.toolbar.vibrationOn, R.game.textures.toolbar.vibrationOff);
	}

	public void setListener(IToolbarListener listener) {
		this.listener = listener;
	}

	void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void draw() {
		backButton.draw();
		settingsButton.draw();
		musicButton.draw();
		soundButton.draw();
		vibrationButton.draw();
		TextDrawer.draw(FontManager.defaultFont, title, TextDrawer.AlignN);
	}
}
