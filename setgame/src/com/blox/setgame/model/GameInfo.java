package com.blox.setgame.model;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;

class GameInfo extends SetGameObject {
	private String text;
	private int alignment;
	private int shiftY;
	private IFont font;

	GameInfo(float paddingX, float paddingY) {
		getLocation().set(paddingX, paddingY);
		setWidth(Game.getVirtualWidth() - 2 * paddingX);
		setHeight(Game.getVirtualHeight() - 2 * paddingY);
		getColor().set(Color.white());
		
		font = FontManager.createDefaultFontInstance();
		font.setSize(18);
	}

	@Override
	public void draw() {
		Game.setRenderingShift(0, shiftY, false);
		TextDrawer.draw(font, text, this, alignment);
		Game.resetRenderingShift();
	}

	public void draw(String text, int alingment, int shiftY) {
		this.alignment = alingment;
		this.text = text;
		this.shiftY = shiftY;
		this.draw();
	}
}