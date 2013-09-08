package com.blox.setgame.controller;

import com.blox.setgame.model.ICardEventListener;

public interface ISetGameController extends ICardEventListener {
	void activated();

	void deactivated();
}
