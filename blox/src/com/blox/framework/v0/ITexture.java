package com.blox.framework.v0;

public interface ITexture {
	float getWidth();
	float getHeight();
	
	void load(String resourcePath);
	void dispose();
}
