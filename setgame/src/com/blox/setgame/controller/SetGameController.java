package com.blox.setgame.controller;

import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.impl.StateManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;
import com.blox.setgame.model.Card;
import com.blox.setgame.model.GameTable;
import com.blox.setgame.utils.R;
import com.blox.setgame.view.SetGameScreen;

public abstract class SetGameController extends StateManager {
	
	private static final ITexture textureCardEmpty;
	private static final ITexture textureCardClosed;
	private static final ITexture textureCardBorder;

	private static final ISound soundSuccess;
	private static final ISound soundError;
	private static final ISound soundWait;
	private static final ISound soundTimeUp;

	static {
		IResourceManager r = Game.getResourceManager();

		textureCardEmpty = r.getTexture(R.game.textures.cardEmpty);
		textureCardClosed = r.getTexture(R.game.textures.cardClosed);
		textureCardBorder = r.getTexture(R.game.textures.cardBorder);

		soundSuccess = r.getSound(R.game.sounds.success);
		soundError = r.getSound(R.game.sounds.error);
		soundWait = r.getSound(R.game.sounds.wait);
		soundTimeUp = r.getSound(R.game.sounds.timeUp);
	}
	
	public static void drawTextureCardEmpty(IDrawingInfo info) {
		TextureDrawer.draw(textureCardEmpty, info);
	}
	
	public static void drawTextureCardClosed(IDrawingInfo info) {
		TextureDrawer.draw(textureCardClosed, info);
	}
	
	public static void drawTextureCardBorder(IDrawingInfo info) {
		TextureDrawer.draw(textureCardBorder, info);
	}
	
	public static void playSoundSuccess() {
		soundSuccess.play();
	}
	
	public static void playSoundError() {
		soundError.play();
	}

	public static void playSoundWait() {
		soundWait.play();
	}
	
	public static void playSoundTimeUp() {
		soundTimeUp.play();
	}
	
	protected SetGameScreen screen; // Parent screen.
	protected GameTable gameTable; // Game table.
	/**
	 * Waiting for user to select a card.
	 */
	protected WaitingState waitingState;
	
//	/**
//	 * User selected a card.
//	 */
//	private SelectedState selectedState;
	
	public SetGameController(SetGameScreen parent) {
		super();
		this.screen = parent;
		
		//Initializations
		waitingState = new WaitingState(this);
//		selectedState = new SelectedState();
	}
	
	abstract public void cardTapped(Card card);
	
	public final void registerWaiting() {
		gameTable.registerWaiting(waitingState);
	}
	
	public final void unregisterWaiting() {
		gameTable.unregisterWaiting();
	}
	
	public final void activated() {
		gameTable.activateCards();
	}
	
	public final void deactivated() {
		gameTable.deactivateCards();
	}
}
