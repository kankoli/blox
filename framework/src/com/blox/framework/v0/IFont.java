package com.blox.framework.v0;

import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Vector;

public interface IFont extends IDisposable {
	Color getColor();

	void draw(String text, float x, float y);

	Vector measureText(String text);

	void setScale(float scale);
}
