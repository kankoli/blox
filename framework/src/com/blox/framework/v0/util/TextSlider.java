package com.blox.framework.v0.util;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IFont;

public class TextSlider implements IDrawable {
	private IFont font;
	private String text;

	private boolean isSliding;
	private float y;
	private float x;
	private float duration;
	private float speed;
	private float width;
	private ITextSliderListener listener;

	public void setListener(ITextSliderListener listener) {
		this.listener = listener;
	}

	public IFont getFont() {
		return font;
	}

	public String getText() {
		return text;
	}

	public boolean isSliding() {
		return isSliding;
	}

	public float getDuration() {
		return duration;
	}

	private void notifySlideEnd() {
		if (listener != null)
			listener.onSlideEnd(this);
	}

	public void slide(IFont font, String text, float duration, float y) {
		this.font = font;
		this.text = text;
		this.duration = duration;
		this.y = Game.scale(y);
		this.isSliding = true;
		this.x = Game.getScreenWidth();
		this.width = font.measureText(text).x;
		this.speed = this.x / duration;
	}

	@Override
	public void draw() {
		if (!isSliding)
			return;

		x -= Game.getDeltaTime() * speed;

		if (x + width < 0) {
			isSliding = false;
			notifySlideEnd();
		}

		TextDrawer.draw(font, text, x, y);
	}

	public static interface ITextSliderListener {
		void onSlideEnd(TextSlider slider);
	}
}