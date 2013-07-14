package com.blox.framework.v0;

public interface IVector {
	float getX();
	IVector setX(float x);
	
	float getY();
	IVector setY(float y);
	
	float getZ();
	IVector setZ(float z);

	IVector add(IVector v);
	IVector sub(IVector v);
	IVector div(IVector v);
	IVector mul(IVector v);
	IVector mul(float f); 
	float len();
	float dot(IVector v);
}