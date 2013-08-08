package com.blox.framework.v0;

import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.Vector;

public interface IFont extends IDisposable {
	void setScale(float scale);

	void setColor(Color color);
	
	Vector calculateSize(String text);
	
	float getLineHeight();
}
