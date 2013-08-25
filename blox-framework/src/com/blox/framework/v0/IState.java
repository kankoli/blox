package com.blox.framework.v0;

public interface IState extends IInputListener, IAnimationEndListener, ICollisionListener {
	void work();
}
