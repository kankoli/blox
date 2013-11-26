package com.turpgames.framework.v0.component;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.impl.Settings;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TextureDrawer;

public class ToggleButton extends ImageButton {
	private ITexture textureOff;
	private String settingsKey;
	private boolean isOn;
	private Color onColor;
	private Color offColor;

	public ToggleButton(float width, float height, String settingsKey,
			String onTextureId, String offTextureId, Color onColor,
			Color offColor) {
		super(width, height, onColor, onColor);
		this.settingsKey = settingsKey;
		this.isOn = Settings.getBoolean(settingsKey, true);
		this.textureOff = Game.getResourceManager().getTexture(offTextureId);
		setTexture(onTextureId);
		this.onColor = onColor;
		this.offColor = offColor;
	}

	@Override
	protected void onDraw() {
		if (isOn) {
			getColor().set(this.onColor);
			super.onDraw();
		} else {
			getColor().set(this.offColor);
			TextureDrawer.draw(textureOff, this);
		}
	}

	@Override
	protected boolean onTap() {
		isOn = !isOn;
		Settings.putBoolean(settingsKey, isOn);
		return super.onTap();
	}
}
