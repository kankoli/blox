package com.turpgames.framework.v0.util;

import com.turpgames.framework.v0.IDrawable;
import com.turpgames.framework.v0.impl.Text;

public class TextSlider implements IDrawable {
	private final float speed;
	
	private final Text text;
	
	private float x;
	private float textWidth;
	private boolean isSliding;
	private ITextSliderListener listener;
	
	public TextSlider() {
		this(Game.getVirtualWidth() / 2f);
	}
	
	public TextSlider(float speed) {
		this.speed = speed;

		text = new Text();
		text.setWidth(Game.getVirtualWidth());
		text.setHeight(Game.getVirtualHeight());
		text.setWrapped(false);
	}
	
	public void setListener(ITextSliderListener listener) {
		this.listener = listener;
	}

	public String getText() {
		return text.getText();
	}

	public void setText(String text) {
		this.text.setText(text);
	}
	
	public void setFontScale(float scale) {
		text.setFontScale(scale);
	}
	
	public float getY() {
		return text.getY();
	}
	
	public void setY(float y) {
		text.setY(y);
	}

	public boolean isSliding() {
		return isSliding;
	}

	private void notifySlideEnd() {
		if (listener != null)
			listener.onTextSlideEnd(this);
	}

	public void slide() {
		x = Game.getVirtualWidth();
		textWidth = text.getTextAreaWidth();

		Drawer.getCurrent().register(this, Utils.LAYER_INFO);
		isSliding = true;
	}

	@Override
	public void draw() {
		if (!isSliding)
			return;

		x -= Game.getDeltaTime() * speed;

		if (x + textWidth < 0) {
			isSliding = false;
			notifySlideEnd();
			return;
		}
		
		text.setX(x);		
		text.draw();
	}

	public void stop() {
		isSliding = false;
	}
}