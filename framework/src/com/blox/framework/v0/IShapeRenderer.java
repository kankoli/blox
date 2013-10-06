package com.blox.framework.v0;

import com.blox.framework.v0.util.Color;

public interface IShapeRenderer {
	void drawLine(float x0, float y0, float x1, float y1, Color color);
	void drawRect(float x, float y, float w, float h, Color color, boolean filled);
}
