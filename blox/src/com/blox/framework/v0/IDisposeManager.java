package com.blox.framework.v0;

public interface IDisposeManager {
	void register(IDisposable disposable);

	void dispose(IDisposable disposable);

	void disposeAll();
}
