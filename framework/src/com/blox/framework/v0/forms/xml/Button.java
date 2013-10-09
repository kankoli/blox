package com.blox.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.blox.framework.v0.IFont;
import com.blox.framework.v0.ISound;
import com.blox.framework.v0.ITexture;
import com.blox.framework.v0.util.Color;
import com.blox.framework.v0.util.FontManager;
import com.blox.framework.v0.util.Game;
import com.blox.framework.v0.util.TextDrawer;
import com.blox.framework.v0.util.Utils;

public class Button extends DrawableControl {
	private final static Color white = Color.white();

	private List<IClickListener> clickListeners;
	private String text;
	private Style style;
	private int vibrationDuration;
	public ISound clickSound;
	public IFont font;
	private Color focusColor;

	Button() {
		style = new Style();
		clickListeners = new ArrayList<IClickListener>();
		font = FontManager.createDefaultFontInstance();
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
		if (vibrationDuration > 0)
			Game.vibrate(vibrationDuration);
		if (clickSound != null)
			clickSound.play();
	}

	@Override
	protected void setAttribute(String attribute, String value) {
		if ("texture-default".equals(attribute))
			style.textureDefault = Game.getResourceManager().getTexture(value);

		else if ("texture-focused".equals(attribute))
			style.textureFocused = Game.getResourceManager().getTexture(value);

		else if ("texture-disabled".equals(attribute))
			style.textureDisabled = Game.getResourceManager().getTexture(value);

		else if ("sound-click".equals(attribute))
			clickSound = Game.getResourceManager().getSound(value);

		else if ("focus-color".equals(attribute))
			focusColor = Color.fromHex(value);

		else if ("vibrate".equals(attribute))
			vibrationDuration = Utils.parseInt(value);

		else if ("text".equals(attribute))
			text = value;

		else
			super.setAttribute(attribute, value);
	}

	@Override
	protected void setAction(String action) {
		final IControlActionHandler handler = Game.getActionHandlerFactory().create(this, action);
		addClickListener(new IClickListener() {
			@Override
			public void onClick(Control control) {
				handler.handle(control);
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
	protected void onDraw() {
		if (text == null || "".equals(text.trim()))
			return;

		if (isTouched())
			font.getColor().set(focusColor);
		else
			font.getColor().set(white);

		TextDrawer.draw(font, text, drawable);
	}

	public static class Style {
		public ITexture textureDefault;
		public ITexture textureFocused;
		public ITexture textureDisabled;

		private Style() {

		}
	}
}