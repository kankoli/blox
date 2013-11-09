package com.turpgames.framework.v0.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.turpgames.framework.v0.ILanguageListener;
import com.turpgames.framework.v0.metadata.GameMetadata;
import com.turpgames.framework.v0.metadata.LanguageMetadata;
import com.turpgames.framework.v0.util.Game;

public class LanguageManager extends Manager<ILanguageListener> {

	private Properties strings;
	
	public LanguageManager() {
		loadLanguage(Settings.getString("language", "en-US"));
	}
	
	public String getString(String id) {
		if (strings.getProperty(id) == null)
			return "null";
		return strings.getProperty(id);
	}
	
	public void changeLanguage(String id) {
		Settings.putString("language", id);
		loadLanguage(id);
		super.execute();
	}
	
	private void loadLanguage(String id) {
		strings = new Properties();
		try {
			LanguageMetadata metadata = GameMetadata.getLanguage(id);
			InputStream is = Game.getResourceManager().readFile(metadata.getFilePath());
			InputStreamReader isr = new InputStreamReader(is, metadata.getEncoding());
			strings.load(isr);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	protected void execute(ILanguageListener item) {
		item.onLanguageChanged();
	}
}
