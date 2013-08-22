package com.blox.framework.v0.impl;

import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.IMover;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class Mover1 implements IMover {
	protected Vector start;
	protected Vector end;
	protected float duration;
	protected boolean looping;

	protected Vector target;
	protected float distToTarget;

	protected float vx;
	protected float vy;
	protected boolean stopped;

	public Mover1(Vector start, Vector end, float duration) {
		this.start = new Vector(start);
		this.end = new Vector(end);
		this.duration = duration;
		
		vx = (end.x - start.x) / duration;
		vy = (end.y - start.y) / duration;

		distToTarget = end.dist2(start);
	}

	public Vector getStart() {
		return start;
	}

	public Vector getEnd() {
		return end;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public boolean isLooping() {
		return looping;
	}

	public void setLooping(boolean looping) {
		this.looping = looping;
	}

	@Override
	public void move(IMovable movable) {
		if (stopped)
			return;
		
		float dt = Game.getDeltaTime();
		Vector loc = movable.getLocation();

		if (target == null) {
			target = end;
			loc.x = start.x;
			loc.y = start.y;
		}
		else {
			float dist2 = target.dist2(loc);

			if (dist2 > distToTarget) {
				if (!looping) {
					stopped = true;
					return;
				}
				
				loc.x = target.x;
				loc.y = target.y;
				
				target = target == end ? start : end;
				vx = -vx;
				vy = -vy;
				distToTarget = end.dist2(start);
			}
			else {
				distToTarget = dist2;
			}
		}
		loc.x += vx * dt;
		loc.y += vy * dt;
	}
}