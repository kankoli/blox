package com.blox.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IActionHandler;
import com.blox.framework.v0.IFont;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;

public class Button extends DrawableControl {
	private List<IClickListener> clickListeners;
	private String text;
	private IFont font;
	private Style style;

	Button() {
		style = new Style();
		clickListeners = new ArrayList<IClickListener>();
		font = FontManager.defaultFont;
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

		else if ("font".equals(attribute))
			font = FontManager.get(value);
		
		else
			super.setAttribute(attribute, value);
	}

	@Override
	protected void setAction(String action) {
		final IActionHandler handler = Game.getActionHandlerFactory().create(action);
		addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				handler.handle();				
			}
		});
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
		super.draw();
		if (text != null && !"".equals(text.trim()))
			TextDrawer.draw(font, text, drawable);
	}
	
	protected class Style {
		public ITexture textureDefault;
		public ITexture textureFocused;
		public ITexture textureDisabled;

		public Style() {

		}
	}
}