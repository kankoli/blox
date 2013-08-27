package com.blox.framework.v0.impl;
import com.blox.framework.v0.IMovable;

public class MoveManager extends Manager<IMovable> {
	@Override
	protected void execute(IMovable item) {
		item.move();
	}	
}
