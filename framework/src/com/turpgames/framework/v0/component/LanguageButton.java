package com.turpgames.framework.v0.component;

import java.util.Map;

import com.turpgames.framework.v0.metadata.GameMetadata;
import com.turpgames.framework.v0.metadata.LanguageMetadata;
import com.turpgames.framework.v0.util.Color;
import com.turpgames.framework.v0.util.Drawer;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.Utils;

public class LanguageButton extends ImageButton {

	private String id;
	
	protected LanguageButton(String languageId, float width, float height, Color defaultColor, Color touchedColor) {
		super(width, height, defaultColor, touchedColor);
		load(languageId);
	}
	
	private void load(String languageId) {
		LanguageMetadata metadata = GameMetadata.getLanguage(languageId);
		if (metadata == null)
			return;
		initAttributes(metadata.getAttributes());
	}
	
	public String getId() {
		return id;
	}
	
	protected void setAttribute(String attribute, String value) {
		if ("id".equals(attribute))
			id = value;
		else if ("flag-texture-id".equals(attribute))
			setTexture(value);
	}
	
	private void initAttributes(Map<String, String> attributes) {
		for (String key : attributes.keySet())
			setAttribute(key, attributes.get(key));
	}
	
	@Override
	protected boolean onTap() {
		Game.getLanguageManager().changeLanguage(id);
		return super.onTap();
	}
	
	@Override
	public void registerSelf() {
		Drawer.getCurrent().register(this, Utils.LAYER_DIALOG);
		Game.getInputManager().register(this, Utils.LAYER_DIALOG);
	}
}
