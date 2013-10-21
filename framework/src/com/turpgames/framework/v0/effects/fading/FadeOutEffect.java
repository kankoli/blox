package com.turpgames.framework.v0.effects.fading;

public class FadeOutEffect extends FadingEffect {	
	public FadeOutEffect(IFadingEffectSubject obj) {
		super(obj);
	}
	
	@Override
	protected void onStart() {
		obj.setAlpha(getMaxAlpha());
	}
	
	@Override
	protected void onStop() {
		obj.setAlpha(getMinAlpha());
	}

	@Override
	protected void onUpdate() {
		obj.setAlpha(getMinAlpha() + (1 - getProgress()) * getAlphaRange());
	}
}
