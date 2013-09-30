package com.blox.setgame.model;

public abstract class SetGameMode {
	protected CardDealer dealer;
	protected ISetGameModeListener modeListener;

	protected void notifySetFound() {
		if (modeListener != null)
			modeListener.onSetFound();
	}

	protected void notifyInvalidSetSelected() {
		if (modeListener != null)
			modeListener.onInvalidSetSelected();
	}
	
	public void setModeListener(ISetGameModeListener modeListener) {
		this.modeListener = modeListener;
	}
	
	public void setDealerListener(ICardDealerListener dealerListener) {
		if (dealer != null)
			dealer.setListener(dealerListener);
	}

	public void deal() {
		dealer.deal();
	}
}