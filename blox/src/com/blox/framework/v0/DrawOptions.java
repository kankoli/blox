package com.blox.framework.v0;

public class DrawOptions {
	// x the x-coordinate in screen space
	public float x;
	// y the y-coordinate in screen space
	public float y;
	// originX the x-coordinate of the scaling and rotation origin relative to the texture itself
	public float originX;
	// originY the y-coordinate of the scaling and rotation origin relative to the texture itself
	public float originY;
	// width the width in pixels
	public float width;
	// height the height in pixels
	public float height;
	// scaleX the scale of the rectangle around originX/originY in x
	public float scaleX = 1;
	// scaleY the scale of the rectangle around originX/originY in y
	public float scaleY = 1;
	// rotation the angle of counter clockwise rotation (in degrees) of the rectangle around originX/originY
	public float rotation;
	// flipX whether to flip the sprite horizontally
	public boolean flipX;
	// flipY whether to flip the sprite vertically
	public boolean flipY;
}
