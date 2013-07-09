package com.blox.framework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/***
 * Abstract class to derive bounds of different shapes.
 * Uses <b>the separating axis theorem directly on X and Y axes</b>
 * so it works fine <b>for only rectangles and circles</b>. 
 * @author kadirello
 *
 */
public abstract class Bounds {

	abstract protected Vector2 getIntervalX();
	abstract protected Vector2 getIntervalY();
	
	/**
	 * Calculates collision between given Bounds objects. 
	 * @param b1
	 * @param b2
	 * @return true if there is collision.
	 */
	public static final boolean collide(Bounds b1, Bounds b2) {
		return collideInterval(b1.getIntervalX(), b2.getIntervalX()) 
				&& collideInterval(b1.getIntervalY(), b2.getIntervalY());
	}
	
	/**
	 * Calculates collision between given Bounds and other Bounds objects in bArray. 
	 * @param b1
	 * @param bArray
	 * @return true if there is collision with any of the objects in given arrays.
	 */
	public static final boolean collide(Bounds b1, Array<Bounds> bArray) {
		for (Bounds b2: bArray) {
			if (collide(b1,b2))
				return true;
		}
		return false;
	}
	
	/**
	 * Calculates collision between Bounds objects in given arrays.
	 * @param bArray1
	 * @param bArray2
	 * @return true if there is collision between any two objects in array.
	 */
	public static final boolean collide(Array<Bounds> bArray1, Array<Bounds> bArray2) {
		for (Bounds b1: bArray1) {
			if (collide(b1,bArray2))
				return true;
		}
		return false;
	}
	
	private static final boolean collideInterval(Vector2 i1, Vector2 i2) {
		return (i1.y >= i2.x && i1.x <= i2.x) || (i1.x < i2.y && i1.y > i2.y);
	}
}
