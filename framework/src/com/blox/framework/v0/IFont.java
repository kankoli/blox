package com.blox.framework.v0;

import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Rectangle;
import com.blox.framework.v0.util.Vector;

public interface IFont extends IDisposable {	
	Color getColor();

	void draw(String text, Rectangle textArea, int align);

	Vector measureText(String text, float maxWidth);

	void setScale(float scale);
}
