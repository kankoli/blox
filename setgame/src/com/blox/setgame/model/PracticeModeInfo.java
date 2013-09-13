package com.blox.setgame.model;

import com.blox.framework.v0.impl.GameObject;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;

class PracticeModeInfo extends GameObject {
	private static final PracticeModeInfo info = new PracticeModeInfo();

	private String text;
	private int alignment;
	private int shiftY;

	private PracticeModeInfo() {
		getLocation().set(50, 25);
		setWidth(Game.getVirtualWidth() - 100);
		setHeight(Game.getVirtualHeight() - 50);
		getColor().set(Color.white());
	}

	@Override
	public void draw() {
		Game.renderingShiftY = shiftY;
		TextDrawer.draw(FontManager.defaultFont, text, this, alignment);
		Game.renderingShiftY = 0;
	}

	public static void draw(String text, int alingment, int shiftY) {
		info.alignment = alingment;
		info.text = text;
		info.shiftY = shiftY;
		info.draw();
	}
}