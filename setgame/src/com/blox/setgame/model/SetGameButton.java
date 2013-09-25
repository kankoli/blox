package com.blox.setgame.model;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Vector;
import com.blox.setgame.utils.R;
import com.blox.setgame.utils.SetGameResources;

class SetGameButton extends SetGameObject {
	protected ISetGameButtonListener listener;
	protected String text;
	protected boolean isActive = true;
	protected IFont font;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		updateSize();
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public IFont getFont() {
		return font;
	}

	public void setFont(IFont font) {
		this.font = font;
		updateSize();
	}

	public ISetGameButtonListener getListener() {
		return listener;
	}

	public void setListener(ISetGameButtonListener listener) {
		this.listener = listener;
	}

	protected void updateSize() {
		if (font == null || text == null)
			return;
		Vector textSize = font.measureText(text);
		
		setWidth(Game.descale(textSize.x) + 10);
		setHeight(Game.descale(textSize.y) + 5);
	}

	@Override
	public void draw() {
		if (!isActive)
			return;
		if (Game.getInputManager().isTouched() && isIn(Game.getInputManager().getX(), Game.getInputManager().getY()))
			font.getColor().set(R.colors.setGreen);
		else
			font.getColor().set(1);
		
		// ShapeRenderer.drawRect(getLocation().x, getLocation().y, getWidth(), getHeight(), font.getColor(), false, ignoreViewport());
		
		TextDrawer.draw(font, text, this);
	}

	@Override
	protected boolean onTap() {
		if (!isActive)
			return false;

		SetGameResources.playSoundFlip();
		Game.vibrate(50);

		if (listener != null)
			listener.onButtonTapped();

		return true;
	}
}