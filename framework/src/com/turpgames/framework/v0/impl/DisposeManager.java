package com.turpgames.framework.v0.impl;

import com.turpgames.framework.v0.IDisposable;

public class DisposeManager extends Manager<IDisposable> {
	@Override
	protected void execute(IDisposable item) {
		item.dispose();
	}
	
	@Override
	public void execute() {
		super.execute();
		items.clear();
	}
}