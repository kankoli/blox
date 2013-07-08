package com.blox.framework.v0;

public interface ISound {
	void play();
	void stop();

	void load(String resourcePath);
	void dispose();
}
