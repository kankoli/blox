package com.blox.framework.v0.impl;

import com.blox.framework.v0.IActionHandler;
import com.blox.framework.v0.IActionHandlerFactory;

public class ActionHandlerFactory implements IActionHandlerFactory {
	private IActionHandlerFactory successor;
	
	@Override
	public IActionHandler create(String action) {
		if (action.startsWith("form="))
			return new FormSwitchActionHandler(action.substring(5), false);
		if (action.startsWith("formback="))
			return new FormSwitchActionHandler(action.substring(9), true);
		if (action.startsWith("screen="))
			return new ScreenSwitchActionHandler(action.substring(7), false);
		if (action.startsWith("screenback="))
			return new ScreenSwitchActionHandler(action.substring(11), true);
		else if ("exit".equals(action))
			return new ExitActionHandler();
		if (successor != null)
			return successor.create(action);
		return IActionHandler.NULL;
	}

	@Override
	public void setSuccessor(IActionHandlerFactory successor) {
		this.successor = successor;
	}
}
