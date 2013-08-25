package com.blox.framework.v0;

public interface IDrawer {
	void register(IDrawable obj, int layer);

	void unregister(IDrawable obj);

	void draw();
}
