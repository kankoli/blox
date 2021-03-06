package com.turpgames.framework.v0.component.info;

import com.turpgames.framework.v0.effects.flash.FlashEffect;
import com.turpgames.framework.v0.effects.flash.IFlashEffectSubject;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;

public class FlashingGameInfo extends Text implements IFlashEffectSubject {
	private FlashEffect flashEffect;
	
	public FlashingGameInfo(Color flashColor, int flashPerSecond, float duration) {
		super();
		flashEffect = new FlashEffect(this, flashColor, flashPerSecond);
		flashEffect.setDuration(duration);
	}
	
	public void start() {
		flashEffect.start();
	}
	
	public void stop() {
		flashEffect.stop();
	}
	
	@Override
	public void setColor(float r, float g, float b, float a) {
		getColor().set(r,g,b,a);
	}
}
