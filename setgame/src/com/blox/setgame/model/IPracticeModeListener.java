package com.blox.setgame.model;

public interface IPracticeModeListener extends ISetGameModelListener {
	void onUnblock();

	void onDealTimeUp();

	void onModeEnd();

	void onNewGame();
}