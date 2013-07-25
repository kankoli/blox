package com.blox.framework.v0;

public interface IStateManager {
	void register(IState state);

	void unregister(IState state);

	void work();
}
