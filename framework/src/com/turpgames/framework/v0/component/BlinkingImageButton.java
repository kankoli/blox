package com.turpgames.framework.v0.component;

import com.turpgames.framework.v0.effects.blink.BlinkEffect;
import com.turpgames.framework.v0.effects.blink.IBlinkEffectSubject;
import com.turpgames.framework.v0.util.Color;

public class BlinkingImageButton extends ImageButton implements IBlinkEffectSubject {

	private BlinkEffect blinkEffect;
	
	public BlinkingImageButton(float width, float height, Color defaultColor, Color touchedColor, float duration, int blinkPerSecond) {
		super(width, height, defaultColor, touchedColor);
		blinkEffect = new BlinkEffect(this);
		blinkEffect.setDuration(duration);
		blinkEffect.setBlinkPerSecond(blinkPerSecond);
	}
	
	public void blink() {
		blinkEffect.start();
	}
	
	@Override
	public void setAlpha(float alpha) {
		getColor().a = alpha;
	}

	@Override
	public float getAlpha() {
		return getColor().a;
	}
}
