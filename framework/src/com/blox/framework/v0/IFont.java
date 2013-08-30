package com.blox.framework.v0;

import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Vector;

public interface IFont extends IDisposable {	
	void setColor(Color color);
	
	Vector getSize(String text);

	void draw(String text, float x, float y);
	
	void setSize(int size);
}
