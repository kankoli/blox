package com.blox.framework.v0.effects;

import com.blox.framework.v0.IDrawingInfo;

public class FadeInEffect extends FadingEffect {
	public FadeInEffect(IDrawingInfo obj) {
		super(obj);
	}
	
	@Override
	protected void onStart() {
		obj.getColor().a = getMinAlpha();
	}
	
	@Override
	protected void onStop() {
		obj.getColor().a = getMaxAlpha();
	}

	@Override
	protected void onUpdate() {
		obj.getColor().a = getMinAlpha() + getProgress() * getAlphaRange();
	}
}
