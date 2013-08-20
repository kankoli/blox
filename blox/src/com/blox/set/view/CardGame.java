package com.blox.set.view;

import com.blox.framework.v0.impl.BaseGame;

public class CardGame extends BaseGame {
	private CardScreen cardScreen;
	
	@Override
	public void init() {
		super.init();
		cardScreen = new CardScreen();
		cardScreen.init();
		setScreen(cardScreen);
	}
	
	@Override
	public float getVirtualHeight() {
		return 800;
	}

	@Override
	public float getVirtualWidth() {
		return 550;
	}

}
