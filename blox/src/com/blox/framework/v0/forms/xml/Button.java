package com.blox.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Game;

public class Button extends Control {
	private List<IClickListener> clickListeners;

	private Style style;

	Button() {
		style = new Style();
		clickListeners = new ArrayList<IClickListener>();

		drawable = new AnimatedControlDrawableAdapter(this, 2f, 5f, 0.03f, 0.01f);
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

		else
			super.setAttribute(attribute, value);
	}

	@Override
	protected ITexture getTexture() {
		if (!isEnabled)
			return style.textureDisabled;
		return isTouched() ? style.textureFocused : style.textureDefault;
	}


	protected class Style {
		public ITexture textureDefault;
		public ITexture textureFocused;
		public ITexture textureDisabled;

		public Style() {

		}
	}
}