package com.blox.framework.v0.util;

import java.util.Iterator;
import com.blox.framework.v0.IBound;
import com.blox.framework.v0.ICollidable;
import com.blox.framework.v0.ICollisionDetector;
import com.blox.framework.v0.ICollisionGroup;

public final class CollisionDetector {
	private CollisionDetector() {

	}

	public static boolean detect(ICollisionGroup group, ICollidable obj1, ICollidable obj2) {
		Iterator<IBound> bounds1 = obj1.getBounds();
		boolean collided = false;
		while (bounds1.hasNext()) {
			IBound bound1 = bounds1.next();
			Iterator<IBound> bounds2 = obj2.getBounds();
			while (bounds2.hasNext()) {
				IBound bound2 = bounds2.next();
				ICollisionDetector detector = getDetector(bound1.getType(), bound2.getType());
				if (detector.detect(bound1, bound2)) {  // colliding
					boolean exit = false;
					exit = exit || obj1.onCollide(bound1, bound2, obj2);
					exit = exit || obj2.onCollide(bound2, bound1, obj1);
					collided = true;
					group.onCollide(obj1, bound1, obj2, bound2);
					if (exit)
						return true;
				}
				else {									// not colliding
					group.onNotCollide(obj1, bound1, obj2, bound2);
				}
			}
		}
		return collided;
	}

	private static ICollisionDetector getDetector(int boundType1, int boundType2) {
		return Game.getCollisionDetectorFactory().getDetector(boundType1, boundType2);
	}
}