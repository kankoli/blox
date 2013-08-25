package com.blox.framework.v0;

public interface IDrawerManager {
	void register(IDrawer drawer, int layer);

	void unregister(IDrawer drawer);

	void draw();
}
