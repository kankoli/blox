package com.blox.setgame.model;

import com.blox.framework.v0.IDrawable;

public abstract class SetGameTable implements IDrawable {
	protected abstract Card[] getCardsOnTable();

	@Override
	public void draw() {
		Card[] cc = getCardsOnTable();
		for (int i = 0; i < cc.length; i++)
			cc[i].draw();
	}
}
