package com.blox.framework.v0;

public interface IDrawer {
	public static final IDrawer NULL = new IDrawer() {
		@Override
		public void draw(IDrawable drawable) {

		}
	};

	void draw(IDrawable drawable);
}
