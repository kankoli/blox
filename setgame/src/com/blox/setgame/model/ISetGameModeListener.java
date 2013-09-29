package com.blox.setgame.model;

public interface ISetGameModeListener extends ICardListener {
	void onSetFound();

	void onInvalidSetSelected();
}