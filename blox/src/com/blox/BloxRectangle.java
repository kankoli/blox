package com.blox;

import com.badlogic.gdx.math.Vector2;

/*

 D******************C
 *                  *
 *                  *
 *                  *
 A******************B

 */

public class BloxRectangle {
	private float width;
	private float height;
	private Vector2 a;
	private Vector2 b;
	private Vector2 c;
	private Vector2 d;
	private float rotation;
	private float sinr;
	private float cosr;
	private Vector2 rotatationOrigin;

	private Vector2[] vertices;

	private BloxRectangle() {
		a = new Vector2();
		b = new Vector2();
		c = new Vector2();
		d = new Vector2();
		vertices = new Vector2[] { a, b, c, d };
		rotatationOrigin = new Vector2();
		setRotation(0);
	}

	public BloxRectangle(float ax, float ay, float width, float height) {
		this();
		a.x = ax;
		a.y = ay;
		this.width = width;
		this.height = height;
		rotatationOrigin.x = ax + width / 2;
		rotatationOrigin.y = ay + height / 2;
		
		ensureVertices();
	}

	public float getRotation() {
		return this.rotation;
	}

	public void setRotation(float rotation) {
		sinr = (float) Math.sin(rotation);
		cosr = (float) Math.cos(rotation);
		this.rotation = rotation;
	}

	public void rotate(float theta) {
		setRotation(rotation + theta);
	}

	public void setRotationOrigin(float ox, float oy) {
		setRotatationOriginX(ox);
		setRotatationOriginY(oy);
	}

	public float getRotatationOriginX() {
		return rotatationOrigin.x;
	}

	public void setRotatationOriginX(float ox) {
		rotatationOrigin.x = ox;
	}

	public void moveRotatationOriginX(float dox) {
		setRotatationOriginX(rotatationOrigin.x + dox);
	}

	public float getRotatationOriginY() {
		return rotatationOrigin.y;
	}

	public void setRotatationOriginY(float oy) {
		rotatationOrigin.y = oy;
	}

	public void moveRotatationOriginY(float doy) {
		setRotatationOriginY(rotatationOrigin.y + doy);
	}

	public float getX() {
		return a.x;
	}

	public float getY() {
		return a.y;
	}

	public void setX(float x) {
		a.x = x;
		ensureVertices();
	}

	public void setY(float y) {
		a.y = y;
		ensureVertices();
	}

	public void setLocation(float x, float y) {
		setX(x);
		setY(y);
	}

	public void move(float dx, float dy) {
		moveX(dx);
		moveY(dy);
	}

	public void moveX(float dx) {
		setX(a.x + dx);
	}

	public void moveY(float dy) {
		setY(a.y + dy);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		ensureVertices();
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		ensureVertices();
	}

	private void ensureVertices() {
		b.x = a.x + width;
		b.y = a.y;

		c.x = a.x + width;
		c.y = a.y + height;

		d.x = a.x;
		d.y = a.y + height;
	}

	public void draw(ScaledShapeRenderer renderer) {

		float ox = rotatationOrigin.x;
		float oy = rotatationOrigin.y;
		
//		float ox = a.x + width / 2;
//		float oy = a.y + height / 2;
				
		for (int i = 0; i < vertices.length; i++) {
			Vector2 p0 = vertices[i];
			Vector2 p1 = vertices[(i + 1) % vertices.length];
						
			float x0 = cosr * (p0.x - ox) - sinr * (p0.y - oy) + ox;
			float y0 = sinr * (p0.x - ox) + cosr * (p0.y - oy) + oy;
			float x1 = cosr * (p1.x - ox) - sinr * (p1.y - oy) + ox;
			float y1 = sinr * (p1.x - ox) + cosr * (p1.y - oy) + oy;
			
			renderer.line(x0, y0, x1, y1);
		}
	}
}
