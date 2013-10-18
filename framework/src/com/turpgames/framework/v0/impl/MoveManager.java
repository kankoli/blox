package com.turpgames.framework.v0.impl;
import com.turpgames.framework.v0.IMovable;

public class MoveManager extends Manager<IMovable> {
	private static MoveManager current;
	
	static void setCurrent(MoveManager manager) {
		current = manager;
	}
	
	public static MoveManager getCurrent() {
		return current;
	}
	
	@Override
	protected void execute(IMovable item) {
		item.move();
	}	
}
