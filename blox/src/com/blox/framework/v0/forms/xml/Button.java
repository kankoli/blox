package com.blox.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.Vector;

public class Button extends Control {
	private List<IClickListener> clickListeners;
	private String text;

	private Style style;

	Button() {
		style = new Style();
		clickListeners = new ArrayList<IClickListener>();

		drawable = new ControlDrawableAdapter(this);
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
		if ("texture-default".equals(attribute))
			style.textureDefault = Game.getResourceManager().loadTexture(value);

		else if ("texture-focused".equals(attribute))
			style.textureFocused = Game.getResourceManager().loadTexture(value);

		else if ("texture-disabled".equals(attribute))
			style.textureDisabled = Game.getResourceManager().loadTexture(value);

		else if ("text".equals(attribute))
			text = value;

		else
			super.setAttribute(attribute, value);
	}

	@Override
	protected ITexture getTexture() {
		if (!isEnabled)
			return style.textureDisabled;
		return isTouched() ? style.textureFocused : style.textureDefault;
	}

	@Override
	protected String getNodeName() {
		return "button";
	}

	@Override
	protected void draw() {
		Vector loc = getDrawable().getLocation();
		Game.getTextDrawer().draw(text, loc.x + 50, loc.y + 40);
	}
	
	protected class Style {
		public ITexture textureDefault;
		public ITexture textureFocused;
		public ITexture textureDisabled;

		public Style() {

		}
	}
}