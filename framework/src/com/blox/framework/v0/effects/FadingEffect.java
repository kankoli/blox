package com.blox.framework.v0.effects;

import com.blox.framework.v0.IDrawingInfo;

public abstract class FadingEffect extends DrawingEffect {
	private float minAlpha;
	private float maxAlpha;
	private float alphaRange;

	protected FadingEffect(IDrawingInfo obj) {
		super(obj);
		minAlpha = 0;
		maxAlpha = 1;
		updateAlphaRange();
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
