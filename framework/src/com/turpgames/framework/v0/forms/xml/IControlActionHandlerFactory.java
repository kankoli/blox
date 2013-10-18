package com.turpgames.framework.v0.forms.xml;

public interface IControlActionHandlerFactory {
	void setSuccessor(IControlActionHandlerFactory successor);
	
	IControlActionHandler create(Control control, String action);
}
