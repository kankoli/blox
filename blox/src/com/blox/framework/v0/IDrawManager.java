package com.blox.framework.v0;

public interface IDrawManager {
	void register(IDrawable obj, int layer);	
	void unregister(IDrawable obj);	
	void draw();
}
