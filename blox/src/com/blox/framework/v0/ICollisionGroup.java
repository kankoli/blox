package com.blox.framework.v0;

import java.util.List;
import java.util.ListIterator;

public interface ICollisionGroup {
	void registerFirst(ICollidable obj);
	
	void registerSecond(ICollidable obj);
	
	void unregisterFirst(ICollidable obj);
	
	void unregisterSecond(ICollidable obj);
	
	List<ICollidable> getFirst();
	
	List<ICollidable> getSecond();
	
	boolean isActive();
	
	void activate();
	
	void deactivate();

	boolean onCollide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound);

	boolean onNotCollide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound);
	
	void registerCollisionListener(ICollisionListener listener);
	
	void unregisterCollisionListener(ICollisionListener listener);

	void registerNotCollisionListener(ICollisionListener listener);

	void unregisterNotCollisionListener(ICollisionListener listener);
}
