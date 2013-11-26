package com.turpgames.framework.v0.effects.blink;

import com.turpgames.framework.v0.effects.Effect;

public class BlinkEffect extends Effect {
	private float blinkPerSecond;
	private float initialAlpha;
	private IBlinkEffectSubject obj;
	
	public BlinkEffect(IBlinkEffectSubject obj) {
		this.obj = obj;
	}
	
	public float getBlinkPerSecond() {
		return blinkPerSecond;
	}

	public void setBlinkPerSecond(float blinkPerSecond) {
		this.blinkPerSecond = blinkPerSecond;
	}
	
	@Override
	protected void onStart() {
		initialAlpha = obj.getAlpha();
	}
	
	@Override
	protected void onStop() {
		obj.setAlpha(initialAlpha);
	}

	@Override
	protected void onUpdate() {
		obj.setAlpha(((int)(blinkPerSecond * getProgress())) % 2 == 0 ? 0 : initialAlpha); 
	}

	@Override
	@SuppressWarnings("unchecked")
	protected IBlinkEffectSubject getObject() {
		return obj;
	}
}