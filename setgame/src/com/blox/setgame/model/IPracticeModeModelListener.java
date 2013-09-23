package com.blox.setgame.model;

public interface IPracticeModeModelListener extends ISetGameModelListener {
	void onUnblock();

	void onDealTimeUp();

	void onModeEnd();

	void onNewGame();
}