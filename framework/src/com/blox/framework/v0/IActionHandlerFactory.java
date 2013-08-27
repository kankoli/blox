package com.blox.framework.v0;

public interface IActionHandlerFactory {
	void setSuccessor(IActionHandlerFactory successor);
	
	IActionHandler create(String action);
}
