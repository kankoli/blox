package com.turpgames.framework.v0;

public interface ICompositeInputListener extends IInputListener {
	void register(IInputListener listener);

	void unregister(IInputListener listener);

	void activate();

	void deactivate();

	boolean isActive();
}
