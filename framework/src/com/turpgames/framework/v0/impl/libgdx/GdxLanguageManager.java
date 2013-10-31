package com.turpgames.framework.v0.impl.libgdx;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.turpgames.framework.v0.ILanguageListener;
import com.turpgames.framework.v0.ILanguageManager;
import com.turpgames.framework.v0.impl.Settings;

public class GdxLanguageManager implements ILanguageManager {

	private Properties strings;
	private List<ILanguageListener> listeners;
	
	GdxLanguageManager() {
		loadLanguage(Settings.getString("language", "en"), Settings.getString("country", "US"));
		listeners = new ArrayList<ILanguageListener>();
	}
	
	@Override
	public String getString(String id) {
		if (strings.getProperty(id) == null)
			return "null";
		return strings.getProperty(id);
	}

	@Override
	public void changeLanguage(String id, String country) {
		Settings.putString("language", id);
		Settings.putString("country", country);
		loadLanguage(id, country);
		notifyListeners();
	}

	public void register(ILanguageListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	public void unregister (ILanguageListener listener) {
		listeners.remove(listener);
	}
	
	private void loadLanguage(String id, String country) {
		InputStream is = Gdx.files.internal("lang/Strings_" + id + "_" + country + ".properties").read();

		strings = new Properties();
		try {
			InputStreamReader isr = new InputStreamReader(is, "UTF8" );
			strings.load(isr);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private void notifyListeners() {
		for (ILanguageListener listener : listeners)
			listener.onLanguageChanged();
	}
}
