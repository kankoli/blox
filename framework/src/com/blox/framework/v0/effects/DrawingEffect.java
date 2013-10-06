package com.blox.framework.v0.effects;

import com.blox.framework.v0.IDrawingInfo;

public abstract class DrawingEffect extends Effect {
	protected final IDrawingInfo obj;
	
	protected DrawingEffect(IDrawingInfo obj) {
		this.obj = obj;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected IDrawingInfo getObject() {
		return obj;
	}
}
