package com.blox.framework.v0;

public interface IMoveManager {
	void register(IMovable obj);	
	void unregister(IMovable obj);	
	void move();
}
