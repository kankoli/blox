package com.blox.framework.v0.impl;

import com.blox.framework.v0.IActionHandler;
import com.blox.framework.v0.IActionHandlerFactory;

public class ActionHandlerFactory implements IActionHandlerFactory {
	@Override
	public IActionHandler create(String action) {
		if (action.startsWith("form="))
			return new FormSwitchActionHandler(action.substring(5));
		if (action.startsWith("screen="))
			return new ScreenSwitchActionHandler(action.substring(7));
		else if ("exit".equals(action))
			return new ExitActionHandler();
		return IActionHandler.NULL;
	}
}
