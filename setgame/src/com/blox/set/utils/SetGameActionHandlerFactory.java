package com.blox.set.utils;

import com.blox.framework.v0.forms.xml.Control;
import com.blox.framework.v0.forms.xml.IControlActionHandler;
import com.blox.framework.v0.forms.xml.IControlActionHandlerFactory;

public class SetGameActionHandlerFactory implements IControlActionHandlerFactory {
	@Override
	public void setSuccessor(IControlActionHandlerFactory successor) {

	}

	@Override
	public IControlActionHandler create(Control control, String action) {
		return IControlActionHandler.NULL;
	}
}
