package com.blox.framework.v0.impl;

import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

public abstract class Bound implements IBound {
	protected ICollidable parent;
	protected Vector offset;
	
	protected Rotation rotation;
	protected Vector location;


	protected Bound(ICollidable parent, Vector offset) {
		this.parent = parent;
		this.offset = offset;
		
		this.rotation = new Rotation();
		this.location = new Vector();
	}

	@Override
	public Rotation getRotation() {
		return rotation;
	}
}