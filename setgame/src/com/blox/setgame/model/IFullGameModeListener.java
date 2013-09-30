package com.blox.setgame.model;

public interface IFullGameModeListener extends ISetGameModeListener {
	void onModeEnd();

	void onNewGame();
}
