package com.turpgames.framework.v0.effects.fading;

public class FadeInEffect extends FadingEffect {
	public FadeInEffect(IFadingEffectSubject obj) {
		super(obj);
	}
	
	@Override
	protected void onStart() {
		obj.setAlpha(getMinAlpha());
	}
	
	@Override
	protected void onStop() {
		obj.setAlpha(getMaxAlpha());
	}

	@Override
	protected void onUpdate() {
		obj.setAlpha(getMinAlpha() + getProgress() * getAlphaRange());
	}
}
