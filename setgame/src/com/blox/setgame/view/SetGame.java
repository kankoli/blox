package com.blox.setgame.view;

import com.blox.framework.v0.impl.BaseGame;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;

public class SetGame extends BaseGame {
	@Override
	public void init() {
		super.init();
		FontManager.defaultFont.setColor(Color.Black);
	}
}
