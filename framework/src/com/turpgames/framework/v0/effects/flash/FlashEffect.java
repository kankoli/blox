package com.turpgames.framework.v0.effects.flash;

import com.turpgames.framework.v0.effects.Effect;
import com.turpgames.framework.v0.util.Color;

public class FlashEffect extends Effect {

	private float flashPerSecond;
	private Color initialColor;
	private Color flashColor;
	private IFlashEffectSubject obj;
	
	public FlashEffect(IFlashEffectSubject obj, Color flashColor, int flashPerSecond) {
		this.obj = obj;
		this.flashColor = flashColor;
		this.initialColor = new Color();
		this.flashPerSecond = flashPerSecond;
	}

	public float getFlashPerSecond() {
		return flashPerSecond;
	}

	public void setFlashPerSecond(float flashPerSecond) {
		this.flashPerSecond = flashPerSecond;
	}
	
	public void setFlashColor(Color flashColor) {
		this.flashColor = flashColor;
	}
	
	@Override
	protected void onStart() {
		initialColor.set(obj.getColor());
	}
	
	@Override
	protected void onStop() {
		obj.setColor(initialColor.r, initialColor.g, initialColor.b, initialColor.a);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected IFlashEffectSubject getObject() {
		return obj;
	}

	@Override
	protected void onUpdate() {
		if ((int)(2 * flashPerSecond * getElapsed()) % 2 == 0) {
			obj.setColor(flashColor.r, flashColor.g, flashColor.b, flashColor.a);
		}
		else {
			obj.setColor(initialColor.r, initialColor.g, initialColor.b, initialColor.a);
		}
	}

}
