package com.blox.framework.v0.forms.xml;

import com.blox.framework.v0.impl.ScreenManager;

public class ScreenSwitchListener implements IClickListener {
	
	private String targetScreen;
	
	public ScreenSwitchListener(String targetScreen) {
		this.targetScreen = targetScreen;
	}
	
	@Override
	public void onClick(Control control) {
		ScreenManager.instance.switchTo(targetScreen);
	}
}