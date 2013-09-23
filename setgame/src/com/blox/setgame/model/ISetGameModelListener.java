package com.blox.setgame.model;

public interface ISetGameModelListener extends ICardListener {
	void onSetFound();

	void onInvalidSetSelected();
}