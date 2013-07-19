package com.blox.framework.v0;

public interface IInputManager {
	void register(IInputListener listener);	
	void unregister(IInputListener listener);

	void activate();
	void deactivate();
	boolean isActive();
}
