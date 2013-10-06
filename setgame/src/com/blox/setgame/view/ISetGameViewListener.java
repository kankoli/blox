package com.blox.setgame.view;

import com.blox.framework.v0.IDrawable;

public interface ISetGameViewListener extends IDrawable {
	void onScreenActivated();

	void onScreenDeactivated();
}
