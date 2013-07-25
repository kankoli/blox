package com.blox.framework.v0;

import com.blox.framework.v0.util.Rotation;
import com.blox.framework.v0.util.Vector;

public interface IDrawable {	
	float getWidth();
	float getHeight();
	Vector getLocation();
	Vector getScale();
	Rotation getRotation();
	void setRotation(Rotation r);
	boolean isFlipX();
	boolean isFlipY();
	void draw();
	void setDrawer(IDrawer drawer);
}
