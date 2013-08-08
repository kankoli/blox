package com.blox.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.ITextDrawer;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;
import com.blox.maze.util.R;

public class Button extends Control {
	protected String text;
	
	private List<IClickListener> clickListeners;

	private ITexture btnLightBlue;
	private ITexture btnPink;

	public Button() {
		btnLightBlue = Game.getResourceManager().loadTexture(R.textures.buttonLightBlue);
		btnPink = Game.getResourceManager().loadTexture(R.textures.buttonPink);
		clickListeners = new ArrayList<IClickListener>();
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void addClickListener(IClickListener listener) {
		clickListeners.add(listener);
	}
	
	public void removeClickListener(IClickListener listener) {
		clickListeners.remove(listener);
	}
	
	protected void notifyClickListeners() {
		for (IClickListener listener : clickListeners)
			listener.onClick(this);
	}
	
	@Override
	protected void onTap() {		
		notifyClickListeners();
	}
	
	@Override
	protected void setAttribute(String attribute, String value) {
		if ("text".equals(attribute))
			setText(value);
		else
			super.setAttribute(attribute, value);
	}

	@Override
	protected ITexture getTexture() {
		return isTouched() ? btnPink : btnLightBlue;
	}

	@Override
	protected void draw() {
		super.draw();

		Vector loc = ControlDrawableAdapter.instance.getLocation();
		float width = ControlDrawableAdapter.instance.getWidth();
		float height = ControlDrawableAdapter.instance.getHeight();

		ITextDrawer txtDrawer = Game.getTextDrawer();
		IFont font = txtDrawer.getFont();
		Vector size = font.calculateSize(text);
		float lineHeight = font.getLineHeight();

		txtDrawer.draw(text, loc.x + (width - size.x) / 2, loc.y + (height - lineHeight) / 2 + lineHeight);
	}
}