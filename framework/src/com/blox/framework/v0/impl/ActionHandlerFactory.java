package com.blox.framework.v0.impl;

import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.IControlActionHandler;
import com.blox.framework.v0.forms.xml.IControlActionHandlerFactory;

public class ActionHandlerFactory implements IControlActionHandlerFactory {
	private IControlActionHandlerFactory successor;
	
	@Override
	public IControlActionHandler create(Control control, String action) {
		if (action.startsWith("form="))
			return new FormSwitchActionHandler(action.substring(5), false);
		if (action.startsWith("formback="))
			return new FormSwitchActionHandler(action.substring(9), true);
		if (action.startsWith("screen="))
			return new ScreenSwitchActionHandler(action.substring(7), false);
		if (action.startsWith("bind="))
			return BindingActionHandler.create(control, action.substring(5));
		if (action.startsWith("screenback="))
			return new ScreenSwitchActionHandler(action.substring(11), true);
		if ("exit".equals(action))
			return new ExitActionHandler();
		if (successor != null)
			return successor.create(control, action);
		return IControlActionHandler.NULL;
	}

	@Override
	public void setSuccessor(IControlActionHandlerFactory successor) {
		this.successor = successor;
	}
}
