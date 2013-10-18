package com.turpgames.framework.v0.effects;

import com.turpgames.framework.v0.IDrawingInfo;
import com.turpgames.framework.v0.util.Vector;

public class ScaleEffect extends DrawingEffect {	
	private float maxScale;
	private Vector initialScale;
	
	public ScaleEffect(IDrawingInfo obj) {
		super(obj);
		initialScale = new Vector();
	} 

	public float getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(float maxScale) {
		this.maxScale = maxScale;
	}

	@Override
	protected void onUpdate() {
		IDrawingInfo obj = getObject();
		float progress = getProgress();

		float s = 1;
		if (progress < 0.5f)
			s = 1f + 2 * progress * maxScale;
		else
			s = 1f + maxScale - (2 * progress - 1) * maxScale;

		obj.getScale().set(s, s);
	}
	
	@Override
	protected void onStart() {
		initialScale.set(getObject().getScale());
	}
	
	@Override
	protected void onStop() {
		getObject().getScale().set(initialScale);
	}
}

