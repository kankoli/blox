package com.turpgames.framework.v0.impl.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.turpgames.framework.v0.IDisposable;
import com.turpgames.framework.v0.ISettings;
import com.turpgames.framework.v0.util.Game;

final class GdxSettings implements ISettings, IDisposable {	
	private Preferences prefs;

	GdxSettings() {
		String prefName = Game.getParam("settings-key");
		prefs = Gdx.app.getPreferences(prefName);
		Game.registerDisposable(this);
	}

	@Override
	public void putBoolean(String key, boolean val) {
		prefs.putBoolean(key, val);
		prefs.flush();
	}

	@Override
	public void putFloat(String key, float val) {
		prefs.putFloat(key, val);	
		prefs.flush();	
	}

	@Override
	public void putInteger(String key, int val) {
		prefs.putInteger(key, val);		
		prefs.flush();
	}

	@Override
	public void putLong(String key, long val) {
		prefs.putLong(key, val);
		prefs.flush();		
	}

	@Override
	public void putString(String key, String val) {
		prefs.putString(key, val);
		prefs.flush();
	}

	@Override
	public boolean getBoolean(String key, boolean defValue) {
		return prefs.getBoolean(key, defValue);
	}

	@Override
	public float getFloat(String key, float defValue) {
		return prefs.getFloat(key, defValue);
	}

	@Override
	public int getInteger(String key, int defValue) {
		return prefs.getInteger(key, defValue);
	}

	@Override
	public long getLong(String key, long defValue) {
		return prefs.getLong(key, defValue);
	}

	@Override
	public String getString(String key, String defValue) {
		return prefs.getString(key, defValue);
	}

	@Override
	public void dispose() {
		prefs.flush();
	}
}
