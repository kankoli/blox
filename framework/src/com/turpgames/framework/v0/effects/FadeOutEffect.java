package com.turpgames.framework.v0.effects;

import com.turpgames.framework.v0.IDrawingInfo;

public class FadeOutEffect extends FadingEffect {	
	public FadeOutEffect(IDrawingInfo obj) {
		super(obj);
	}
	
	@Override
	protected void onStart() {
		obj.getColor().a = getMaxAlpha();
	}
	
	@Override
	protected void onStop() {
		obj.getColor().a = getMinAlpha();
	}

	@Override
	protected void onUpdate() {
		obj.getColor().a = getMinAlpha() + (1 - getProgress()) * getAlphaRange();
	}
}
