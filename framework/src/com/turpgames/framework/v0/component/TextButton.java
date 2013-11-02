package com.turpgames.framework.v0.component;

import com.turpgames.framework.v0.impl.AttachedText;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Utils;

public class TextButton extends Button {
	private Text text;
	
	public TextButton(Color defaultColor, Color touchedColor) {
		super(defaultColor, touchedColor);
		text = new AttachedText(this);
		text.setPadX(10);
		text.setPadY(5);
		text.setWrapped(false);
	}

	public String getText() {
		return this.text.getText();
	}

	public void setText(String text) {
		this.text.setText(text);
		updateSize();
	}

	private void updateSize() {
		setWidth(text.getTextAreaWidth() + 20);
		setHeight(text.getTextAreaHeight() + 10);
	}

	@Override
	public boolean ignoreShifting() {
		return false;
	}
	
	@Override
	public boolean ignoreViewport() {
		return false;
	}
	
	@Override
	public Color getColor() {
		return text.getColor();
	}

	@Override
	protected void onDraw() {
		text.draw();
	}

	@Override
	public void registerSelf() {
		Game.getInputManager().register(this, Utils.LAYER_SCREEN);
	}
}