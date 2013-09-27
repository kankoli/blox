package com.blox.setgame.model;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.impl.Settings;
import com.blox.framework.v0.util.Game;

class OnOffButton extends ToolbarButton {
	private ITexture textureOn;
	private ITexture textureOff;
	private String settingsKey;
	private boolean isOn;
	private boolean isVisible;

	OnOffButton(String settingsKey) {
		this.settingsKey = settingsKey;
		this.isOn = Settings.getBoolean(settingsKey, true);
	}
	
	void setTextures(String on, String off) {
		textureOn = Game.getResourceManager().getTexture(on);
		textureOff = Game.getResourceManager().getTexture(off);
	}

	@Override
	public void draw() {
		if (isVisible) {
			onDraw(isOn ? textureOn : textureOff);
		}
	}

	@Override
	protected boolean onTap() {
		isOn = !isOn;
		Settings.putBoolean(settingsKey, isOn);
		return super.onTap();
	}

	void toggle() {
		listenInput(isVisible = !isVisible);
	}
}
