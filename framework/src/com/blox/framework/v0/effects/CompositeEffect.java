package com.blox.framework.v0.effects;

public class CompositeEffect extends Effect {
	private Effect[] effects;
	private Object obj;

	public CompositeEffect(Object obj, Effect... effects) {
		this.obj = obj;
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

	@SuppressWarnings("unchecked")
	@Override
	protected Object getObject() {
		return obj;
	}
}