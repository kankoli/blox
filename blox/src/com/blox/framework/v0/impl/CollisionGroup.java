package com.blox.framework.v0.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionGroup;
import com.blox.framework.v0.ICollisionListener;

public class CollisionGroup implements ICollisionGroup {

	protected List<ICollisionListener> collisionListeners; 
	protected List<ICollisionListener> notCollisionListeners; 

	protected List<ICollidable> first;
	protected List<ICollidable> second;
	protected boolean active;
	
	public CollisionGroup(ICollidable firstObj, ICollidable secondObj) {
		this(firstObj, Arrays.asList(secondObj));
	}
	
	public CollisionGroup(ICollidable firstObj, List<ICollidable> secondList) {
		this(Arrays.asList(firstObj), secondList);
	}
	
	public CollisionGroup(List<ICollidable> firstList, List<ICollidable> secondList) {
		this.first = firstList;
		this.second = secondList;
		collisionListeners = new ArrayList<ICollisionListener>();
		notCollisionListeners = new ArrayList<ICollisionListener>();
		activate();
	}
	
	@Override
	public void registerFirst(ICollidable obj) {
		if (!first.contains(obj))
			first.add(obj);
	}

	@Override
	public void registerSecond(ICollidable obj) {
		if (!second.contains(obj))
			second.add(obj);
	}

	@Override
	public void unregisterFirst(ICollidable obj) {
		first.remove(obj);
	}

	@Override
	public void unregisterSecond(ICollidable obj) {
		second.remove(obj);
	}

	@Override
	public List<ICollidable> getFirst() {
		return first;
	}

	@Override
	public List<ICollidable> getSecond() {
		return second;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void activate() {
		active = true;
	}

	@Override
	public void deactivate() {
		active = false;
	}
	
	@Override
	public boolean onCollide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound) {
		Iterator<ICollisionListener> itr = collisionListeners.iterator();
		while(itr.hasNext()) {
			ICollisionListener l = itr.next();
			l.collide(thisObj, thisBound, thatObj, thatBound);
		}
		return false;
	}

	@Override
	public void registerCollisionListener(ICollisionListener listener) {
		collisionListeners.add(listener);
		activate();
	}

	@Override
	public void unregisterCollisionListener(ICollisionListener listener) {
		collisionListeners.remove(listener);
		if (collisionListeners.size() == 0 && notCollisionListeners.size() == 0)
			deactivate();
	}

	@Override
	public boolean onNotCollide(ICollidable thisObj, IBound thisBound, ICollidable thatObj, IBound thatBound) {
		Iterator<ICollisionListener> itr = notCollisionListeners.iterator();
		while(itr.hasNext()) {
			ICollisionListener l = itr.next();
			l.notCollide(thisObj, thisBound, thatObj, thatBound);
		}
		return false;
	}
	
	@Override
	public void registerNotCollisionListener(ICollisionListener listener) {
		notCollisionListeners.add(listener);
		activate();
	}

	@Override
	public void unregisterNotCollisionListener(ICollisionListener listener) {
		notCollisionListeners.remove(listener);
		if (collisionListeners.size() == 0 && notCollisionListeners.size() == 0)
			deactivate();
	}
}
