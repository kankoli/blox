package com.blox.setgame.model;

import com.blox.framework.v0.effects.BlinkEffect;
import com.blox.framework.v0.effects.FadeOutEffect;
import com.blox.framework.v0.effects.IEffectEndListener;
import com.blox.framework.v0.impl.GameObject;

abstract class SetGameObject extends GameObject {
	public static final float FadingDuration = 0.25f;
	public static final float BlinkDuration = 1f;
	public static final int BlinkPerSecond = 10;
	
	private FadeOutEffect fader;
	private BlinkEffect blinker;

	private FadeOutEffect getFader() {
		if (fader == null) {
			fader = new FadeOutEffect(this);
			fader.setDuration(FadingDuration);
		}
		return fader;
	}

	private BlinkEffect getBlinker() {
		if (blinker == null) {
			blinker = new BlinkEffect(this);
			blinker.setDuration(BlinkDuration);
			blinker.setBlinkPerSecond(BlinkPerSecond);
		}
		return blinker;
	}
	
	void fadeOut(IEffectEndListener listener) {
		getFader().start(listener);
	}

	void blink(IEffectEndListener listener, boolean looping) {
		getBlinker().setLooping(looping);
		getBlinker().start(listener);
	}

	void stopBlinking() {
		getBlinker().stop();
	}

}
