package com.blox.setgame.model;

import com.blox.framework.v0.util.Game;
import com.blox.setgame.utils.R;
import com.blox.setgame.utils.SetGameResources;

abstract class SetGameButton extends SetGameObject {
	protected boolean isActive;
	protected ISetGameButtonListener listener;

	protected SetGameButton() {
		activate();
	}

	public void activate() {
		isActive = true;
		listenInput(true);
	}

	public void deactivate() {
		isActive = false;
		listenInput(false);
	}

	public boolean isActive() {
		return isActive;
	}

	public ISetGameButtonListener getListener() {
		return listener;
	}

	public void setListener(ISetGameButtonListener listener) {
		this.listener = listener;
	}

	@Override
	protected boolean onTap() {
		SetGameResources.playSoundFlip();
		Game.vibrate(50);

		if (listener != null)
			listener.onButtonTapped();

		return true;
	}

	@Override
	public void draw() {
		if (!isActive)
			return;

		if (isTouched())
			getColor().set(R.colors.setBlue.r, R.colors.setBlue.g, R.colors.setBlue.b, getColor().a);
		else
			getColor().set(1, 1, 1, getColor().a);

		onDraw();
	}

	protected abstract void onDraw();
}