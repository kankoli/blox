package com.turpgames.framework.v0.effects;

import com.badlogic.gdx.math.MathUtils;
import com.turpgames.framework.v0.IDrawingInfo;
import com.turpgames.framework.v0.util.Vector;

public class BreathEffect extends DrawingEffect {
	private float max;
	private float min;
	Vector initialScale;

	public BreathEffect(IDrawingInfo obj) {
		super(obj);
		initialScale = new Vector();
	}

	public void setMaxFactor(float max) {
		this.max = max;
	}

	public void setMinFactor(float min) {
		this.min = min;
	}

	@Override
	protected void onStart() {
		super.onStart();
		initialScale.set(getObject().getScale());
	}

	@Override
	protected void onStop() {
		super.onStop();
		getObject().getScale().set(initialScale);
	}

	@Override
	protected void onUpdate() {
		float f = MathUtils.sin(MathUtils.PI * elapsed / duration) * (max - min) + min;
		getObject().getScale().set(f, f);
	}
}