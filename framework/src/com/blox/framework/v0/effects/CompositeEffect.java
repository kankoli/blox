package com.blox.framework.v0.effects;

public class CompositeEffect extends Effect {
	private Effect[] effects;

	public CompositeEffect(Effect... effects) {
		this.effects = effects;
	}

	@Override
	public void setDuration(float duration) {
		for (int i = 0; i < effects.length; i++)
			effects[i].setDuration(duration);
		super.setDuration(duration);
	}

	@Override
	protected void onUpdate() {
		for (int i = 0; i < effects.length; i++) {
			effects[i].onUpdate();
		}
	}
	
	@Override
	public void start() {
		for (int i = 0; i < effects.length; i++) {
			effects[i].start();
		}
		super.start();
	}
	
	@Override
	public void stop() {
		for (int i = 0; i < effects.length; i++) {
			effects[i].stop();
		}
		super.stop();
	}

	@Override
	protected <T> T getObject() {
		return null;
	}
}
