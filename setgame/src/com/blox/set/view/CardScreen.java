package com.blox.set.view;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.util.Game;
import com.blox.set.model.FullGameTable;

public class CardScreen extends Screen {

	private FullGameTable gameTable;
	
	@Override
	public void init() {
		super.init();
		gameTable = new FullGameTable();
		super.registerDrawable(gameTable, 1);
	}
	
	@Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK || keycode == Keys.ESCAPE)
    		Game.exit();
    	return super.keyDown(keycode);
    }
}
