package com.blox.framework.v0;

public interface IMedia extends IDisposable {
	void play();
	void pause();
	void stop();

	float getSpeed();
	void setSpeed(float speed);

	float getSoundLevel();
	void setSoundLevel(float soundLevel);
	
	float getPosition();
	void setPosition(float position);
}
