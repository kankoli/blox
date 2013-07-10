package com.blox.framework.v0;

public abstract class GameObject {
	// http://en.wikipedia.org/wiki/Physical_property#List_of_properties
	public float mass;
	public float density;
	public float friction;
	public float elasticity;
	public Vector location;
	
	abstract void update();
}
