package com.turpgames.framework.v0.impl;

import com.turpgames.framework.v0.IMovable;
import com.turpgames.framework.v0.IMoveEndListener;
import com.turpgames.framework.v0.IMover;

public abstract class Mover implements IMover {
	private IMoveEndListener endListener;
	
	public void setEndListener(IMoveEndListener endListener) {
		this.endListener = endListener;
	}
	
	protected boolean notifyMoveEnd(IMovable movable) {
		if (endListener == null || endListener.onMoveEnd(this, movable)) {
			movable.stopMoving();
			return true;
		}
		return false;
	}
}
