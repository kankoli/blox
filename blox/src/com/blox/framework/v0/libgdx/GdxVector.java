package com.blox.framework.v0.libgdx;

import com.badlogic.gdx.math.Vector3;
import com.blox.framework.v0.Vector;

public class GdxVector extends Vector {
	private Vector3 gdxVector;
	
	public GdxVector() {
		gdxVector = new Vector3();
	}
	
	Vector3 getGdxVector3() {
		gdxVector.x = x;
		gdxVector.y = y;
		gdxVector.z = z;
		return gdxVector;
	}
}
