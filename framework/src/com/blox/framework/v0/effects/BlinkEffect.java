package com.blox.framework.v0.effects;

import com.blox.framework.v0.IDrawingInfo;

public class BlinkEffect extends DrawingEffect {
	private float blinkPerSecond;
	private float initialAlpha;
	
	public BlinkEffect(IDrawingInfo obj) {
		super(obj);
	}
	
	public float getBlinkPerSecond() {
		return blinkPerSecond;
	}

	public void setBlinkPerSecond(float blinkPerSecond) {
		this.blinkPerSecond = blinkPerSecond;
	}
	
	@Override
	protected void onStart() {
		initialAlpha = obj.getColor().a;
	}
	
	@Override
	protected void onStop() {
		obj.getColor().a = initialAlpha;
	}

	@Override
	protected void onUpdate() {
		obj.getColor().a = ((int)(blinkPerSecond * getProgress())) % 2 == 0 ? 0 : initialAlpha; 
	}
}