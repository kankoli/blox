package com.blox.setgame.model;

public abstract class SetGameModeModel {
	protected CardDealer dealer;
	protected ISetGameModelListener modelListener;

	protected void notifySetFound() {
		if (modelListener != null)
			modelListener.onSetFound();
	}

	protected void notifyInvalidSetSelected() {
		if (modelListener != null)
			modelListener.onInvalidSetSelected();
	}
	
	public void setModelListener(ISetGameModelListener modelListener) {
		this.modelListener = modelListener;
	}
	
	public void setDealerListener(ICardDealerListener dealerListener) {
		if (dealer != null)
			dealer.setListener(dealerListener);
	}

	public void deal() {
		dealer.deal();
	}
}