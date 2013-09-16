package com.blox.setgame.model;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;

class GameInfo extends GameObject {
	private String text;
	private int alignment;
	private int shiftY;

	public GameInfo(float paddingX, float paddingY) {
		getLocation().set(paddingX, paddingY);
		setWidth(Game.getVirtualWidth() - 2 * paddingX);
		setHeight(Game.getVirtualHeight() - 2 * paddingY);
		getColor().set(Color.white());
	}

	@Override
	public void draw() {
		Game.renderingShiftY = shiftY;
		TextDrawer.draw(FontManager.defaultFont, text, this, alignment);
		Game.renderingShiftY = 0;
	}

	public void draw(String text, int alingment, int shiftY) {
		this.alignment = alingment;
		this.text = text;
		this.shiftY = shiftY;
		this.draw();
	}
}