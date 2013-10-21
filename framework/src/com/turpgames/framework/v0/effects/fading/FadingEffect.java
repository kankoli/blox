package com.turpgames.framework.v0.effects.fading;

import com.turpgames.framework.v0.effects.Effect;

public abstract class FadingEffect extends Effect {
	private float minAlpha;
	private float maxAlpha;
	private float alphaRange;
	protected final IFadingEffectSubject obj;
	
	protected FadingEffect(IFadingEffectSubject obj) {
		minAlpha = 0;
		maxAlpha = 1;
		updateAlphaRange();
		
		this.obj = obj;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected IFadingEffectSubject getObject() {
		return obj;
	}
	
	public float getMinAlpha() {
		return minAlpha;
	}

	public void setMinAlpha(float minAlpha) {
		this.maxAlpha = minAlpha > 1 ? 1 : (minAlpha < 0 ? 0 : minAlpha);

		updateAlphaRange();
	}

	public float getMaxAlpha() {
		return maxAlpha;
	}

	public void setMaxAlpha(float maxAlpha) {		
		this.maxAlpha = maxAlpha > 1 ? 1 : (maxAlpha < 0 ? 0 : maxAlpha);
		updateAlphaRange();
	}

	private void updateAlphaRange() {
		alphaRange = this.maxAlpha - this.minAlpha;
	}
	
	protected float getAlphaRange() {
		return alphaRange;
	}
}
