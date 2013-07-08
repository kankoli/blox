package com.blox.framework.v0;

public interface IMusic {
	void play();
	void pause();
	void stop();

	void load(String resourcePath);
	void dispose();
}
