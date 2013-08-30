package com.blox.framework.v0.impl;

import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.IControlActionHandler;

public class ScreenSwitchActionHandler implements IControlActionHandler {
	private String screenId;
	private boolean back;
	
	public ScreenSwitchActionHandler(String screenId, boolean back) {
		this.screenId = screenId;
		this.back = back;
	}

	@Override
	public void handle(Control control) {
		ScreenManager.instance.switchTo(screenId, back);		
	}
}