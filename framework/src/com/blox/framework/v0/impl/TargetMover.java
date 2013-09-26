package com.blox.framework.v0.impl;

import com.blox.framework.v0.IMovable;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class TargetMover extends Mover {
	protected Vector start;
	protected Vector end;
	protected float duration;
	protected boolean looping;

	protected Vector target;
	protected float distToTarget;

	protected float vx;
	protected float vy;
	protected boolean stopped;

	public TargetMover(float duration) {
		this.start = new Vector();
		this.end = new Vector();
		this.duration = duration;
	}

	public void updateRoute(float x0, float y0, float x1, float y1) {
		this.start.set(x0, y0);
		this.end.set(x1, y1);

		this.target = null;
		this.updateVelocity();
	}

	public void updateRoute(Vector start, Vector end) {
		updateRoute(start.x, start.y, end.x, end.y);
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
		this.updateVelocity();
	}

	public void start() {
		stopped = false;
		this.distToTarget = end.dist2(start);
	}

	public void stop() {
		stopped = true;
		this.distToTarget = 0;
	}

	public boolean isLooping() {
		return looping;
	}

	public void setLooping(boolean looping) {
		this.looping = looping;
	}

	protected void updateVelocity() {
		vx = (end.x - start.x) / duration;
		vy = (end.y - start.y) / duration;
	}

	@Override
	public void move(IMovable movable) {
		if (stopped)
			return;

		float dt = Game.getDeltaTime();
		Vector loc = movable.getLocation();

		if (target == null) {
			target = end;
			loc.set(start);
		}
		else {
			float dist2 = target.dist2(loc);

			if (dist2 > distToTarget) {
				loc.set(target);

				if (stopped = notifyMoveEnd(movable))
					return;

				if (!looping) {
					stopped = true;
					return;
				}

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