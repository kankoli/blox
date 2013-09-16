package com.blox.setgame.model;

import com.blox.framework.v0.IDrawable;

public abstract class SetGameMode implements IDrawable {
	protected CardDealer dealer;
	protected ISetGameModelListener gameListener;

	protected void notifySetFound() {
		if (gameListener != null)
			gameListener.onSetFound();
	}

	protected void notifyInvalidSetSelected() {
		if (gameListener != null)
			gameListener.onInvalidSetSelected();
	}

	public void setGameListener(ISetGameModelListener gameListener) {
		this.gameListener = gameListener;
	}

	public void setDealerListener(ICardDealerListener dealerListener) {
		if (dealer != null)
			dealer.setListener(dealerListener);
	}

	public void deal() {
		dealer.deal();
	}
}