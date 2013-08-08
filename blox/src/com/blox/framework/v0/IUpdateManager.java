package com.blox.framework.v0;

public interface IUpdateManager {
	void register(IUpdatable obj);

	void unregister(IUpdatable obj);

	void update();
}
