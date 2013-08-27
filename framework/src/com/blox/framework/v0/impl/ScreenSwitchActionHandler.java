package com.blox.framework.v0.impl;

import com.blox.framework.v0.IActionHandler;

public class ScreenSwitchActionHandler implements IActionHandler {
	private String screenId;
	private boolean back;
	
	public ScreenSwitchActionHandler(String screenId, boolean back) {
		this.screenId = screenId;
		this.back = back;
	}

	@Override
	public void handle() {
		ScreenManager.instance.switchTo(screenId, back);		
	}
}