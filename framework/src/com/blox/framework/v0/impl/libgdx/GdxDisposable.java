package com.blox.framework.v0.impl.libgdx;

import com.badlogic.gdx.utils.Disposable;
import com.blox.framework.v0.IDisposable;

class GdxDisposable implements IDisposable {
	private Disposable disposable;

	GdxDisposable(Disposable disposable) {
		this.disposable = disposable;
	}

	@Override
	public void dispose() {
		disposable.dispose();
	}
}
