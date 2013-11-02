package com.turpgames.framework.v0.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.turpgames.framework.v0.ILanguageListener;
import com.turpgames.framework.v0.metadata.GameMetadata;
import com.turpgames.framework.v0.metadata.LanguageMetadata;

public class LanguageManager extends Manager<ILanguageListener> {

	private Properties strings;
	
	public LanguageManager() {
		loadLanguage(Settings.getString("language", "en-US"));
//		changeLanguage("en-US");
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
			InputStream is = Gdx.files.internal(metadata.getFilePath()).read();
			InputStreamReader isr = new InputStreamReader(is, metadata.getEncoding());
			strings.load(isr);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
	}
	
	@Override
	protected void execute(ILanguageListener item) {
		item.onLanguageChanged();
	}
}
