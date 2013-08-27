package com.blox.set.view;

import com.badlogic.gdx.Input.Keys;
import com.blox.framework.v0.impl.Screen;
import com.blox.framework.v0.impl.ScreenManager;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.set.utils.SetFonts;

public abstract class SetGameScreen extends Screen {
	@Override
	public void init() {
		super.init();
		registerInputListener(this);
	}
	
	@Override
	public void render() {
		super.render();
		TextDrawer.draw(SetFonts.font48, getId(), TextDrawer.AlignN);
	}	
	 
	@Override
    public boolean keyDown(int keycode) {
    	if (keycode == Keys.BACK || keycode == Keys.ESCAPE)
    		ScreenManager.instance.switchTo("menu", true);
    	return super.keyDown(keycode);
    }
}