package com.blox.framework.v0.util;

import com.blox.framework.v0.IDrawable;
import com.blox.framework.v0.IDrawingInfo;
import com.blox.framework.v0.IFont;

public class TextSlider implements IDrawable {
	private final static float speed = Game.getViewportWidth() / 2f;
	
	private IFont font;
	private String text;

	private boolean isSliding;
	private float y;
	private float x;
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

	private void notifySlideEnd() {
		if (listener != null)
			listener.onSlideEnd(this);
	}

	public void slide(IFont font, String text, float y) {
		this.font = font;
		this.text = text;
		this.y = y;
		this.isSliding = true;
		this.x = Game.getVirtualWidth();
		this.width = Game.descale(font.measureText(text).x);
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

		Game.renderingShiftX = x;
		Game.renderingShiftY = y;
		
		TextDrawer.draw(font, text, IDrawingInfo.viewport, TextDrawer.AlignSW);

		Game.renderingShiftX = 0;
		Game.renderingShiftY = 0;
	}

	public static interface ITextSliderListener {
		void onSlideEnd(TextSlider slider);
	}

	public void stop() {
		isSliding = false;
	}
}