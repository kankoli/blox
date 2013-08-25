package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.graphics.Color;

public final class GdxUtils {
	private GdxUtils() {

	}

	public static Color toGdxColor(com.blox.framework.v0.util.Color color) {
		return new Color(color.r / 255f, color.g / 255f, color.b / 255f, color.a / 255f);
	}
}
