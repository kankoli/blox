package com.blox.setgame.model;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.setgame.utils.R;
import com.blox.setgame.utils.SetGameResources;

public class SetGameButton extends SetGameObject {
	public static interface ISetGameButtonListener {
		void onButtonTapped();
	}

	private ISetGameButtonListener listener;
	private String text;
	private IFont font;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public IFont getFont() {
		return font;
	}

	public void setFont(IFont font) {
		this.font = font;
	}

	public ISetGameButtonListener getListener() {
		return listener;
	}

	public void setListener(ISetGameButtonListener listener) {
		this.listener = listener;
	}

	@Override
	public void draw() {
		if (Game.getInputManager().isTouched() && isIn(Game.getInputManager().getX(), Game.getInputManager().getY())) {
			font.getColor().set(R.colors.setGreen);
		}
		else {
			font.getColor().set(1);
		}
		TextDrawer.draw(font, text, this, TextDrawer.AlignW);
	}

	@Override
	protected boolean onTap() {
		SetGameResources.playSoundFlip();
		Game.vibrate(50);
		if (listener != null) {
			listener.onButtonTapped();
			return true;
		}
		return super.onTap();
	}
}