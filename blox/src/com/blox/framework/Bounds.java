package com.blox.framework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/***
 * Abstract class to derive bounds of different shapes.
 * Uses <b>the Separating Hyperplane Theorem directly on X and Y axes</b>
 * so this implementation currently works <b>fine for only rectangles</b>. 
 * Full implementation might be done later on if needed.
 * @author kadirello
 *
 */
public abstract class Bounds {

	protected BloxSprite parent;
	protected Vector2 parentOffset;
	
	// TODO: these might be generalized to use in the full implementation of SHT.
	abstract protected Vector2 getIntervalX();
	abstract protected Vector2 getIntervalY();
	
	abstract public boolean collide(Bounds b);

	public final boolean collide(Array<Bounds> bArray) {
		for (Bounds b: bArray) {
			if (collide(b))
				return true;
		}
		return false;
	}
}
