package com.turpgames.framework.v0.effects.flash;

import com.turpgames.framework.v0.util.Color;

public interface IFlashEffectSubject {
	Color getColor();

	void setColor(float r, float g, float b, float a);
}
