package com.turpgames.framework.v0.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.turpgames.framework.v0.ILanguageListener;
import com.turpgames.framework.v0.ISound;
import com.turpgames.framework.v0.ITexture;
import com.turpgames.framework.v0.impl.AttachedText;
import com.turpgames.framework.v0.impl.Text;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Utils;

public class FormButton extends DrawableControl implements ILanguageListener {
	private final static Color white = Color.white();

	private final Text text;

	private List<IClickListener> clickListeners;
	private Style style;
	private int vibrationDuration;
	public ISound clickSound;
	private Color focusColor;
	
	private boolean ignore;

	private String resource_text;

	FormButton() {
		style = new Style();
		clickListeners = new ArrayList<IClickListener>();
		text = new AttachedText(drawable);
		text.setHorizontalAlignment(Text.HAlignCenter);
		text.setWrapped(false);
		Game.getLanguageManager().register(this);
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
	protected boolean onTap() {
		if (!isEnabled || ignore)
			return false;

		notifyClickListeners();
		if (vibrationDuration > 0)
			Game.vibrate(vibrationDuration);
		if (clickSound != null)
			clickSound.play();

		return true;
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
			text.setText(value);

		else if ("font-scale".equals(attribute))
			text.setFontScale(Utils.parseFloat(value));

		// TODO: buna daha g�zel bi ��z�m bulmal�
		else if ("hidden-ios".equals(attribute)) {
			if (Game.isIOS() && "true".equals(value)) {
				disable();
				hide();
				ignore = true;
			}
		}

		else if ("resource-text".equals(attribute)) {
			resource_text = value;
			text.setText(Game.getLanguageManager().getString(resource_text));
		}

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
		if (ignore)
			return;
		if (isEnabled && isTouched())
			text.getColor().set(focusColor);
		else
			text.getColor().set(white);

		text.draw();
	}

	public static class Style {
		public ITexture textureDefault;
		public ITexture textureFocused;
		public ITexture textureDisabled;

		private Style() {

		}
	}

	@Override
	public void onLanguageChanged() {
		text.setText(Game.getLanguageManager().getString(resource_text));
	}
}