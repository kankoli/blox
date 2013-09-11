package com.blox.setgame.model;

import com.blox.framework.v0.IDrawable;
import com.blox.setgame.utils.CardDealer;

public abstract class SetGameMode implements IDrawable {
	protected abstract Card[] getCardsOnTable();
	protected CardDealer dealer;
	protected ISetGameModelListener gameListener;
		
	protected void setDealer(CardDealer dealer) {
		this.dealer = dealer;
	}
	
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
	
	public CardDealer getDealer() {
		return dealer;
	}
	
	public void deal() {
		dealer.deal();
	}

	@Override
	public void draw() {
		Card[] cc = getCardsOnTable();
		for (int i = 0; i < cc.length; i++)
			cc[i].draw();
	}
}
