package com.blox.framework.v0.impl;

import com.blox.framework.v0.IActionHandler;

public class ScreenSwitchActionHandler implements IActionHandler {
	private String screenId;

	public ScreenSwitchActionHandler(String screenId) {
		this.screenId = screenId;
	}

	@Override
	public void handle() {
		ScreenManager.instance.switchTo(screenId);		
	}
}