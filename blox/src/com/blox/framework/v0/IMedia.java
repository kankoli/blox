package com.blox.framework.v0;

public interface IMedia {
	void play();
	void pause();
	void stop();

	void getSpeed();
	void setSpeed(float speed);
	
	void getPosition();
	void setPosition(float position);
}
