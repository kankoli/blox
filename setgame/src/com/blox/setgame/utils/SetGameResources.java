package com.blox.setgame.utils;

import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.IResourceManager;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextureDrawer;

public final class SetGameResources {
	private SetGameResources() {
		
	}

	private static final ISound soundError;
	private static final ISound soundSuccess;
	private static final ISound soundTimeUp;
	private static final ISound soundWait;
	
	private static final ITexture textureCardBorder;
	private static final ITexture textureCardClosed;
	private static final ITexture textureCardEmpty;

	static {
		IResourceManager r = Game.getResourceManager();

		soundSuccess = r.getSound(R.game.sounds.success);
		soundError = r.getSound(R.game.sounds.error);
		soundWait = r.getSound(R.game.sounds.wait);
		soundTimeUp = r.getSound(R.game.sounds.timeUp);

		textureCardEmpty = r.getTexture(R.game.textures.cardEmpty);
		textureCardClosed = r.getTexture(R.game.textures.cardClosed);
		textureCardBorder = r.getTexture(R.game.textures.cardBorder);
	}

	public static void drawTextureCardBorder(IDrawingInfo info) {
		TextureDrawer.draw(textureCardBorder, info);
	}

	public static void drawTextureCardClosed(IDrawingInfo info) {
		TextureDrawer.draw(textureCardClosed, info);
	}

	public static void drawTextureCardEmpty(IDrawingInfo info) {
		TextureDrawer.draw(textureCardEmpty, info);
	}

	public static void playSoundError() {
		soundError.play();
	}

	public static void playSoundSuccess() {
		soundSuccess.play();
	}

	public static void playSoundTimeUp() {
		soundTimeUp.play();
	}

	public static void playSoundWait() {
		soundWait.play();
	}
}
