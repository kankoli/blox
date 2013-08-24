package com.blox.framework.v0;

public interface IView {
	String getId();

	void render();

	void activated();

	void deactivated();
}
