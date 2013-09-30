package com.blox.framework.v0.effects;

import com.blox.framework.v0.util.Game;

public abstract class Effect {
	protected boolean isActive;
	protected boolean isLooping;
	protected float duration;
	protected float elapsed;
	protected IEffectEndListener listener;
	
	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isLooping() {
		return isLooping;
	}

	public void setLooping(boolean isLooping) {
		this.isLooping = isLooping;
	}

	public float getElapsed() {
		return elapsed;
	}

	public float getProgress() {
		return elapsed / duration;
	}

	public IEffectEndListener getListener() {
		return listener;
	}

	public void setListener(IEffectEndListener listener) {
		this.listener = listener;
	}

	public void stop() {
		if (!isActive)
			return;
		EffectManager.instance.unregister(this);
		isActive = false;
		onStop();
	}

	public void start() {
		if (isActive)
			return;
		onStart();
		elapsed = 0;
		isActive = true;
		EffectManager.instance.register(this);
	}

	public void start(IEffectEndListener listener) {
		if (isActive)
			return;
		setListener(listener);
		start();
	}

	public void update() {
		if (!isActive)
			return;

		elapsed += Game.getDeltaTime();

		if (elapsed > duration) {
			boolean endEffect = notifyEffectEnd();
			if (isLooping && !endEffect) {
				elapsed = 0;
			}
			else {
				stop();
				isActive = false;
				return;
			}
		}

		onUpdate();
	}

	protected boolean notifyEffectEnd() {
		return listener != null
				? listener.onEffectEnd(getObject())
				: true;
	}
	
	protected void onStart() {
		
	}
	
	protected void onStop() {
		
	}
	
	protected abstract <T> T getObject();

	protected abstract void onUpdate();
}