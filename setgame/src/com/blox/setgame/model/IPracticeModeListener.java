package com.blox.setgame.model;

public interface IPracticeModeListener extends ISetGameModeListener {
	void onUnblock();

	void onDealTimeUp();

	void onModeEnd();

	void onNewGame();
}