package com.turpgames.framework.v0.component.info;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;

public class GameInfo implements IDrawable {
	protected Text text;

	public GameInfo() {
		text = new Text();
		text.setWidth(Game.getVirtualWidth());
		text.setHeight(Game.getVirtualHeight());
	}

	public void setAlignment(int halign, int valign) {
		text.setAlignment(halign, valign);
	}

	public void setPadding(float padX, float padY) {
		text.setPadding(padX, padY);
	}

	public void setColor(Color color) {
		text.getColor().set(color);
	}
	
	public String getText() {
		return text.getText();
	}
	
	public void setText(String info) {
		text.setText(info);
	}

	@Override
	public void draw() {
		text.draw();
	}

	public void setFontScale(float f) {
		text.setFontScale(f);
	}
}
