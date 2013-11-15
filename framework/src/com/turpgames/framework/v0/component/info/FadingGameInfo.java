package com.turpgames.framework.v0.component.info;

import com.turpgames.framework.v0.effects.IEffectEndListener;
import com.turpgames.framework.v0.effects.fading.FadeOutEffect;
import com.turpgames.framework.v0.effects.fading.IFadingEffectSubject;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Drawer;
import com.turpgames.framework.v0.util.Utils;

public class FadingGameInfo extends GameInfo implements IFadingEffectSubject {
	private FadeOutEffect fadeOutEffect;
	private boolean isActive;
	private float fadeOutDuration = 2.5f;

	public FadingGameInfo() {
		fadeOutEffect = new FadeOutEffect(this);
		fadeOutEffect.setMinAlpha(0);
		fadeOutEffect.setMaxAlpha(1);
		fadeOutEffect.setListener(effectListener);

		this.text.getColor().set(Color.white());
	}

	public void setDuration(float duration) {
		fadeOutEffect.setDuration(duration);
	}
	
	public void show() {
		if (isActive) {
			fadeOutEffect.stop();
			setAlpha(1);
		}

		this.isActive = true;

		fadeOutEffect.setDuration(fadeOutDuration);
		fadeOutEffect.start();

		Drawer.getCurrent().register(this, Utils.LAYER_INFO);
	}

	private void effectEnd() {
		this.isActive = false;
		if (Drawer.getCurrent() != null) // If mode is exited before the effect is finished, current drawer becomes null
			Drawer.getCurrent().unregister(this);
	}

	private IEffectEndListener effectListener = new IEffectEndListener() {
		@Override
		public boolean onEffectEnd(Object obj) {
			effectEnd();
			return true;
		}
	};

	@Override
	public void setAlpha(float alpha) {
		this.text.getColor().a = alpha;
	}
}
