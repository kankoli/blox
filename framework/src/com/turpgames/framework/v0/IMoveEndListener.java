package com.turpgames.framework.v0;

public interface IMoveEndListener {
	/**
	 * called when a mover completes its moving action
	 * @param mover
	 * @param movable
	 * @return false to continue moving
	 */
	boolean onMoveEnd(IMover mover, IMovable movable);
}