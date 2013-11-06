package com.turpgames.framework.v0.component;

import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.TextureDrawer;
import com.turpgames.framework.v0.util.Utils;

public class ImageButton extends Button {
	private ITexture texture;

	public ImageButton(float width, float height, String id) {
		this(width, height, id, Color.white(), Color.white());
	}

	public ImageButton(float width, float height, String id, Color defaultColor, Color touchedColor) {
		this(width, height, defaultColor, touchedColor);
		setTexture(id);
	}

	public ImageButton(float width, float height, Color defaultColor, Color touchedColor) {
		super(defaultColor, touchedColor);
		setWidth(Game.scale(width));
		setHeight(Game.scale(height));
	}

	public void setTexture(String textureId) {
		texture = Game.getResourceManager().getTexture(textureId);
	}

	@Override
	protected void onDraw() {
		TextureDrawer.draw(texture, this);
	}

	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Utils.LAYER_SCREEN);
	}
}
